package com.matheusilvac.proposta_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaResponseDTO{
    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;
    private String valorSolicitadoFmt;
    private int prazoPagamento;
    private Boolean aprovada;
    private String observaco;
}
