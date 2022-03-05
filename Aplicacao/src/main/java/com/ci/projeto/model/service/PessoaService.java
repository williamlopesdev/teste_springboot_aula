package com.ci.projeto.model.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


import com.ci.projeto.model.Pessoa;
import com.ci.projeto.model.service.exception.RecursoNaoEncontradoException;
import com.ci.projeto.repository.PessoaRepository;

@Service
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public Page<Pessoa> findAll(Pageable pageable){
		Page<Pessoa> list = pessoaRepository.findAll(pageable);
		
		return list;
	}
	
	public Pessoa getById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		Pessoa p = pessoa.orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));
		return p;
	}
}
