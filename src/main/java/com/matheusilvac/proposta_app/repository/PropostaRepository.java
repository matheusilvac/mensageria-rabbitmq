package com.matheusilvac.proposta_app.repository;

import com.matheusilvac.proposta_app.entity.Proposta;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
