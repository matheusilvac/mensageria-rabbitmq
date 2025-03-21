package com.matheusilvac.proposta_app.service;

import com.matheusilvac.proposta_app.dtos.PropostaRequestDTO;
import com.matheusilvac.proposta_app.dtos.PropostaResponseDTO;
import com.matheusilvac.proposta_app.entity.Proposta;

import com.matheusilvac.proposta_app.mapper.PropostaMapper;
import com.matheusilvac.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    private final PropostaMapper propostaMapper;

    private NotificacaoService notificacaoService;

    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           PropostaMapper propostaMapper,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.ex}")String exchange) {
        this.propostaRepository = propostaRepository;
        this.propostaMapper = propostaMapper;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }


    public List<PropostaResponseDTO> obterTodos(){
      return propostaMapper.convertListEntityToListDto(propostaRepository.findAll());
    }

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        Proposta proposta = propostaMapper.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);
        notificarRabbitMQ(proposta);
        return propostaMapper.convertEntityToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }
}
