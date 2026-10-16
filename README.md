# TP5DPBO2526C1
Tugas Praktikum 5 DPBO 2026

Janji Saya Bozorov Huseyn (NIM: 2521812) mengerjakan evaluasi Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berbasis Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

Aplikasi desktop Java Swing untuk mengelola data orang, terhubung dengan database MySQL (CRUD).

## Desain Program

- **Person.java**: class model (`id`, `nama`, `tahunLahir`, `kategori`, `kota`).
- **Database.java**: koneksi JDBC ke MySQL (`db_people`), berisi `selectQuery()` untuk SELECT dan `insertUpdateDeleteQuery()` untuk INSERT/UPDATE/DELETE.
- **PeopleMenu.java**: logika GUI dan CRUD. Tidak lagi memakai `ArrayList`, semua data dibaca dan ditulis langsung ke database.
- **PeopleMenu.form**: desain GUI.
- **db_people.sql**: struktur tabel `person` dan data awal.

Validasi:
- Semua kolom harus diisi saat insert/update.
- ID tidak boleh sama saat insert.
- Tahun lahir harus berupa angka.
- Konfirmasi Yes/No sebelum delete.

## Cara Menjalankan

1. Jalankan MySQL (XAMPP) dan import `db_people.sql`.
2. Pastikan `lib/mysql-connector-j-26.7.0.jar` sudah ditambahkan sebagai dependency.
3. Jalankan `PeopleMenu.java`.

## Penjelasan Alur

1. Saat program dibuka, `Database` membuat koneksi ke MySQL dan `setTable()` menjalankan `SELECT * FROM person` untuk mengisi tabel.
2. **Create**: isi form, tekan **Add**. Program memeriksa kolom kosong dan ID ganda, lalu menjalankan `INSERT`. Tabel dimuat ulang dari database.
3. **Read**: tabel selalu diisi dari hasil `SELECT`. Saat baris diklik, nilainya diambil dari model tabel dan dimasukkan ke form, tombol berubah menjadi **Update**, tombol **Delete** muncul.
4. **Update**: ubah data, tekan **Update**. Program menjalankan `UPDATE ... WHERE id = ...`. Kolom ID dikunci saat update.
5. **Delete**: tekan **Delete**, konfirmasi **Yes**, program menjalankan `DELETE ... WHERE id = ...`.
6. **Cancel**: mengosongkan form.

## Dokumentasi

[Video Dokumentasi](PeopleData/Dokumentasi/Screen Recording tp5.mov) ⁠
