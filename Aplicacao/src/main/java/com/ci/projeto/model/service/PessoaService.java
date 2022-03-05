package com.ci.projeto.model.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	public Pessoa create (Pessoa pessoa) {
		
		Pessoa p = new Pessoa();
		p.setNome(pessoa.getNome());
		p.setSobreNome(pessoa.getSobreNome());
		pessoaRepository.save(p);
		
		return p;
	}
	
	@Transactional
	public Pessoa update(Pessoa pessoa, Long id) {
		try {
			Optional<Pessoa> p = pessoaRepository.findById(id);
			p.get().setNome(pessoa.getNome());
			p.get().setSobreNome(pessoa.getSobreNome());
			
			pessoaRepository.save(p.get());
			
			return p.get();
			
		}catch(EntityNotFoundException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}catch(NoSuchElementException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}
		
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			
			pessoaRepository.deleteById(id);
			
			
		}catch(EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}
	}
	
}
