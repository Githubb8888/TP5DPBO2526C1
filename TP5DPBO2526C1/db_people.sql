CREATE DATABASE IF NOT EXISTS db_people;
USE db_people;

CREATE TABLE person (
  id VARCHAR(20) NOT NULL,
  nama VARCHAR(100) NOT NULL,
  tahun_lahir INT NOT NULL,
  kategori VARCHAR(50) NOT NULL,
  kota VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO person (id, nama, tahun_lahir, kategori, kota) VALUES
('12345', 'Husein', 2007, 'Mahasiswa', 'Bandung'),
('P001', 'Budi Santoso', 2003, 'Mahasiswa', 'Bandung'),
('P002', 'Siti Aminah', 1985, 'Dosen', 'Jakarta'),
('P003', 'Rian Pratama', 1995, 'Staf', 'Surabaya'),
('P004', 'Dewi Lestari', 2001, 'Alumni', 'Yogyakarta');
