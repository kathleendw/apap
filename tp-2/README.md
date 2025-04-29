# Tutorial APAP

## Authors

* **Kathleen Daniella Wijaya** - *2106637366* - *A* 

---
## Tutorial 1

### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda juga boleh menambahkan catatan apapun di bagian ini)

### GitLab

##### 1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue Tracker adalah salah satu fitur Gitlab yang digunakan untuk mengelola dan melacak masalah, tugas, perbaikan, dan permintaan fitur dalam suatu proyek. Fitur ini memungkinkan tim untuk bekerja sama secara lebih terstruktur dan merencanakan pengembangan proyek secara lebih efektif. Beberapa masalah yang dapat diselesaikan dengan Issue Tracker adalah (1) melaporkan dan melacak bug, (2) mengusulkan, mendiskusikan, dan mengelola perbaikan secara kolaboratif, (3) mengajukan maupun mengimplementasikan permintaan fitur baru, (4) mengelola tugas non-teknis, (5) mengelola prioritas dan menetapkan tenggat waktu untuk masalah dan tugas, serta (6) melakukan dokumentasi terkait aktivitas yang telah dilakukan.

##### 2. Apa perbedaan dari git merge dan git merge --squash?
Kedua command tersebut berfungsi untuk menggabungkan perubahan dari satu branch ke branch lainnya. Akan tetapi, ketika menjalankan command "git merge", seluruh sejarah commit akan digabungkan dari branch sumber ke branch tujuan sehingga sejarah commit yang lengkap akan ada dalam sejarah branch tujuan. Sementara itu, command "git merge --squash" akan menciptakan satu commit baru yang mewakili seluruh perubahan dalam branch sumber sehingga sejarah commit dari branch sumber tidak akan ada dalam sejarah branch tujuan. Command "git merge" akan berguna apabila ingin melacak perubahan secara detil, sedangkan command "git merge --squash" akan berguna apabila terdapat commit yang tidak berguna sehingga ingin disatukan.

##### 3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?
Beberapa keunggulan dalam menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi adalah (1) memungkinkan kita untuk melacak perubahan dan kembali ke versi yang kita inginkan jika diperlukan, (2) bekerja pada proyek secara kolaboratif, (3) membuat branch untuk mengembangkan fitur atau memperbaiki bug tanpa mengganggu kode utama, (4) melakukan dokumentasi sehingga dapat dengan mudah memahami sejarah pengembangan proyek, (5) memberikan kontrol akses yang ketat pada repositori, dan (6) dapat digunakan untuk proyek kecil hingga proyek besar.

### Spring

##### 4. Apa itu library & dependency?
Library adalah kumpulan kode yang dapat digunakan untuk melakukan tugas tertentu dalam sebuah aplikasi. Dalam Spring framework, library termasuk dalam Java Archive (JAR) yang berisi kode Java yang diperlukan untuk menjalankan aplikasi Spring. Misalnya, spring-core, spring-web, spring-data, spring-security, dan lain-lain. Sementara itu, dependency adalah cara kita menyatakan bahwa proyek yang sedang dikerjakan membutuhkan library-library tertentu untuk berjalan. Dalam Spring framework, dependency didefinisikan terhadap library-library yang diperlukan oleh aplikasi. Misalnya jika menggunakan Maven, dependency akan didefinisikan pada 'pom.xml' lalu Maven akan mengunduh dan menyinkronkan library-library tersebut ke dalam proyek secara otomatis.

##### 5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
Maven adalah salah satu perangkat lunak manajemen proyek yang digunakan dalam pengembangan perangkat lunak berbasis Java. Kita menggunakan Maven karena Maven dapat membantu dalam mengelola siklus hidup proyek, dependency, pembangunan proyek, dan lain sebagainya. Maven memungkinkan kita mendefinisikan dependencies dengan mudah pada file 'pom.xml' lalu secara otomatis mengunduh library-library tersebut. Maven juga mendefinisikan siklus hidup proyek standar yang terdiri dari beberapa fase seperti kompilasi, pengujian, paket, dan lain-lain. Selain itu, Maven menyediakan pola direktori proyek yang baku dan standar sehingga proyek terstruktur dan mudah dipahami. Terdapat beberapa alternatif lain dari Maven, seperti Gradle, Ant, dan Ivy.

##### 6. Jelaskan bagaimana alur ketika kita menjalankan http://localhost:8080/celsius-converter/90 sampai dengan muncul keluarannya di browser!
Saat menjalankan http://localhost:8080/celsius-converter/90 di browser, permintaan HTTP akan dikirimkan ke server Spring Boot yang berjalan di localhost dengan port 8080. Spring Boot kemudian akan melakukan routing dengan URL tersebut. Jika sudah menemukan controller yang sesuai, controller tersebut akan menerima input 90 menggunakan @PathVariable, melakukan konversi, dan menghasilkan respon HTML dalam bentuk celsiusConverterPage.html yang akan ditampilkan di browser.

##### 7. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
Selain pengembangan web, Spring framework dapat digunakan untuk berbagai jenis aplikasi seperti aplikasi konsol, aplikasi desktop, aplikasi mobile, dan aplikasi berbasis layanan. Tidak hanya itu, Spring framework juga dapat digunakan untuk integrasi data, pengolahan pesan, serta pengelolaan keamanan aplikasi. 

##### 8. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
@RequestParam dan @PathVariable digunakan untuk mengambil data dari permintaan HTTP. Akan tetapi, @RequestParam digunakan untuk mengambil parameter dari URL yang dikirimkan sebagai string, sedangkan @PathVariable digunakan untuk mengambil data dari path URL. @RequestParam sebaiknya digunakan ketika parameter bersifat opsional, sedangkan @PathVariable sebaiknya digunakan ketika parameter adalah bagian hal yang diperlukan dari URL.

### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat mencentang apabila Anda sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP? 
- [x] Kenapa?
   Karena …

(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu lebih dalam tentang penulisan README.md di GitLab pada link [berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))


---
## Tutorial 2

### What I have learned today

##### 1. Apa itu DTO? Jelaskan kegunaannya pada proyek ini?
DTO, singkatan dari "Data Transfer Object", adalah suatu objek yang digunakan dalam pengembangan perangkat lunak untuk mengirimkan data antara komponen aplikasi. Pada proyek ini, DTO digunakan untuk mengirim data buku ke antarmuka pengguna dan menerima data buku dari antarmuka pengguna. Dengan DTO, kita dapat mengambil data yang diperlukan saja serta melakukan validasi dan pengolahan data sebelum menyimpan data. Secara keseluruhan, penggunaan DTO membantu dalam manajemen data antara berbagai komponen aplikasi untuk memastikan bahwa data yang dikirimkan dan diterima sesuai dengan kebutuhan spesifik.

##### 2. Apa itu UUID? Mengapa UUID digunakan?
UUID, singkatan dari "Universally Unique Identifier", adalah jenis pengidentifikasi unik yang bersifat universal. UUID digunakan dalam pengembangan perangkat lunak untuk memberikan identifikasi yang unik. UUID memberikan jaminan bahwa pengenal (ID) yang dihasilkan akan unik di seluruh dunia. Selain itu, UUID bersifat aman dan dapat digunakan dalam sistem yang terdistribusi dimana suatu entitas perlu menghasilkan ID unik tanpa harus mengecek entitas lain.

##### 3. Pada service, mengapa perlu ada pemisahan antara interface dan implementasinya?
Terdapat beberapa alasan mengapa pemisahan antara interface dan implementasi service perlu dilakukan. Pertama, kita dapat melakukan perubahan pada implementasi service tanpa mempengaruhi komponen aplikasi lainnya. Hal ini memungkinkan kita untuk mengoptimalkan implementasi service dengan mudah. Kedua, kita dapat bekerja pada interface dan implementasinya secara terpisah sehingga dapat dilakukan pada waktu yang bersamaan. Kita juga bisa fokus untuk mendefinisikan interface terlebih dahulu, kemudian fokus pada implementasinya. Ketiga, pemisahan antara interface dan implementasi service membuat kode menjadi lebih terstruktur dan rapi sehingga mudah dipahami dan dikelola.

##### 4. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat.
Anotasi @Autowired pada class Controller merupakan implementasi dari konsep Dependency Injection (DI), yaitu pola desain yang digunakan dalam pengembangan perangkat lunak untuk mengelola dependensi antar komponen. Pada proyek ini, @Autowired digunakan untuk memberikan instance dari service ke controller. Dengan adanya @Autowired, Spring Framework akan mencari instance BukuService yang sudah ada lalu menyuntikkannya ke dalam controller secara otomatis. Jadi, @Autowired memungkinkan kita untuk mendefinisikan dependensi antara komponen-komponen aplikasi tanpa perlu mengelola objek-objek tersebut secara manual.

##### 5. Apa perbedaan @GetMapping dan @PostMapping?
@GetMapping dan @PostMapping keduanya digunakan untuk mengatur pemetaan URL ke metode dalam controller. Akan tetapi, @GetMapping digunakan untuk permintaan HTTP GET, biasanya untuk mengambil data atau melakukan operasi read di server tanpa mengubah data. Sementara itu, @PostMapping digunakan untuk permintaan HTTP POST, biasanya untuk mengirim data ke server guna membuat atau memperbarui data.

##### 6. Jelaskan proses yang terjadi di controller, model, dan service pada proses create buku, mulai dari fungsi formAddBuku hingga pengguna menerima halaman success-create-buku.
- Pengguna menekan button "Tambah Buku" yang mengirimkan permintaan HTTP GET ke URL "buku/create".
- Metode "formAddBuku" pada controller akan dijalankan dimana instance baru dari BukuDTO akan dibuat lalu ditambahkan ke model. Metode ini mengembalikan "form-create-buku" yang akan menampilkan view html tersebut pada antarmuka pengguna.
- Pengguna mengisi form tambah buku yang dibuat pada html "form-create-buku" lalu menekan button "Submit" yang mengirimkan permintaan HTTP POST ke URL "buku/create"
- Metode "addBuku" pada controller akan dijalankan dimana instance bukuDTO yang berisi data yang diisi oleh pengguna akan diterima. Validasi input akan dilakukan oleh service berdasarkan judul dari bukuDTO. 
- Apabila judul buku sudah pernah didaftarkan, maka metode "validasiInput" pada service akan mengembalikan UUID dari buku tersebut. Apabila belum, maka metode akan mengembalikan null.
- Pada metode "addBuku", apabila UUID tidak null, maka buku yang sudah terdaftar akan diakses berdasarkan UUIDnya menggunakan service lalu ditambahkan ke model. Metode "addBuku" mengembalikan "validasi-input" yang akan menampilkan view html tersebut pada antarmuka pengguna. Sedangkan apabila UUID null, maka objek buku yang baru akan dibuat berdasarkan UUID baru yang digenerate dan data dari bukuDTO menggunakan service lalu ditambahkan ke model. Metode "addBuku" mengembalikan "success-create-buku" yang akan menampilkan view html tersebut pada antarmuka pengguna. 

##### 7. Jelaskan mengenai th:object!
"th:object" adalah salah satu atribut yang digunakan dalam template HTML dengan pengolahan pemodelan tampilan Thymeleaf. Atribut tersebut digunakan untuk mengaitkan objek dari model dengan elemen HTML dalam template, yang memungkinkan kita untuk mengakses dan menampilkan data dari objek model tersebut dalam halaman HTML. Contohnya pada proyek ini adalah
`th:object="${bukuDTO}`
Potongan kode tersebut digunakan untuk mengaitkan objek bukuDTO dari model dengan elemen HTML dalam template sehingga kita dapat mengakses data yang diinginkan, seperti judul, penulis, tahun terbit, dan harga buku.

##### 8. Jelaskan mengenai th:field!
"th:field" juga merupakan salah satu atribut yang digunakan dalam template HTML dengan pengolahan pemodelan tampilan Thymeleaf. Atribut tersebut digunakan untuk menghasilkan input HTML yang terkait dengan objek dari model. Contohnya pada proyek ini adalah
`th:field="*{judul}"`
Potongan kode tersebut digunakan untuk menghasilkan input HTML yang sesuai dengan properti "judul" dalam objek model bukuDTO.


---
## Tutorial 3

### What I have learned today

##### 1. Jelaskan apa itu ORM pada spring serta apa fungsi dan kegunaannya?
Object-Relational Mapping (ORM) pada Spring adalah sebuah teknik yang digunakan untuk menghubungkan dan memetakan objek dalam kode pemrograman dengan tabel dalam basis data relasional. Dengan ORM pada Spring, kita dapat berinteraksi dengan basis data relasional menggunakan objek dan bahasa pemrograman Java, bukan dengan SQL, sehingga kode menjadi lebih rapi dan mudah dipahami. ORM juga membantu menjaga konsistensi data antara objek dalam aplikasi dengan tabel dalam basis data. Selain itu, Spring-ORM memiliki pendekatan Inversion of Control (IoC) sehingga application testing lebih mudah dilakukan. Secara singkat, ORM pada Spring membantu kita mengatasi masalah dalam pengembangan aplikasi berbasis basis data relasional, mengurangi kerumitan, dan meningkatkan produktivitas pengembangan.

##### 2. Jelaskan secara singkat apa itu dan kegunaan dari tag-tag dibawah ini.
`(@Entity, @Table, @Column)`

Ketiga tag tersebut merupakan anotasi JPA yang digunakan dalam kerangka kerja Spring untuk menghubungkan objek dalam kode Java dengan tabel basis data relasional. @Entity digunakan untuk menandai sebuah kelas sebagai entitas yang akan dipetakan ke tabel basis data. @Table digunakan untuk menentukan nama tabel, skema, dan konfigurasi lainnya terkait tabel basis data yang sesuai dengan entitas kelas. @Column digunakan untuk menentukan nama kolom, jenis data kolom, dan konfigurasi lainnya terkait kolom dalam tabel basis data. Dengan menggunakan ketiga anotasi ini, kita dapat menggambarkan struktur basis data dengan mudah dalam kode Java dan memetakan objek-objek ke tabel-tabel dalam basis data.

##### 3. Pada relasi buku ke penulis, terdapat tag 
```@JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_penulis"))``` 
##### Jelaskan maksud dari tag @JoinTable tersebut beserta parameternya (name, joinColumns, inverseJoinColumns) dan implementasinya pada database.
Tag @JoinTable biasanya digunakan dalam pemetaan hubungan Many-to-Many antara dua entitas. Dengan menggunakan tag @JoinTable, tabel penghubung akan dibuat dalam basis data. Parameter "name" digunakan untuk menentukan nama tabel penghubung dalam basis data. Pada bacabaca, @JoinTable digunakan untuk menghubungkan entitas Buku dan Penulis yang memiliki hubungan Many-to-Many dengan nama tabel "penulis_buku". Parameter "joinColumns" digunakan untuk menentukan kolom-kolom dalam tabel penghubung yang akan merujuk ke entitas yang memiliki asosiasi, dalam hal ini, kolom "id" pada entitas Buku. Sementara itu, parameter "inverseJoinColumns" digunakan untuk menentukan kolom-kolom dalam tabel penghubung yang akan merujuk ke entitas yang tidak memiliki asosiasi, dalam hal ini, kolom "id_penulis" pada entitas Penulis. Dengan tabel penghubung ini, kita dapat menyimpan dan mengelola hubungan Many-to-Many antara entitas Buku dan Penulis dalam basis data.

##### 4. Bagaimana cara kerja dari dependensi java mapper, yaitu mapstruct?
MapStruct merupakan sebuah framework yang digunakan untuk menghasilkan kode pemetaan (mapping) objek Java secara otomatis. Hal ini memungkinkan konversi objek dari satu jenis ke jenis lain dengan mudah, tanpa perlu menulis kode pemetaan manual. Pertama-tama, antarmuka Java yang sering disebut sebagai Mapper perlu didefinisikan. Pada Mapper, anotasi MapStruct digunakan untuk menandai metode-metode yang akan digunakan untuk pemetaan. Anotasi ini akan diproses oleh MapStruct lalu dicolokkan ke kompiler Java dan alat generasi kode seperti Maven atau Gradle dapat digunakan untuk menghasilkan kode pemetaan secara otomatis sehingga objek dikonversi dari satu jenis ke jenis lain. 

##### 5. Apa keuntungan dari implementasi soft delete?
Soft delete merupakan teknik menghapus data tanpa benar-benar menghapus dengan menggunakan kolom status yang menunjukkan keaktifan data sehingga data tidak akan muncul pada antarmuka pengguna. Soft delete seringkali digunakan karena dapat mengembalikan data apabila suatu saat data yang telah dihapus dibutuhkan kembali. Selain itu, soft delete memungkinkan kita untuk menyimpan rekam jejak data yang dihapus sehingga pelacakan data sangat detil karena lengkapnya riwayat data. 

##### Referensi Tutorial 3:
- https://www.geeksforgeeks.org/spring-orm-framework/
- https://www.baeldung.com/jpa-entities
- https://docs.oracle.com/javaee/7/api/javax/persistence/JoinTable.html
- https://mapstruct.org/
- https://sudusoftware.com/teknik-sql-hard-delete-dan-soft-delete/


---
## Tutorial 4

### What I have learned today

##### 1. Pada file html project bacabaca, terdapat baris kode berikut.
`<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://thymeleaf.org">`
##### Apa itu xmlns? Jawab dengan singkat dan padat.
xmlns adalah atribut yang digunakan untuk mendefinisikan namespace pada dokumen HTML.
- `xmlns="http://www.w3.org/1999/xhtml"`
Pada kode ini, xmlns digunakan untuk mendefinisikan namespace default, yaitu namespace yang akan digunakan oleh elemen-elemen dalam dokumen jika tidak terdapat namespace yang secara khusus dideklarasikan untuk elemen tersebut. 
- `xmlns:th="http://thymeleaf.org"`
Pada kode ini, xmlns digunakan untuk menghubungkan dan mendefinisikan namespace khusus, yaitu Thymeleaf, pada dokumen HTML. Alias "th" diberikan ke namespace tersebut sehingga memungkinkan penggunaan ekspresi dan fitur Thymeleaf seperti "th:text" dalam elemen-elemen dokumen HTML.

##### 2. Jelaskan perbedaan th:include dan th:replace! Jawab dengan singkat dan padat.
- `th:include`: menyisipkan konten dari fragment atau template ke dalam elemen target, menjaga elemen target yang ada.
- `th:replace`: menggantikan elemen target sepenuhnya dengan konten dari fragment atau template yang ditentukan.

##### 3. Kapan sebaiknya kita menggunakan static files dibandingkan dengan file eksternal menggunakan link?  Jawab dengan singkat dan padat.
Static files sebaiknya digunakan ketika file bersifat statis dan tidak perlu dimodifikasi secara dinamis berdasarkan logika aplikasi. Sebaliknya, file eksternal dengan link sebaiknya digunakan ketika konten file perlu dimuat secara dinamis atau dikelola melalui logika aplikasi.

##### 4. Jelaskan caramu menyelesaikan latihan no 2.
- Menambahkan atribut listPenulisExisting ke model dalam fungsi formUpdateBuku pada BukuController
`model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());`
- Membuat function addRowPenulisBukuUpdate dan deleteRowPenulisBukuUpdate pada BukuController untuk menambahkan dan menghapus row penulis saat update buku
- Menambahkan kode pada form-update-buku.html untuk menampilkan form Penulis pada antarmuka pengguna sehingga pengguna dapat menambahkan atau menghapus penulis saat update buku

##### 5. Jelaskan apa itu pagination! Jawab dengan singkat dan padat.
Pagination adalah teknik dalam desain antarmuka pengguna yang digunakan untuk membagi daftar data menjadi beberapa halaman terpisah, sehingga tampilan menjadi lebih rapi dan pengguna dapat melakukan navigasi dengan lebih mudah.

##### 6. Sebutkan salah satu skenario yang mengharuskan adanya perbedaan dev dan prod dan jelaskan alasannya!
Misalkan terdapat perusahaan yang ingin meluncurkan aplikasi berbasis data. Saat pengembangan aplikasi, tim pengembangan memerlukan database dev untuk memastikan bahwa aplikasi telah diuji secara menyeluruh sehingga seluruh kebutuhan fungsional dijamin terpenuhi. Apabila database dev tetap digunakan saat aplikasi telah dirilis, maka data-data palsu yang dibuat saat uji aplikasi akan tergabung dengan data-data asli dari pengguna. Hal ini dapat mengganggu integritas data dan transaksi di lingkungan produksi, yang berpotensi menimbulkan dampak negatif dan biaya yang signifikan jika terjadi masalah. Maka dari itu, penting untuk membedakan database dev dan prod untuk membantu menghindari kebocoran data, kerusakan pada transaksi, atau gangguan pada pengalaman pengguna.

##### 7. Lampirkan screenshot kalau kamu sudah berhasil membuat user untuk environment production serta bukti bahwa kamu sudah berhasil mengakses database production dengan user tersebut!
- Screenshot bahwa saya sudah berhasil membuat user untuk environment production 
![Screenshot_2023-09-26_at_22.05.55](/uploads/739970185b05cebb52ffe7476ec9bccd/Screenshot_2023-09-26_at_22.05.55.png)
- Screenshot bahwa saya sudah berhasil mengakses database production
![Screenshot_2023-09-26_at_22.07.34](/uploads/dda06d4c78ddaa132d9510a788d553b0/Screenshot_2023-09-26_at_22.07.34.png)


---
## Tutorial 5

### What I have learned today

##### 1. Apa itu Postman? Apa kegunaannya?
Postman adalah alat pengujian API yang memungkinkan pengembang perangkat lunak untuk dengan mudah menguji, mengelola, dan berinteraksi dengan API. Postman memungkinkan pengguna untuk membuat, mengirim, dan menganalisis permintaan HTTP ke berbagai jenis API, yang memudahkan pengembangan, debugging, dan dokumentasi API. Postman juga menyediakan fitur kolaborasi yang memungkinkan tim pengembang bekerja bersama dalam mengembangkan API.

##### 2. Apa yang terjadi ketika kita tidak menggunakan @JsonIgnoreProperties dan @JsonProperty pada model Buku dan Penulis? Apabila terjadi error, mengapa hal tersebut dapat terjadi?
Jika kita tidak menggunakan @JsonIgnoreProperties pada model Buku dan Penulis, maka seluruh atribut pada objek Buku dan Penulis akan dikonversi menjadi properti JSON secara otomatis. Hal ini dapat menyebabkan masalah apabila terdapat atribut yang tidak seharusnya menjadi bagian dari JSON karena hanya digunakan secara internal. Selain itu, terdapat kemungkinan nama atribut pada objek Buku dan Penulis tidak sesuai dengan nama atribut dalam JSON, sehingga @JsonProperty perlu digunakan untuk menentukan nama atribut yang benar dalam JSON. Jika tidak digunakan, maka kesalahan pengiriman dan penerimaan data dapat terjadi.

##### 3. Pada tutorial ini, kita mencoba untuk memanggil data dengan menggunakan method GET. Namun, apakah kita dapat memanggil data dengan method lainya, seperti POST? Jelaskan pendapat kalian?
Meskipun method GET adalah metode yang paling sesuai dan umum untuk memanggil data, akan tetapi kita dapat memanggil data dengan menggunakan method lainnya seperti POST. Misalnya, ketika kita mengkonfigurasi server API untuk menangani permintaan POST dan mengirimkan data dalam responsenya. Melalui latihan tutorial ini, kita mencoba melakukan hal tersebut dengan memanggil data translatedText dari server API dengan menggunakan method POST.

##### 4. Selain method GET dan POST, sebutkan dan jelaskan secara singkat HTTP request methods lainnya yang dapat kita gunakan!
- HEAD: meminta respon tanpa response body
- PUT: mengganti semua representasi sumber daya target dengan permintaan
- DELETE: menghapus sumber daya tertentu
- CONNECT: membuat koneksi jaringan ke server yang diidentifikasi oleh sumber daya target
- OPTIONS: menjelaskan opsi komunikasi untuk sumber daya target
- TRACE: melakukan tes loop-back pesan di sepanjang jalur ke sumber daya target
- PATCH: menerapkan modifikasi parsial pada sumber daya

##### 5. Apa kegunaan atribut WebClient?
Atribut `WebClient` dalam Spring Framework adalah objek yang digunakan untuk berkomunikasi dengan server melalui protokol HTTP. Kegunaannya adalah untuk membuat permintaan HTTP ke server, mengelola respons yang diterima, dan memungkinkan interaksi yang efisien antara aplikasi dan layanan web eksternal. `WebClient` memungkinkan kita untuk mengkonfigurasi berbagai aspek permintaan, seperti URL, metode HTTP, header, dan body, serta mengonversi respons HTTP menjadi objek Java dengan mudah. Atribut ini adalah komponen penting dalam mengakses dan berinteraksi dengan API web.

Referensi:
- https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods


---
## Tutorial 6

### What I have learned today

##### 1. Apa yang menjadi penyebab dari CONFLICT tersebut?
Conflict tersebut terjadi karena terdapat perbedaan antara perubahan yang ada pada branch saat ini (current change) dan perubahan yang ada pada branch yang ingin dimerge (incoming change). Dalam konteks lab 6 ini, terdapat perbedaan body antara perubahan yang ada pada branch feat/tutorial-6-advancedgit-1 dan perubahan yang ada pada branch tut6-for-merge. Konflik ini mengindikasikan bahwa Git tidak dapat secara otomatis menentukan cara menggabungkan perubahan tersebut karena ada potensi kehilangan informasi atau ketidakjelasan.

##### 2. Jelaskan perbedaan dari "rebase –continue", "rebase –skip", dan "rebase –abort"!
- rebase –continue: digunakan untuk melanjutkan proses rebase setelah menyelesaikan konflik
- rebase –skip: digunakan untuk mengabaikan commit yang menyebabkan konflik selama rebase
- rebase –abort: digunakan untuk membatalkan proses rebase dan kembali ke keadaan sebelum rebase dimulai


---
## Tutorial 7

### What I have learned today

##### 1. Apa itu Dockerfile dan docker-compose.yml? Apa fungsinya?
Dockerfile merupakan sebuah dokumen teks yang berisi semua perintah yang diperlukan pengguna untuk membuat docker image pada command line. Dengan bantuan Dockerfile, pengguna dapat membuat build otomatis yang menjalankan beberapa instruksi command line secara berurutan. Sementara itu, Compose merupakan tool yang digunakan untuk mendefinisikan dan menjalankan aplikasi Docker multi-container. Untuk melakukan konfigurasi, Docker Compose menggunakan docker-compose.yml. Pada dokumen ini, services, volumes, dan networks didefinisikan untuk container Docker sehingga kita dapat membuat dan memulai semua layanan dari konfigurasi hanya dengan 1 baris perintah saja.

##### 2. Screenshot hasil perubahan anda. Setelah anda menyelesaikan tutorial ini, menurut anda, mengapa kita perlu mengganti port?
![Screenshot_2023-11-15_at_22.33.26](/uploads/b83aeb944eb83a6ef4bac7bddf492ce4/Screenshot_2023-11-15_at_22.33.26.png)

Menurut saya, kita perlu mengganti port karena apabila semua mahasiswa APAP menggunakan port yang sama saat melakukan deploy, maka konflik akan terjadi saat mencoba menjalankan beberapa proyek atau layanan secara bersamaan. Konflik ini dapat menyebabkan ketidakmampuan untuk terhubung ke layanan yang berjalan di port tersebut. Dengan memberikan port yang berbeda, kita dapat menghindari konflik dan memastikan bahwa setiap proyek dapat berjalan secara terpisah dan tidak saling mempengaruhi. Selain itu, dalam konteks deploy CI/CD, menggunakan port yang berbeda juga dapat membantu mencegah konflik antara job CI/CD yang berjalan secara bersamaan.

##### 3. Apa saja yang terjadi di langkah ini?
Saat menjalankan `docker-compose up`, kita membangun, membuat, dan memulai container untuk suatu layanan. Dalam konteks tutorial 7 ini, saya membangun, membuat, dan memulai container tutorial-apap-2106637366 untuk layanan db-1 dan web-1. Saat exit (menekan control+C), container akan berhenti berjalan. Apabila kita ingin exit dan membiarkan container tetap berjalan, maka kita dapat menjalankan `docker-compose up -d` (-d singkatan dari -detach).

##### 4. Sertakan screenshot container yang sedang berjalan (versi gui atau cli, pilih salah satu). Apa itu docker images, docker container, dan docker volume?
![Screenshot_2023-11-15_at_22.33.52](/uploads/3696b3dd756bcc29cf04c39f9029892e/Screenshot_2023-11-15_at_22.33.52.png)
Docker Image merupakan sebuah file berisi kode aplikasi, libraries, tools, dan dependencies yang dibutuhkan untuk menjalankan sebuah aplikasi dalam Docker Container. Docker Image bertindak sebagai serangkaian instruksi yang bersifat read-only untuk membangun Docker Container. Sementara itu, Docker Container merupakan tempat terisolasi dimana aplikasi berjalan tanpa memengaruhi dan dipengaruhi sistem secara keseluruhan. Akibat sifatnya yang terisolasi, Docker Container sangat cocok untuk menjalankan perangkat lunak dengan aman, seperti database atau aplikasi web yang memerlukan akses ke sumber daya sensitif. Selain itu, Docker Container seringkali perlu menggunakan data di luar container atau berbagi data antar container. Solusi untuk hal ini adalah bekerja dengan data persisten dalam sebuah container, yaitu Docker Volume. Docker Volume adalah sistem file independen yang sepenuhnya dikelola oleh Docker dan berfungsi sebagai tempat penyimpanan data. Tujuan penggunaan Docker Volume adalah untuk menyimpan data di luar container sehingga dapat di-back up atau dibagikan antar container.

##### 5. Apa perbedaan docker-compose down dan docker stop?
`Docker-compose down` menghentikan container dan menghapus container, networks, volumes, dan images yang dibangun dan dibuat oleh `docker-compose up`. Sementara itu, `docker-compose stop` hanya menghentikan container tanpa menghapusnya sehingga container dapat dimulai lagi dengan `docker-compose start`.

##### 6. Sertakan screenshot mengakses laman kirti milik anda melalui browser (seperti screenshot di atas)
![Screenshot_2023-11-16_at_15.04.43](/uploads/d7d322d34f5a8d4f9bdc3e7b37a6a203/Screenshot_2023-11-16_at_15.04.43.png)

##### 7. Ceritakan pengalaman anda melakukan deployment ke Kirti. Kendala apa yang anda alami?
Kendala yang sering saya alami adalah server Kirti yang full sehingga menyebabkan timeout karena respon HTTP request yang terlalu lama. Hal ini menyebabkan pengerjaan bagian Kirti sangat lama.

##### 9. Buka container docker Anda, lalu screenshot. Apa perbedaan tampilan container sekarang dengan tampilan container pada langkah tutorial docker di awal tadi?
![Screenshot_2023-11-16_at_03.08.57](/uploads/1749e64b4181630946139921197e1482/Screenshot_2023-11-16_at_03.08.57.png)
Bedanya adalah terdapat layanan web2 pada tampilan container sekarang, sedangkan tidak terdapat layanan web2 pada tampilan container pada langkah tutorial docker di awal tadi. Hal ini disebabkan oleh penambahan layanan web2 pada docker-compose.yml.

##### 10. Sertakan screenshot tampilan web ketika pertama kali menjalankan localhost:9090/port dan tampilan web ketika halaman di-refresh.
Berikut merupakan screenshot tampilan web ketika pertama kali menjalankan localhost:9090/port.
![Screenshot_2023-11-16_at_03.08.30](/uploads/977ee53434ad6518fd056cae67a45bab/Screenshot_2023-11-16_at_03.08.30.png)
Berikut merupakan screenshot tampilan web ketika halaman di-refresh.
![Screenshot_2023-11-16_at_03.16.37](/uploads/984cddf16bbb80e47cbc61b890e287ec/Screenshot_2023-11-16_at_03.16.37.png)

##### 11. Apa yang dimaksud load balancing? Pada langkah keberapa kita mengatur konfigurasi untuk load balancing? Jelaskan blok baris yang mengatur hal tersebut.
Load balancing merupakan proses pendistribusian traffic atau lalu lintas jaringan secara efisien ke dalam beberapa server. Hal ini berguna agar salah satu server dari website yang mendapatkan banyak lalu lintas kunjungan tidak mengalami kelebihan beban. Load balancing dapat diterapkan untuk menstabilkan server website yang memiliki lonjakan traffic dan menghindari gangguan server down.
Kita mengatur konfigurasi untuk load balancing pada langkah berikut:
```
upstream homepage {
   server localhost: 10061;
   server localhost: 10062;
}
```
Dengan konfigurasi di atas, kita mendefinisikan grup server bernama homepage yang terdiri dari dua server, satu dengan alamat localhost:10061 dan yang lainnya dengan alamat localhost:10062. Nginx kemudian akan mendistribusikan lalu lintas ke kedua server tersebut secara merata (load balancing) menggunakan algoritme defaultnya.

##### 12. Apa yang dimaksud reverse proxy? Pada langkah keberapa kita mengatur konfigurasi untuk reverse proxy? Jelaskan baris yang mengatur hal tersebut dan jelaskan kegunaannya dalam pengerjaan tugas kelompok nanti.
Reverse proxy adalah server yang berada di depan server web dan meneruskan permintaan klien ke server web tersebut. Reverse proxy biasanya diterapkan untuk melindungi server web dari serangan serta membantu meningkatkan kinerja dan keandalan.
Kita mengatur konfigurasi untuk reverse proxy pada langkah berikut:
```
location / {
   proxy_pass "http://homepage";
}
```
Dengan konfigurasi di atas, Nginx berperan sebagai reverse proxy untuk permintaan yang datang ke direktori root (/). Semua permintaan klien akan diteruskan ke grup server `homepage` dengan menggunakan perintah `proxy_pass`. Reverse proxy ini akan sangat berguna untuk pengerjaan tugas kelompok nanti karena terdapat empat server web yang digunakan yaitu Catalog, FrontEnd, User, dan Order. Dengan adanya reverse proxy, suatu server dapat meneruskan permintaan klien ke server web yang bersangkutan.

##### 13. Kendala apa yang anda hadapi ketika melakukan tutorial bagian nginx?
Tidak ada kendala yang saya hadapi ketika melakukan tutorial bagian nginx.

##### 14. Apa fungsi dari SSH keys yang Anda buat dengan menggunakan ssh-keygen? Apa perbedaan antara file ~/.ssh/deployer_apap.pub dan ~/.ssh/deployer_apap?
SSH keys adalah kredensial akses dalam protokol SSH. Fungsinya mirip dengan username dan password, namun SSH keys terutama digunakan untuk proses otomatis dan menerapkan single sign-on. File ~/.ssh/deployer_apap.pub merupakan file public keys. Keys ini dapat disebarkan ke server atau sistem lain yang ingin diakses. File ini berisi informasi identitas, tetapi tidak dapat digunakan untuk otentikasi tanpa pasangan private keys-nya. Sementara itu, file ~/.ssh/deployer_apap merupakan file private keys yang sesuai dengan public keys. Private keys disimpan dengan aman dan tidak boleh dibagikan. Private keys ini yang akan digunakan oleh klien SSH untuk membuktikan identitasnya saat mengakses server atau sistem.

##### 15. Apa perbedaan antara GitLab repository dan GitLab runner?
Gitlab repository adalah tempat untuk menyimpan kode dan mengubahnya. Perubahan kode dapat dilacak dengan version control. Sementara itu, Gitlab Runner merupakan sebuah aplikasi yang bekerja dengan Gitlab CI/CD untuk menjalankan jobs di pipeline.

##### 16. Apa perbedaan antara Continuous Integration, Continuous Delivery, dan Continuous Deployment?
Continuous Integration merupakan penggabungan perubahan dari branch ke cabang utama sesering mungkin. Perubahan ini divalidasi dengan membuat build dan menjalankan pengujian otomatis terhadap build tersebut. Dengan melakukan hal ini, tantangan integrasi yang dapat terjadi bisa dihindari. Continuous Delivery adalah perpanjangan dari Continuous Integration karena secara otomatis mendeploy semua perubahan kode ke testing dan/atau production setelah tahap build. Dengan kata lain, selain pengujian otomatis, proses perilisan juga otomatis sehingga kita dapat langsung melakukan deploy aplikasi apabila proses pengembangan sudah selesai. Continuous Deployment merupakan perpanjangan dari Continuous Delivery karena secara otomatis mendeploy aplikasi sehingga dapat digunakan pengguna apabila perubahan yang dilakukan melewati semua stage dari production pipeline. Hanya pengujian yang gagal yang akan mencegah perubahan baru dideploy.

##### 17. Apa perbedaan dari stages-stages yang berada dalam file .gitlab-ci.yml?
- build: membangun (compile) perangkat lunak dari kode sumber
- build-image: membuat atau mempersiapkan Docker image
- publish-image: menerbitkan (push) Docker image yang sudah dibuat ke Docker registry
- deploy: mendeploy aplikasi atau layanan ke lingkungan production atau testing

##### 18. Pada script gitlab-ci.yml, terdapat perubahan if: $CI_COMMIT_BRANCH == 'main' menjadi if: $CI_COMMIT_BRANCH == 'feat/tutorial-7-bacabaca'. Apa fungsi dari perubahan tersebut?
Baris `if: $CI_COMMIT_BRANCH == 'main'` berarti bahwa job akan dijalankan hanya jika commit terjadi di branch `main`. Dengan mengubah baris tersebut menjadi `if: $CI_COMMIT_BRANCH == 'feat/tutorial-7-bacabaca'`, artinya job akan dijalankan hanya jika commit terjadi di branch `feat/tutorial-7-bacabaca'`. Karena kita menggunakan branch `feat/tutorial-7-bacabaca'`, maka perubahan tersebut diperlukan.

##### 19. Apa yang dimaksud dengan "docker registry"? Apa fungsinya? 
Docker registry adalah sistem penyimpanan dan distribusi untuk Docker image yang diberi nama. Image yang sama mungkin memiliki beberapa versi berbeda, yang diidentifikasi berdasarkan tagnya. Docker registry diatur ke dalam Docker repository, dimana repositori menyimpan semua versi image tertentu. Docker registry memungkinkan pengguna Docker untuk pull image secara lokal, serta push image baru ke registry.

##### 20. Dalam gitlab CI/CD, apa perbedaan antara: pipeline, stage, dan job?
Pipeline adalah komponen tingkat atas dari continuous integration, continuous delivery, dan continuous deployment. Pipeline terdiri dari jobs yang menentukan apa yang harus dilakukan dan stages yang menentukan kapan untuk menjalankan jobs. Jobs dieksekusi oleh runners. Apabila semua jobs pada suatu stage berhasil dijalankan, maka pipeline akan lanjut ke stage berikutnya. Sementara itu, apabila terdapat job yang gagal, maka stage berikutnya tidak dieksekusi dan pipeline akan berhenti.

##### 21. Sertakan screenshot fullscreen saat Anda mengakses apap-xxx.cs.ui.ac.id ketika sudah berhasil men-deploy aplikasi menggunakan CI/CD! 
Berikut error yang saya dapatkan ketika mendeploy aplikasi menggunakan CI/CD.
![Screenshot_2023-11-21_at_00.33.58](/uploads/e49d7726322c66c0f0ab76bea78f84d8/Screenshot_2023-11-21_at_00.33.58.png)
Error yang saya dapatkan adalah `No space left on device` yang disebabkan oleh server Kirti yang full. Akan tetapi, saya sudah berhasil melewati stage build, build-image, dan publish-image.
![Screenshot_2023-11-21_at_00.40.27](/uploads/16a20d7e476373d82f8e089066bf390d/Screenshot_2023-11-21_at_00.40.27.png)

##### 22. Kapan proses CI/CD dijalankan di GitLab?
Secara umum, GitLab CI/CD bekerja berdasarkan peristiwa (events) yang terjadi pada repositori. Dalam konteks tutorial ini, proses CI/CD di GitLab dijalankan setiap kali ada perubahan yang di-push ke repositori proyek.

##### 23. Mengapa CI/CD ini penting? Apa manfaatnya? 
CI/CD penting karena membantu tim pengembangan, keamanan, dan operasi bekerja seefisien dan seefektif mungkin. Hal ini mengurangi pekerjaan pengembangan manual yang memakan waktu. Otomatisasi membuat proses dapat diprediksi dan diulang sehingga peluang terjadinya kesalahan akibat campur tangan manusia menjadi lebih kecil. Selain itu, tim pengembangan mendapatkan masukan lebih cepat dan dapat mengintegrasikan perubahan kecil secara berkala untuk mengurangi risiko perubahan yang dapat merusak build. Dengan CI/CD, siklus hidup pengembangan perangkat lunak menjadi lebih cepat sehingga deploy aplikasi dapat segera dilakukan.

Referensi:
- https://www.simplilearn.com/tutorials/docker-tutorial/what-is-dockerfile
- https://docs.docker.com/reference/
- https://cloudmatika.co.id/blog-detail/apa-itu-load-balancing
- https://www.cloudflare.com/learning/cdn/glossary/reverse-proxy/
- https://docs.nginx.com/nginx/admin-guide/load-balancer/http-load-balancer/
- https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment
- https://www.aquasec.com/cloud-native-academy/docker-container/docker-registry
- https://docs.gitlab.com/ee/user/project/repository
- https://docs.gitlab.com/runner/
- https://www.ssh.com/academy/ssh-keys
- https://www.spiceworks.com/tech/devops/articles/what-is-ci-cd/