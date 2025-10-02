package com.desafio.votacao.enuns;

public enum VotoTipo {
    SIM("sim"),
    NAO("não");

    private String status;

    VotoTipo(String status) {
        this.status = status;
    }
}
