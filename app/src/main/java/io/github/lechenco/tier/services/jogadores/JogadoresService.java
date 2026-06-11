package io.github.lechenco.tier.services.jogadores;

import java.util.List;

public interface JogadoresService {
    Jogador save(Jogador jogador);

    List<Jogador> getAllJogadores();

    Jogador getjogador(String id);

    Jogador updateJogador(Jogador jogador);

    void deletaJogador(String idJogador);
}
