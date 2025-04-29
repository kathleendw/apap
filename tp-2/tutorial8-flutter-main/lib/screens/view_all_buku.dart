import 'dart:convert';
import 'package:bacabaca_mobile/model/BukuResponse.dart';
import 'package:bacabaca_mobile/utils/reusable_widget.dart';

import 'package:flutter/material.dart';

class BukuScreen extends StatefulWidget {
  static const routeName = '/appointment/list';
  const BukuScreen({Key? key}) : super(key: key);

  @override
  State<BukuScreen> createState() => _BukuScreenState();
}

class _BukuScreenState extends State<BukuScreen> {

  Future<List<Buku>?> fetchBuku(String? token) async {
    try {
      // Todo
    } catch (e) {
      print(e.toString());
      return null;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            elevation: 0,
            automaticallyImplyLeading: false,
            backgroundColor: const Color(0XFF5d675b),
            actions: [
        Padding(
        padding: const EdgeInsets.only(right: 10),
        child: IconButton(
        onPressed: () {
        popUpExit(
            context, "Log out of your account?");
        },
        icon: const Icon(
        Icons.account_circle_outlined,
        size: 40,
        color: Colors.white,
      ),
    ))
    ],
    ),
      body:  FutureBuilder<List<Buku>?>(
        future: fetchBuku("replace this with real token"), // a previously-obtained Future<String> or null
        builder: (BuildContext context, AsyncSnapshot<List<Buku>?> snapshot) {
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
                        "Buku tidak ditemukan",
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

          if (!snapshot.hasData) {
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
            final data = snapshot.data as List<Buku>;

            return Padding(
              padding: const EdgeInsets.all(20),
              child: ListView.builder(
                  shrinkWrap: true,
                  itemCount: snapshot.data!.length,
                  itemBuilder: (_, index)=> Container(
                    margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                    padding: const EdgeInsets.all(20.0),
                    decoration: BoxDecoration(
                        color:Colors.white,
                        borderRadius: BorderRadius.circular(15.0),
                        boxShadow: const [
                          BoxShadow(
                              color: Colors.black,
                              blurRadius: 2.0
                          )
                        ]
                    ),
                    child: const Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        // Todo
                      ],
                    ),
                  )
              ),
            );
          }
        },
      ),
    );
  }
}
