package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.dto.CompraDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Compra;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import br.com.zupacademy.fabiano.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.fabiano.mercadolivre.utils.EnviadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    CompraRepository repository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EnviadorEmail sendGrid;

    @PostMapping
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Produto produto = produtoRepository.findById(dto.getProduto()).get();

        if(produto.abaterCompra(dto.getQuantidade())){
            produtoRepository.save(produto);
            Compra compra = dto.converter(produtoRepository, usuario);
            repository.save(compra);

            sendGrid.enviaEmail(usuario);
            return ResponseEntity.status(HttpStatus.FOUND).body(compra.getGatewayPagamento().getUrl(compra.getId()));
        }

        return ResponseEntity.badRequest().body("produto quantidade insuficience");
    }


}
