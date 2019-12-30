CREATE TABLE todo (
    id bigserial not null primary key,
    email varchar(255),
    descricao varchar(500),
    done bool,
    categoria varchar(20) check (categoria in ('TRABALHO', 'ESTUDOS', 'PESSOAL', 'OUTROS') )
)

