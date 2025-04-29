class Buku {
  Buku({
    required this.uuid,
    required this.judul,
    required this.harga,
    required this.penerbit,
    required this.tahunTerbit
  });

  String uuid;
  String judul;
  String penerbit;
  int? harga;
  int? tahunTerbit;

  factory Buku.fromJson(Map<String, dynamic> json) => Buku(
    uuid: json["uuid"],
    judul: json["judul"],
    penerbit: json["penerbit"],
    tahunTerbit: json["tahunTerbit"],
    harga: json["harga"],
  );
}
