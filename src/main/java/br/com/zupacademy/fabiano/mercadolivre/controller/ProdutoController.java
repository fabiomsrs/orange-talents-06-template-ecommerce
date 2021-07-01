package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.dto.ImagemDto;
import br.com.zupacademy.fabiano.mercadolivre.dto.OpiniaoDto;
import br.com.zupacademy.fabiano.mercadolivre.dto.PerguntaDto;
import br.com.zupacademy.fabiano.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import br.com.zupacademy.fabiano.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.fabiano.mercadolivre.utils.Bucket;
import br.com.zupacademy.fabiano.mercadolivre.utils.EnviadorEmail;
import br.com.zupacademy.fabiano.mercadolivre.utils.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    OpiniaoRepository opiniaoRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    Uploader bucket;

    @Autowired
    EnviadorEmail sendGrid;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Produto produto = dto.converter(categoriaRepository, usuario);
        repository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<Set<String>> adicionarImagem(@PathVariable("id") Long id, @Valid ImagemDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Produto> optionalProduto = repository.findById(id);

        if(optionalProduto.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Produto produto = optionalProduto.get();

        if(!produto.pertence(usuario)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<String> links = bucket.envia(dto.getImagens());
        produto.adicionarImagens(links);
        repository.save(produto);
        return ResponseEntity.ok(links);
    }

    @PostMapping("/{id}/opinioes")
    public ResponseEntity<?> opiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Opiniao opiniao = dto.converter(usuario, optionalProduto.get());
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok(opiniao);
    }

    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> pergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Pergunta pergunta = dto.converter(usuario, optionalProduto.get());
        perguntaRepository.save(pergunta);
        sendGrid.enviaEmail(usuario);
        return ResponseEntity.ok(pergunta);
    }
}
