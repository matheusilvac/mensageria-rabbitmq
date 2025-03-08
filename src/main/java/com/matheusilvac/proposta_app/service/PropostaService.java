package com.matheusilvac.proposta_app.service;

import com.matheusilvac.proposta_app.dtos.PropostaRequestDTO;
import com.matheusilvac.proposta_app.dtos.PropostaResponseDTO;
import com.matheusilvac.proposta_app.entity.Proposta;

import com.matheusilvac.proposta_app.mapper.PropostaMapper;
import com.matheusilvac.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;

    private final PropostaMapper propostaMapper;

    public Page<PropostaResponseDTO> obterTodos(Pageable paginacao){
        return propostaRepository.findAll(paginacao);
    }

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        Proposta proposta = propostaMapper.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);
        return propostaMapper.convertEntityToDto(proposta);
    }
}
