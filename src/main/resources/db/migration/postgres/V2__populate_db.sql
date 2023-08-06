INSERT INTO users (username, password, role, enabled)
VALUES
('admin', '$2a$10$b0dpvCmBiK0p7WzfhH7oJO8tfph03Tn1mRZoijJ8Ksf7vaJ7GZqAO', 'ROLE_ADMIN', 1);


INSERT INTO note (title, content, access, user_id)
VALUES
	('Title_1', 'Content_1', 'Private', 1),
    ('Title_2', 'Content_2', 'Public', 1),
    ('Title_3', 'Content_3', 'Private', 1),