INSERT INTO users(user_id, name, cpf, email, password)
VALUES (1, 'gabriel', '78754715083', 'email@teste.com', '$2a$10$0Sfen0SsPOmQNO0F534ic.pZisFzFxadxg9lrYdOnuun3WNzptumq');

INSERT INTO roles(role_id, role_name) VALUES (2, 'ADMIN');
INSERT INTO roles(role_id, role_name) VALUES (3, 'MORADOR');

INSERT INTO user_role(user_id, role_id) VALUES (1, 2);