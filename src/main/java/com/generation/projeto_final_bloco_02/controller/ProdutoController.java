package com.generation.projeto_final_bloco_02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projeto_final_bloco_02.model.Produto;
import com.generation.projeto_final_bloco_02.repository.CategoriaRepository;
import com.generation.projeto_final_bloco_02.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping()
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
        if (categoriaRepository.existsById(produto.getCategoria().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria definida para o produto não existe!",
                null);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Produto>> getByNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        return produtoRepository.findById(id)
                .map(resposta -> {
                    if (categoriaRepository.existsById(produto.getCategoria().getId())) {
                        produto.setId(id);
                        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
                    }
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "A categoria fornecida no produto não existe!",
                            null);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
    }
}