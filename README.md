# Sample MVC & REST API

[![pipeline status](https://gitlab.com/sekodingpusilkom/sample-mvc-rest-api/badges/main/pipeline.svg)](https://gitlab.com/sekodingpusilkom/sample-mvc-rest-api/-/commits/main)
[![coverage report](https://gitlab.com/sekodingpusilkom/sample-mvc-rest-api/badges/main/coverage.svg)](https://gitlab.com/sekodingpusilkom/sample-mvc-rest-api/-/commits/main)

Proyek ini adalah aplikasi "Hello, World" sederhana yang dikembangkan
menggunakan _framework_ Spring Boot.

## Daftar Isi

1. [Prasyarat Lingkungan Pengembangan Lokal](#prasyarat-lingkungan-pengembangan-lokal)
1. [Pengembangan dan Uji Coba Lokal](#pengembangan-dan-uji-coba-lokal)
1. [Alur Kerja Pengembangan & Kontribusi](#alur-kerja-pengembangankontribusi) 
1. [Alur Kerja Deployment](#alur-kerja-deployment)
1. [Lisensi dan Hak Cipta](#lisensi-dan-hak-cipta)

## Prasyarat Lingkungan Pengembangan Lokal

Sebelum dapat memulai pengembangan dan uji coba proyek ini, harap persiapkan
hal-hal berikut:

- _Database_ PostgreSQL versi 11.
  - Silakan ikuti instruksi instalasi [PostgreSQL](https://www.postgresql.org/)
    sesuai sistem operasi yang digunakan.
- OpenJDK versi 11.
  - Pastikan untuk memasang **JDK**, bukan **JRE**. JRE hanya mengandung _runtime_
    Java (JVM). Sedangkan untuk pengembangan, Anda akan membutuhkan _compiler_
    dan _runtime_ Java.
- _Integrated Development Environment_ (IDE) atau _text editor_ favorit Anda.
  Disarankan menggunakan IDE [JetBrains IntelliJ](https://www.jetbrains.com/idea/download/)
  versi Community.
  
Spesifikasi lingkungan _deployment_ bisa dilihat di bagian [Deployment](#deployment).
Sangat disarankan untuk membuat lingkungan pengembangan dan _deployment_
semirip mungkin agar proses _deployment_ dapat berjalan dengan lancar.

## Pengembangan dan Uji Coba Lokal

1. Buat _fork_ repositori proyek ini melalui tampilan GitLab. Silakan klik
   tombol **Fork** di laman utama proyek ini lalu tempatkan _fork_ di dalam
   _namespace_ akun GitLab Anda.
1. Buka laman _fork_ proyek ini yang telah Anda buat.
1. Buat salinan (_clone_) kode sumber proyek dari repositori versi _fork_
   menggunakan Git. Tautan URL _clone_ bisa dilihat melalui tombol **Clone**
   di laman versi _fork_ proyek ini di GitLab. Contoh:

   ```shell
   $ git clone <URL clone> <tujuan clone di filesystem komputer pribadi>
   ```
1. Masuk ke folder hasil _clone_ kode sumber proyek menggunakan _shell_ sistem
   operasi yang dipakai.
   
   ```shell
   $ cd <lokasi hasil clone di filesystem komputer pribadi>
   ```
   
   > Catatan: _Shell_ biasanya merujuk pada aplikasi _terminal_ atau
   > _command prompt_ di sistem operasi. Pengguna Windows dapat menggunakan
   > PowerShell (`pwsh`) atau Command Prompt (`cmd`). Pengguna Mac OS atau
   > distro GNU/Linux seperti Ubuntu, Kali Linux, Arch Linux bisa memakai
   > _shell_ `bash`.
1. Buka folder hasil _clone_ dengan IDE atau _text editor_ favorit Anda.
   Kemudian buat berkas konfigurasi Spring Profile baru bernama `application-local.properties`
   di folder [`./src/main/resources`](./src/main/resources). Isi berkas
   tersebut dengan nilai konfigurasi _data source_ yang sesuai agar bisa
   menyambungkan aplikasi ke _database_ PostgreSQL lokal.
1. Untuk menjalankan _test suite_, panggil perintah Maven berikut:

   ```shell
   $ mvn test -Dspring.profiles.active=local
   ```

   > Catatan: Apabila menggunakan _shell_ PowerShell di sistem operasi Windows,
   > maka nilai pada opsi `-D` harus dikutip dalam sepasang tanda kutip (`"`).
   > Contoh: `mvn -D"spring.profiles.active=local" test`

1. Untuk menjalankan aplikasi, panggil perintah Maven berikut:

   ```shell
   $ mvn spring-boot:run -Dspring-boot.run.profiles=local
   ```

   Kemudian aplikasi dapat diuji coba menggunakan _browser_ di alamat
   `http://localhost:8080` (jika konfigurasi masih _default_).

   > Catatan: Perhatikan bahwa nama parameter untuk menggunakan _profile_
   > berbeda pada pemanggilan _test suite_ dan aplikasi. Pastikan kembali
   > sebelum memanggil perintah Maven.
   
## Alur Kerja Pengembangan/Kontribusi

Silakan lihat dokumen terkait di berkas ['./CONTRIBUTING.md`](./CONTRIBUTING.md).

## Alur Kerja Deployment

Silakan lihat dokumen terkait di folder [`./deploy`](./deploy/README.md).

## Lisensi dan Hak Cipta

Kode sumber ini dapat disalin, dimodifikasi, ataupun dibagi secara terbuka
selama mengikuti syarat dan ketentuan yang berlaku dalam lisensi [MIT](./LICENSE.md).