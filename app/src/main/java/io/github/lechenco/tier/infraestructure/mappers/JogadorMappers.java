package io.github.lechenco.tier.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import io.github.lechenco.tier.domain.jogador.JogadorDTO;
import io.github.lechenco.tier.services.jogadores.Jogador;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface JogadorMappers {
    JogadorDTO toResponseDTO(Jogador jogador);

    Jogador toJogadorDynamo(JogadorDTO dto);
}
