package com.matheusilvac.proposta_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_proposta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private Boolean integrada;

    private String observacao;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
