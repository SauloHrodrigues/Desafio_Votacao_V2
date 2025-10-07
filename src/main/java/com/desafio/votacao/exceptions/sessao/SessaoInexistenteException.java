package com.desafio.votacao.exceptions.sessao;

public class SessaoInexistenteException extends RuntimeException{
    public SessaoInexistenteException(String mensagem){
        super(mensagem);
    }
}
