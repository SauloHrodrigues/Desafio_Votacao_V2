package com.desafio.votacao.exceptions;

import com.desafio.votacao.exceptions.pauta.PautaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PautaExistenteException.class)
    public ResponseEntity<Object> hendlerPautaExistenteException(PautaExistenteException mensagem){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error:"+mensagem.getMessage());
    }
}
