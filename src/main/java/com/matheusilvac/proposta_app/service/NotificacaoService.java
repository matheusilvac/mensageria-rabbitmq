package com.matheusilvac.proposta_app.service;

import com.matheusilvac.proposta_app.dtos.PropostaResponseDTO;
import com.matheusilvac.proposta_app.entity.Proposta;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
