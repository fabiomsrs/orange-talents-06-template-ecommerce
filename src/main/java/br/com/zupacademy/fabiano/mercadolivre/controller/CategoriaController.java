package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.dto.CategoriaDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Categoria;
import br.com.zupacademy.fabiano.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaRepository repository;

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid CategoriaDto dto){
        Categoria categoria = dto.converter(repository);
        repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }
}
