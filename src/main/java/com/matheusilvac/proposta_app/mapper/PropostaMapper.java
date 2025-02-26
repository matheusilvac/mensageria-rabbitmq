package com.matheusilvac.proposta_app.mapper;

import com.matheusilvac.proposta_app.dtos.PropostaRequestDTO;
import com.matheusilvac.proposta_app.dtos.PropostaResponseDTO;
import com.matheusilvac.proposta_app.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "aprovada", ignore = true )
    @Mapping(target = "observacao", ignore = true )
    Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDTO);


    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    PropostaResponseDTO convertEntityToDto(Proposta proposta);
}
