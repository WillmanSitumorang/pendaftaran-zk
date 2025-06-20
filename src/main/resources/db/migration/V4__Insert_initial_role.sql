-- Tabel ROLE
CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabel STUDENTS
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    address VARCHAR(255) NOT NULL,
    jurusan VARCHAR(100) NOT NULL
);

-- Tabel USERS
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    student_id INTEGER,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Tabel USER_ROLES
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- === INSERT DATA ===

-- Role Admin dan User
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

-- Tambah user admin dan user biasa
INSERT INTO users (id, username, password) VALUES
(1, 'admin', '$2a$10$oRm5ddTjHMhNsi.NVKRLQOiUXz6qs1zPUhkZGd5TM/7Y0F/MLkBae'), -- password: admin
(2, 'user', '$2a$10$4koVDNa1I8P6u2LqN4fx5e1hSgdkabthueqrWpTJGi9GFIIDUD4AC')   -- password: user
ON CONFLICT DO NOTHING;

-- Hubungkan user dengan role
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1) ON CONFLICT DO NOTHING; -- admin → ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2) ON CONFLICT DO NOTHING; -- admin → ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1) ON CONFLICT DO NOTHING; -- user → ROLE_USER
