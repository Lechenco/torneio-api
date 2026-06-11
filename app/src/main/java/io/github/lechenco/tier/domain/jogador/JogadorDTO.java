package io.github.lechenco.tier.domain.jogador;

import jakarta.validation.constraints.NotBlank;

public record JogadorDTO(
        String id,
        @NotBlank(message = "Nome deve estar preenchido") String nome) {
}
