import 'dart:convert';

Customer customerFromJson(String str) => Customer.fromJson(json.decode(str));

String customerToJson(Customer data) => json.encode(data.toJson());

class Customer {
  String? idUser;
  String? nameUser;
  String? username;
  String? password;
  String? email;
  String? address;
  String? createdAt;
  String? updatedAt;
  int? balance;
  String? cartId;

  Customer({
    required this.idUser,
    required this.nameUser,
    required this.username,
    required this.password,
    required this.email,
    required this.address,
    required this.createdAt,
    required this.updatedAt,
    required this.balance,
    required this.cartId
  });

  factory Customer.fromJson(Map<String, dynamic> json) => Customer(
    idUser: json["idUser"],
    nameUser: json["nameUser"],
    username: json["username"],
    password: json["password"],
    email: json["email"],
    address: json["address"],
    createdAt: json["createdAt"],
    updatedAt: json["updatedAt"],
    balance: json["balance"],
    cartId: json["cartId"]
  );

  Map<String, dynamic> toJson() => {
    "idUser": idUser,
    "nameUser": nameUser,
    "username": username,
    "password": password,
    "email": email,
    "address": address,
    "createdAt": createdAt,
    "updatedAt": updatedAt,
    "balance": balance,
    "cartId": cartId
  };
}
