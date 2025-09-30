package com.desafio.votacao.exceptions.pauta;

public class PautaExistenteException extends RuntimeException{
    public PautaExistenteException(String mensagem){
        super(mensagem);
    }
}
