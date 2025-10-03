package com.desafio.votacao.exceptions.pauta;

public class PautaInexistenteException extends RuntimeException{
    public PautaInexistenteException(String mensagem){
        super(mensagem);
    }
}
