
CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);

INSERT INTO courses (code, name, credits) VALUES ('CS101', 'Dasar Pemrograman', 3);
INSERT INTO courses (code, name, credits) VALUES ('CS102', 'Struktur Data', 3);
INSERT INTO courses (code, name, credits) VALUES ('CS103', 'Basis Data', 3);
INSERT INTO courses (code, name, credits) VALUES ('CS104', 'Sistem Operasi', 3);
INSERT INTO courses (code, name, credits) VALUES ('CS105', 'Jaringan Komputer', 2);

