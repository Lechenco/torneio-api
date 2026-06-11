package io.github.lechenco.tier.services.jogadores;

import java.util.List;

import io.github.lechenco.tier.infraestructure.exceptions.NotFoundException;

public interface JogadoresService {
    Jogador save(Jogador jogador);

    List<Jogador> getAllJogadores();

    Jogador getjogador(String id) throws NotFoundException;

    Jogador updateJogador(Jogador jogador);

    void deletaJogador(String idJogador);
}
