package com.matheusilvac.proposta_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaRequestDTO {
    private String nome;
    private String sobrenome;
    private  String telefone;
    private  String cpf;
    private  Double renda;
    private  Double valorSolicitado;
    private  int prazoPagamento;
}
