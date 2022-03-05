package com.ci.projeto.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ci.projeto.model.Pessoa;
import com.ci.projeto.model.service.PessoaService;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

	private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping
	public ResponseEntity<Page<Pessoa>> getAll(Pageable pageable){
		Page<Pessoa> listaPessoa = pessoaService.findAll(pageable);
		return ResponseEntity.ok().body(listaPessoa);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.getById(id);
		return ResponseEntity.ok(pessoa);
	}
	
	
}
