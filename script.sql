CREATE DATABASE pessoas_db;

-- Criando o esquema
CREATE SCHEMA IF NOT EXISTS sinergi;

CREATE TABLE sinergi.Pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    sexo VARCHAR(1) NOT NULL
);

CREATE TABLE sinergi.Endereco (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER REFERENCES sinergi.Pessoa(id) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10),
    cep VARCHAR(10) NOT NULL
);