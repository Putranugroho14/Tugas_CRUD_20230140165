## Desain Database (Table: `KTP`)
- `id` (int, Primary Key, Auto Increment)
- `nomor_ktp` (varchar, Unique)
- `nama_lengkap` (varchar)
- `alamat` (varchar)
- `tanggal_lahir` (date)
- `jenis_kelamin` (varchar)

## Dokumentasi API

Base URL = `http://localhost:8080`

### 1. Menambahkan Data KTP Baru
- **URL:** `/ktp`
- **Method:** `POST`
- **Body:** `application/json`
  ```json
  {
      "nomorKtp": "3171234567890001",
      "namaLengkap": "John Doe",
      "alamat": "Jl. Kemerdekaan No.1",
      "tanggalLahir": "1990-01-01",
      "jenisKelamin": "Laki-laki"
  }
  ```
- **Response Success:** `200 OK`
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil ditambahkan",
      "data": { ... }
  }
  ```

### 2. Mengambil Seluruh Data KTP
- **URL:** `/ktp`
- **Method:** `GET`
- **Response Success:** `200 OK`
  ```json
  {
      "status": "success",
      "message": "Berhasil mengambil data",
      "data": [ ... ]
  }
  ```

### 3. Mengambil Data KTP Berdasarkan ID
- **URL:** `/ktp/{id}`
- **Method:** `GET`
- **Response Success:** `200 OK`
  ```json
  {
      "status": "success",
      "message": "Berhasil mengambil data {id}",
      "data": { ... }
  }
  ```

### 4. Memperbarui Data KTP Berdasarkan ID
- **URL:** `/ktp/{id}`
- **Method:** `PUT`
- **Body:** `application/json` (semua field wajib diisi sesuai kontrak Data Validasi)
  ```json
  {
      "nomorKtp": "3171234567890001",
      "namaLengkap": "John Doe Updated",
      "alamat": "Jl. Kemerdekaan No.1 Lt 2",
      "tanggalLahir": "1990-01-01",
      "jenisKelamin": "Laki-laki"
  }
  ```
- **Response Success:** `200 OK`
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil diperbarui",
      "data": { ... }
  }
  ```

### 5. Menghapus Data KTP Berdasarkan ID
- **URL:** `/ktp/{id}`
- **Method:** `DELETE`
- **Response Success:** `200 OK`
  ```json
  {
      "status": "success",
      "message": "Data KTP berhasil dihapus",
      "data": "OK"
  }
  ```

## Screenshot Tampilan Website
<img width="1919" height="967" alt="image" src="https://github.com/user-attachments/assets/0774eb06-d6dd-4f7c-bde0-8757c733fcb7" />


