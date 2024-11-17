-- Insertar ROLE_USER solo si no existe
INSERT INTO role (role_name, description)
SELECT 'ROLE_USER', 'Rol de Usuario por defecto.'
    WHERE NOT EXISTS (SELECT 1 FROM role WHERE role_name = 'ROLE_USER');

-- Insertar ROLE_ADMIN solo si no existe
INSERT INTO role (role_name, description)
SELECT 'ROLE_ADMIN', 'Este rol es para un administrador de la aplicación BestPrice.'
    WHERE NOT EXISTS (SELECT 1 FROM role WHERE role_name = 'ROLE_ADMIN');


INSERT INTO user_app (names, surnames, phone_number, email, gender, username, password, image_path, roleID)
SELECT 'Juan', 'Jose', '985147853', 'juanjose@email.com', 'M', 'juanjose', '$2a$12$FUbssX37xhIVLdlj.wtwOe8Fwrl1ODNQFgAa6bsnAqEUvsOgtfcNa','default.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM user_app WHERE username = 'juanjose');

INSERT INTO user_app (names, surnames, phone_number, email, gender, username, password, image_path, roleID)
SELECT 'Pepito', 'Pérez', '985621478', 'pepito.perez@email.com', 'M', 'pepito.perez', '$2a$12$cw9VOusAUJlwgd5k3bce5eTMIEgbi.wqanCvPUWg6NJ4/C34A4r4K', 'default.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM user_app WHERE username = 'pepito.perez');