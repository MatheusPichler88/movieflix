CREATE TABLE filme (
    id serial PRIMARY KEY,
    titulo varchar(255) NOT NULL,
    descricao text,
    data_lancamento date,
    avaliacao numeric,
    criado_em timestamp DEFAULT current_timestamp,
    atualizado_em timestamp DEFAULT current_timestamp
);