package com.loja.bakend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> tratar404(

			RecursoNaoEncontradoException ex) {

		Map<String, Object> erro = new HashMap<>();

		erro.put("status", 404);

		erro.put("error", "Not Found");

		erro.put("message", ex.getMessage());

		return erro;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> tratar400(

			IllegalArgumentException ex) {

		Map<String, Object> erro = new HashMap<>();

		erro.put("status", 400);

		erro.put("error", "Bad Request");

		erro.put("message", ex.getMessage());

		return erro;
	}

}