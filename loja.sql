create clientes_db;

USE clientes_db;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    escola VARCHAR(100) NOT NULL,
    valor DOUBLE NOT NULL
    );

