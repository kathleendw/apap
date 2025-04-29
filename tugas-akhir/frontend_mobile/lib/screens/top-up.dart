import 'dart:convert';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:frontend_mobile/screens/auth/register.dart';
import 'package:frontend_mobile/screens/view-catalog.dart';
import 'package:frontend_mobile/utils/color_palette.dart';
import 'package:http/http.dart' as http;
import 'package:jwt_decode/jwt_decode.dart';
import 'package:provider/provider.dart';
import 'package:frontend_mobile/components/drawer.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../model/Customer.dart';

class TopUpFormScreen extends StatefulWidget{
  static const routeName = '/login';
  const TopUpFormScreen({super.key});

  @override
  State<TopUpFormScreen> createState() => _TopUpFormScreen();
}

class _TopUpFormScreen extends State<TopUpFormScreen> {
  final _formKey = GlobalKey<FormState>();

  showLoading(BuildContext context) {
    return showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext context) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        });
  }

  Future<String?> readJwtToken() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('jwtToken');
  }

  Future<Customer?> fetchCustomer() async {
    try {

      String jwtToken = await readJwtToken() as String;
      Map<String, dynamic>? payload = Jwt.parseJwt(jwtToken);

      print("Ini sebelum response $payload");

      String username = payload["sub"] as String;
      String idUser = payload["id"] as String;

      final response = await http.get(
        Uri.parse("http://localhost:8082/api/customer/retrieve/$idUser"),
        headers: {
          'Authorization': 'Bearer ${jwtToken ?? ''}' // validate jwt token
        },
      );
      final dynamic jsonBody = json.decode(response.body);

      print("customer");
      print(jsonBody);
      final Customer customer = Customer.fromJson(jsonBody);

      return customer;
    } catch (e) {
      print("Ini masuk ke error");
      print(e.toString());
      return null;
    }
  }

  TextEditingController moneyController = TextEditingController();

  @override
  void dispose() {
    moneyController.dispose();
    super.dispose();
  }

  @override
  @override
  Widget build(BuildContext context) {
    bool isLoading = false;

    return PopScope(
      canPop: false,
      child: Scaffold(
          backgroundColor: Colors.grey[300],
          body: FutureBuilder<Customer?>(
            future: fetchCustomer(),
            builder: (BuildContext context, AsyncSnapshot<Customer?> snapshot) {
              if (snapshot.data == null) {
                if (snapshot.connectionState == ConnectionState.done) {
                  return const Padding(
                    padding: EdgeInsets.all(12),
                    child: Center(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            "Customer tidak ditemukan",
                            style: TextStyle(
                                fontSize: 16),
                          ),
                          SizedBox(height: 8),
                        ],
                      ),
                    ),
                  );
                  }
                  return const Center(
                    child: CircularProgressIndicator()
                  );
                }

              if(!snapshot.hasData) {
                return const Column(
                  children: [
                    Text(
                      "Buku tidak ditemukan",
                      style: TextStyle(
                          fontSize: 16),
                    ),
                    SizedBox(height: 8),
                  ],
                );
              } else {
                final customer = snapshot.data as Customer;

                int _balance = customer.balance??0;

                void _incrementBalance(){
                  setState(() {
                    _balance = _balance + int.parse(moneyController.text);
                    moneyController.clear();
                  });
                }

                return Center(
                  child: SingleChildScrollView(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        const Text("APAPAY",
                            style: TextStyle(
                                fontSize: 28,
                                fontWeight: FontWeight.w700)),
                        const SizedBox(
                          height: 10,
                        ),
                        const Icon(
                          Icons.wallet,
                          size: 100,
                        ),
                        const SizedBox(
                          height: 10,
                        ),
                        Container(
                          padding:
                          const EdgeInsets.symmetric(horizontal: 8, vertical: 20),
                          margin: const EdgeInsets.all(16),
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(8),
                            color: Colors.white,
                          ),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              const Text("Top Up",
                                  style: TextStyle(
                                    fontSize: 24,
                                  )),
                              const SizedBox(
                                height: 20,
                              ),
                              const Text("Balance",
                                  style: TextStyle(
                                    fontSize: 24,
                                  )),
                              Text("$_balance",
                                  style: TextStyle(
                                    fontSize: 24,
                                  )),
                              Form(
                                  key: _formKey,
                                  child: SingleChildScrollView(
                                    child: Column(
                                      children: [
                                        const SizedBox(
                                          height: 20,
                                        ),
                                        const SizedBox(
                                          height: 20,
                                        ),
                                        SizedBox(
                                          width:
                                          MediaQuery.of(context).size.width * 0.8,
                                          child: TextFormField(
                                            controller: moneyController,
                                            keyboardType: TextInputType.number,
                                            decoration: const InputDecoration(
                                              border: OutlineInputBorder(),
                                              labelText: "10000",
                                            ),
                                            validator: (String? value) {
                                              if (value == null || value.isEmpty) {
                                                return 'Masukkan nominal top up untuk melanjutkan transaksi';
                                              }
                                              return null;
                                            },
                                          ),
                                        ),
                                        const SizedBox(
                                          height: 20,
                                        ),
                                        ElevatedButton(
                                          style: ElevatedButton.styleFrom(
                                            fixedSize: Size(MediaQuery.of(context).size.width * 0.8, 50),
                                            backgroundColor: ColorPallete.primary,
                                            shape: RoundedRectangleBorder(
                                              borderRadius: BorderRadius.circular(10),
                                            ),
                                          ),
                                          onPressed: () async {
                                            String? jwtToken = await readJwtToken();
                                            final response = await http.put(
                                                Uri.parse("http://localhost:8082/api/customer/update-balance"),
                                                headers: {
                                                  'Content-Type': 'application/json',
                                                  'Authorization': 'Bearer ${jwtToken??''}'// Set the content type to JSON
                                                },
                                                body: jsonEncode({
                                                  "idUser": customer.idUser,
                                                  "money": moneyController.text,
                                                  "withdrawn": false}
                                                )
                                            );
                                            if(response.statusCode == 200) {
                                              _incrementBalance();
                                            } else {
                                              print("gagal top up ${response.body}");
                                            }

                                          },
                                          child: const Text(
                                            'Top Up',
                                            style: TextStyle(
                                                fontSize: 20,
                                                color: Colors.black
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  )
                              )
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              }
            },
          )


    ),
    );
  }
}
