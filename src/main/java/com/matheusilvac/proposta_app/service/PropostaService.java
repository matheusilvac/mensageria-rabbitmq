package com.matheusilvac.proposta_app.service;

import com.matheusilvac.proposta_app.dtos.PropostaRequestDTO;
import com.matheusilvac.proposta_app.dtos.PropostaResponseDTO;
import com.matheusilvac.proposta_app.entity.Proposta;

import com.matheusilvac.proposta_app.mapper.PropostaMapper;
import com.matheusilvac.proposta_app.repository.PropostaRepository;
import org.springframework.stereotype.Service;


@Service
public class PropostaService {

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    private final PropostaRepository propostaRepository;



    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }
}
