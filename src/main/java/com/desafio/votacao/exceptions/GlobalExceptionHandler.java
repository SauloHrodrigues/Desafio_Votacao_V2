package com.desafio.votacao.exceptions;

import com.desafio.votacao.exceptions.pauta.PautaExistenteException;
import com.desafio.votacao.exceptions.pauta.PautaInexistenteException;
import com.desafio.votacao.exceptions.sessao.SessaoInexistenteException;
import com.desafio.votacao.exceptions.voto.VotoJaProferidoException;
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

    @ExceptionHandler(VotoJaProferidoException.class)
    public ResponseEntity<Object> hendlerVotoJaProferidoException(VotoJaProferidoException mensagem){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error:"+mensagem.getMessage());
    }

    @ExceptionHandler(PautaInexistenteException.class)
    public ResponseEntity<Object> hendlerPautaInexistenteException(PautaInexistenteException mensagem){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error:"+mensagem.getMessage());
    }

    @ExceptionHandler(SessaoInexistenteException.class)
    public ResponseEntity<Object> hendlerSessaoInexistenteException(SessaoInexistenteException mensagem){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error:"+mensagem.getMessage());
    }
}
