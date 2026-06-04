# Torneio API

Aplicação Spring Boot que administra e consulta informações sobre partidas de torneio.

## Modelagem

### Jogador

```json
{
  "id": "<UUID>",
  "nome": "Mário"
}
```

### Torneio

```json
{
  "id": "<UUID>",
  "nome": "torneio semanal",
  "tipo": "suíco",
  "numero_de_jogos": 3,
  "numero_jogadores": 12,
  "numero_de_partidas": 4
}
```

### Partida

```json
{
  "id": "<UUID>",
  "id_torneio": "<UIID>",
  "partida_do_torneio": 2,
  "id_jogador_1": "<UUID>",
  "id_jogador_2": "<UUID>",
  "vitorias_j1": 1,
  "vitorias_j2": 1,
  "empates": 1
}
```
