INSERT INTO pessoa (id, nome, email, data_nascimento, genero)
VALUES (1, 'Alex', 'alex@teste.com', '2000-01-01', 'MASCULINO');

INSERT INTO endereco (id, cep, logradouro, numero, bairro, cidade, estado, complemento, pessoa_id)
VALUES (1, '01000-000', 'Rua Teste', '123', 'Bairro Teste', 'Cidade Teste', 'Estado Teste', 'Complemento Teste', 1);
