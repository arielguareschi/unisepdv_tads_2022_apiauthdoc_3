package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.Produto;
import br.edu.unisep.tads.ifud.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produto")
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }
    
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> getProdutoById(
            @PathVariable(value = "id") Long produtoId) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto nao encontrada: " + produtoId));
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping("/produto")
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> updateProduto(
            @PathVariable(value = "id") Long produtoId,
            @RequestBody Produto detalhes) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto nao encontrada: " + produtoId));
        produto.setTitulo(detalhes.getTitulo());
        produto.setDescricao(detalhes.getDescricao());
        produto.setPreco(detalhes.getPreco());
        produto.setCusto(detalhes.getCusto());
        produto.setFornecedor(detalhes.getFornecedor());
        Produto produtoUpdate = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoUpdate);
    }

    @DeleteMapping("/produto/{id}")
    public Map<String, Boolean> deleteProduto(
            @PathVariable(value = "id") Long produtoId
            ) throws Exception {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto nao encontrada: " + produtoId));
        produtoRepository.delete(produto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
