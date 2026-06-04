resource "aws_dynamodb_table" "tabela_jogadores" {
  name           = "jogadores"
  billing_mode   = "PROVISIONED"
  read_capacity  = 20
  write_capacity = 20
  hash_key       = "id"

  attribute {
    name = "id"
    type = "S"
  }

  tags = {
    Name        = "tabela_jogadores"
    App         = "torneio-api"
    Repository  = "https://github.com/Lechenco/torneio-api"
    Environment = "production"
  }
}

resource "aws_dynamodb_table" "tabela_torneios" {
  name           = "torneios"
  billing_mode   = "PROVISIONED"
  read_capacity  = 20
  write_capacity = 20
  hash_key       = "id"
  range_key      = "nome"

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "nome"
    type = "S"
  }

  tags = {
    Name        = "tabela_torneios"
    App         = "torneio-api"
    Repository  = "https://github.com/Lechenco/torneio-api"
    Environment = "production"
  }
}

resource "aws_dynamodb_table" "tabela_partidas" {
  name           = "partidas"
  billing_mode   = "PROVISIONED"
  read_capacity  = 20
  write_capacity = 20
  hash_key       = "id"
  range_key      = "id_torneio"

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "id_torneio"
    type = "S"
  }

  tags = {
    Name        = "tabela_partidas"
    App         = "torneio-api"
    Repository  = "https://github.com/Lechenco/torneio-api"
    Environment = "production"
  }
}
