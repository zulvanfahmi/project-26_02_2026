INSERT INTO master_roles (role_code, role_name, created_by) VALUES
('ADMIN', 'Administrator', 1),
('USER', 'Regular User', 1);

INSERT INTO master_ticket_status (status_code, status_name, created_by) VALUES
('OPEN', 'Open', 1),
('IN_PROGRESS', 'In Progress', 1),
('DONE', 'Completed', 1),
('CANCELLED', 'Cancelled', 1);

INSERT INTO master_ticket_priority (priority_code, priority_name, created_by) VALUES
('LOW', 'Low', 1),
('MEDIUM', 'Medium', 1),
('HIGH', 'High', 1);

INSERT INTO m_user (fullname, username, email, password, id_role, created_by) VALUES
('Edo', 'edo_user', 'edo@mail.com', '$2a$10$XMoLSaxMKS8UaxF6FX1hQu8fgeGPkcOqMHn0XuUtvi/YIbqSS.4oC', 1, 1),
('Andi', 'andi_user', 'andi@mail.com', '$2a$10$XMoLSaxMKS8UaxF6FX1hQu8fgeGPkcOqMHn0XuUtvi/YIbqSS.4oC', 2, 1),
('Budi', 'budi_user', 'budi@mail.com', '$2a$10$XMoLSaxMKS8UaxF6FX1hQu8fgeGPkcOqMHn0XuUtvi/YIbqSS.4oC', 2, 1),
('Siti', 'siti_user', 'siti@mail.com', '$2a$10$XMoLSaxMKS8UaxF6FX1hQu8fgeGPkcOqMHn0XuUtvi/YIbqSS.4oC', 2, 1);

INSERT INTO ticket (title, description, id_status, id_priority, created_by, assigned_by, assigned_to) 
VALUES
('Login Bug', 'Cannot login using valid credentials', 1, 3, 1, 1, 2),
('API Timeout', 'Payment API takes too long to respond', 2, 2, 2, 1, 3),
('UI Misalignment', 'Button not aligned properly', 3, 1, 3, 1, 4),
('Database Error', 'Error when saving transaction', 1, 3, 1, NULL, NULL),
('Report Feature', 'Need export to PDF', 2, 2, 4, 1, 2);

INSERT INTO ticket_comment (id_ticket, created_by, comment) VALUES
(1, 2, 'I will check this issue today'),
(2, 1, 'Please prioritize this bug'),
(3, 2, 'Investigating API logs'),
(4, 3, 'Fix deployed to staging'),
(5, 4, 'Working on PDF export library');

CREATE INDEX idx_token_blacklist_token ON token_blacklist(token);