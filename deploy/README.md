# Panduan Deployment

Dokumen ini menjelaskan prosedur _deployment_ aplikasi dan _reverse proxy_
dari proyek `demo`.

## Daftar Isi

1. [Informasi Lingkungan Deployment](#informasi-lingkungan-deployment)
1. [Gambaran Umum Proses Deployment](#gambaran-umum-proses-deployment)
1. [Panduan Deployment](#panduan-deployment)

## Informasi Lingkungan Deployment

Setiap peserta memiliki sebuah _virtual machine_ (VM) `f1-micro` di Google
Cloud Platform (GCP) dengan kapasitas penyimpanan data sebesar 10 GB. VM
menggunakan sistem operasi Ubuntu 20.04. VM dapat diakses melalui protokol
SSH di _port_ `22` dengan otentikasi _Public-Private Key_.

Spesifikasi VM tipe `f1-micro` di GCP adalah sebagai berikut:

- 1 vCPU (virtual CPU), shared core.
- 614 MB memory.

> Note: Pastikan Anda telah mendaftarkan _public key_ milik pribadi untuk
> keperluan pelatihan ini sebelum mencoba akses ke VM.

Untuk dapat mengakses VM, gunakan program SSH di sistem operasi yang digunakan.
Sistem operasi modern (contoh: distro GNU/Linux apapun, Windows 10 dengan fitur
OpenSSH) saat ini biasanya telah memiliki program SSH dalam bentuk perintah
_command-line interface_ (CLI) bernama `ssh`. Contoh pemanggilan `ssh` untuk
mengakses VM:

```shell
$ ssh -p 22 -i <lokasi berkas private key> <username>@<alamat IP eksternal VM>
```

Misalkan ada peserta bernama Maria Garcia memilki VM di alamat IP eksternal
`34.83.145.77`. Maria Garcia juga menyimpan _private key_ di dalam direktori
`~/.ssh/id_ed25519` komputer pribadinya. Maka contoh pemanggilan perintah `ssh`
yang dapat digunakan adalah sebagai berikut:

```shell
$ ssh -p 22 -i ~/.ssh/id_ed25519 maria_garcia@34.83.145.77
```

Akun peserta di VM memiliki hak akses setara `root` melalui mekanisme `sudo`.
Silakan gunakan hak akses tersebut secara bertanggung jawab.
Hak akses `root` melalui `sudo` akan dibutuhkan untuk kebutuhan-kebututuhan
seperti:

- Memasang _system package_ baru.
- Mengubah konfigurasi _reverse proxy_.
- Menempatkan aplikasi ke folder _deployment_.

Untuk keperluan penyimpanan data, tersedia juga _database_ relasional
PostgreSQL 11 di dalam zona yang sama dengan VM. _Database_ dapat diakses
melalui IP privat dengan alamat `10.13.32.5` (silakan cek alamat terkini di
LMS atau GitLab apabila ternyata berbeda). Masing-masing peserta memiliki
sebuah _user_ dan _database_ dengan nama yang sama persis dengan _username_
di VM. Informasi _username_ dan _password_ dapat dilihat di LMS.

Contoh perintah untuk mengakses _database_ menggunakan _client_ `psql` di VM:

```shell
$ psql -U <username> -h 10.13.32.5
Password for user <username>: <masukkan password>
...
```

Berhubung _database_ berada di jaringan privat, maka akses ke _database_ hanya
bisa dilakukan dari  dalam VM di GCP. Alternatif lain adalah menggunakan SSH
_tunneling_ dari komputer pribadi ke _database_ melalui VM. Silakan pelajari
panduan SSH untuk mengetahui bagaimana caranya membuat SSH _tunnel_.

Saat ini di VM telah terpasang:

- `nginx` sebagai _reverse proxy_.
- OpenJDK versi 11 untuk menjalankan aplikasi.
- `psql` sebagai _client_ untuk komunikasi ke _database_ PostgreSQL melalui
  _command-line interface_ (CLI).

## Gambaran Umum Proses Deployment

Aplikasi `demo` akan ditempatkan ke folder `/opt/sinarmas/demo` di VM. Folder
tersebut akan berisi:

- Berkas JAR hasil _build_ aplikasi `demo` dari mesin pengembangan atau artifak
  proses _Continuous Integration_ (CI).
- Berkas konfigurasi aplikasi (`application.properties`).

Berkas JAR dapat disalin ke VM menggunakan `sftp`. Kemudian berkas konfigurasi
bisa dipersiapkan secara lokal atau langsung di VM menggunakan _text editor_
yang tersedia (misal: `vim`, `nano`).

Selain aplikasi, ada berkas templat konfigurasi _reverse proxy_ `nginx` yang
perlu dipersiapkan dan ditaruh ke folder `/etc/nginx/sites-enabled` di VM.
Berkas templat konfigurasi dapat dilihat di dalam folder [`./nginx`](./nginx/demo.conf.template)
proyek ini.

Setelah aplikasi dan _reverse proxy_ telah dipersiapkan, maka langkah terakhir
pada proses _deployment_ adalah mengatur aplikasi agar dijalankan sebagai
_service_ di VM. Telah tersedia berkas templat konfigurasi `systemd` di dalam
folder [`./systemd`](systemd/demo.template) yang perlu dipersiapkan dan disalin ke VM.

Proses _deployment_ ini bisa diotomasi menggunakan CI. Silakan dieksplorasi
lebih lanjut sebagai bagian dari pembelajaran di kegiatan pelatihan ini.
Berhubung proyek ini disimpan dan dimanajemen melalui GitLab, maka silakan
pelajari cara penggunaan [GitLab CI/CD](https://docs.gitlab.com/ee/ci/).

## Panduan Deployment

Proses _deployment_ dimulai dengan mempersiapkan berkas JAR aplikasi.
Berkas JAR aplikasi dapat dibuat dengan memanggil proses _build_ secara lokal
menggunakan Maven atau diperoleh dari hasil _build_ di CI.

Jika sudah ada berkas JAR, salin berkas JAR ke VM. Gunakan _client_ FTP seperti
`sftp` (berbasis CLI) atau WinSCP (berbasis _desktop_ di Windows). Awalnya,
salin berkas JAR ke folder `$HOME` di VM terlebih dahulu. Berikut ini contoh
penyalinan berkas menggunakan `sftp`:

```shell
$ sftp -P 22 -i <lokasi berkas private key} <username>@<alamat IP eksternal VM>
sftp> PUT <nama berkas JAR>.jar
Uploading <nama berkas JAR> to /home/<username>/<nama berkas JAR>.jar
...
sftp> BYE
```

Setelah menyalin berkas, gunakan SSH untuk melakukan langkah-langkah _deploy_
berikutnya:

1. Salin berkas JAR ke folder _deployment_ di `/opt/sinarmas/demo`:
   
   ```shell
   $ mkdir -p /opt/sinarmas/demo
   $ cp <nama berkas JAR>.jar /opt/sinarmas/demo/<nama berkas JAR>.jar
   ```
1. Persiapkan berkas konfigurasi aplikasi (`application.properties`) di folder
   _deployment_ (`/opt/sinarmas/demo`). Gambaran isi `application.properties`
   dapat dilihat di kode sumber aplikasi.
1. Persiapkan konfigurasi _reverse proxy_. Berkas templat sudah tersedia.
   Silakan isi variabel yang dituliskan dalam format kurung kurawal ganda (`{{ }}`)
   dengan nilai yang sesuai. Konfigurasi _reverse proxy_ kemudian ditaruh ke
   folder `/etc/nginx/sites-enabled`. Setelah memperbaharui konfigurasi
   _reverse proxy_, cek keabsahan sintaks berkas konfigurasi dan picu
   _reverse proxy_ agar membaca ulang berkas konfigurasi:
   
   ```shell
   $ sudo nginx -t
   $ sudo systemctl reload nginx
   ```
1. Persiapkan konfigurasi `systemd`. Berkas templat sudah tersedia. Pembuatan
   berkas konfigurasi mirip dengan konfigurasi _reverse proxy_ di langkah
   sebelumnya. Apabila berkas `systemd` telah siap, salin berkas tersebut ke
   folder `/etc/systemd/system/<nama berkas systemd>` dan picu `systemd` untuk
   membaca berkas konfigurasi terkini:
   
   ```shell
   $ sudo systemctl daemon-reload
   $ sudo systemctl stop <nama berkas systemd>
   $ sudo systemctl start <nama berkas systemd>
   ```
1. Cek apakah aplikasi berhasil jalan sebagai _service_:
   
   ```shell
   $ sudo systemctl status <nama berkas systemd>
   ```
1. Uji coba apakah aplikasi bisa diakses dari komputer pribadi melalui Web.
   Bisa menggunakan _browser_ atau `curl` di CLI. Contoh `curl`:
   
   ```shell
   $ curl -v http://<alamat IP publik VM>:<port aplikasi>
   ```
