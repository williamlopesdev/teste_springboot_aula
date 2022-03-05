package com.ci.projeto.controller.handler;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ci.projeto.model.exception.ErroPadrao;
import com.ci.projeto.model.service.exception.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(RecursoNaoEncontradoException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao erro = new ErroPadrao();
		erro.setTimeStamp(Instant.now());;
		erro.setStatus(status.value());
		erro.setError("Recurso não Encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);

	}
}
