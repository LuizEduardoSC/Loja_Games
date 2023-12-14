package com.generation.lojagames.controller;

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

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@RequestParam String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {

		if (produtoRepository.existsById(produto.getNome()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já existe!", null);

		// INSERT INTO tb_postagens (titulo, texto) VALUES (?, ?);
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {

		if (produtoRepository.existsById(produto.getId())) {

			if (categoriaRepository.existsById(produto.getNome().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository
				.findById(id); /* Criar a Optional e procurar pela id digitada */
		if (produto.isEmpty()) /* Verificar se o valor digitado existe */
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); /* Se não existir, retornar NOT FOUND */
		else
			produtoRepository.deleteById(id); /* Se for encontrado, deletar */

	}
}
