package io.github.lechenco.tier.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lechenco.tier.domain.jogador.JogadorDTO;
import io.github.lechenco.tier.infraestructure.mappers.JogadorMappers;
import io.github.lechenco.tier.services.jogadores.JogadoresService;
import jakarta.validation.Valid;

/**
 * JogadorController
 */
@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    private static final Logger logger = LoggerFactory.getLogger(JogadorController.class);
    private final JogadoresService jogadoresService;
    private final JogadorMappers mapper;

    JogadorController(JogadoresService jogadoresService,
            JogadorMappers mapper) {
        this.jogadoresService = jogadoresService;
        this.mapper = mapper;
    }

    @GetMapping("/{idJogador}")
    public ResponseEntity<JogadorDTO> getJogador(
            @PathVariable("idJogador") String idJogador) {
        try {
            JogadorDTO response = mapper.toResponseDTO(jogadoresService.getjogador(idJogador));

            if (response == null) {
                return ResponseEntity.status(404).body(null);
            }

            return ResponseEntity.status(200)
                    .body(response);
        } catch (Exception e) {
            logger.error("Erro ao recuperar o jogador", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<JogadorDTO> saveJogador(
            @Valid @RequestBody JogadorDTO requestDTO) {
        try {
            logger.info("POST '/jogadores' recebido. {}", requestDTO);
            JogadorDTO response = mapper.toResponseDTO(jogadoresService.save(mapper.toJogadorDynamo(requestDTO)));
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            logger.error("Erro ao salvar jogador", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{idJogador}")
    public ResponseEntity<JogadorDTO> alteraJogador(
            @Valid @RequestBody JogadorDTO requestDTO,
            @PathVariable("idJogador") String idJogador) {
        try {
            JogadorDTO response = mapper.toResponseDTO(
                    jogadoresService.updateJogador(mapper.toJogadorDynamo(
                            new JogadorDTO(idJogador, requestDTO.nome()))));
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            logger.error("Erro ao atualizar jogador", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<JogadorDTO>> recuperaTodosJogadores() {
        try {
            List<JogadorDTO> jogadores = jogadoresService.getAllJogadores()
                    .stream().map(mapper::toResponseDTO).toList();
            return ResponseEntity.status(200).body(jogadores);
        } catch (Exception e) {
            logger.error("Erro ao recuperar todos os jogadores", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{idJogador}")
    public ResponseEntity<String> deletaJogador(
            @PathVariable("idJogador") String idJogador) {
        try {
            jogadoresService.deletaJogador(idJogador);
            return ResponseEntity.status(200).body("Deletado");
        } catch (Exception e) {
            logger.error("Erro ao recuperar todos os jogadores", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
