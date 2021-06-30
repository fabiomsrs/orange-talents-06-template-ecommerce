package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import br.com.zupacademy.fabiano.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Produto produto = dto.converter(categoriaRepository, usuario);
        repository.save(produto);
        return ResponseEntity.ok(produto);
    }
}
