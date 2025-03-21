package com.matheusilvac.proposta_app.agendador;

import com.matheusilvac.proposta_app.entity.Proposta;
import com.matheusilvac.proposta_app.repository.PropostaRepository;
import com.matheusilvac.proposta_app.service.NotificacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {
    private PropostaRepository propostaRepository;
    private NotificacaoService notificacaoService;
    private String exchange;

    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(PropostaRepository propostaRepository,
                                 NotificacaoService notificacaoService,
                                 @Value("${rabbitmq.propostapendente.ex}")String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10,timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(p -> {
            try {
                notificacaoService.notificar(p, exchange);
                atualizarProposta(p);
            } catch (RuntimeException e) {
               logger.error(e.getMessage());
            }

        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }


}
