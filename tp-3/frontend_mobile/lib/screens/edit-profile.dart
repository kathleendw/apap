// ignore_for_file: use_build_context_synchronously

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend_mobile/screens/view-catalog.dart';
import 'package:frontend_mobile/utils/color_palette.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:frontend_mobile/components/drawer.dart';
import 'package:shared_preferences/shared_preferences.dart';
// import 'package:jejakarbon_flutter/apps/homepage/homePage.dart';

class EditProfileFormScreen extends StatefulWidget{
  static const routeName = '/edit-profile';
  const EditProfileFormScreen({super.key});

  @override
  State<EditProfileFormScreen> createState() => _EditProfileFormScreenState();
}

class _EditProfileFormScreenState extends State<EditProfileFormScreen> {
  final _formKey = GlobalKey<FormState>();

  bool isPasswordVisible = false;
  void togglePasswordView() {
    setState(() {
      isPasswordVisible = !isPasswordVisible;
    });
  }

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

  Future<void> saveData(String jwtToken) async {
    SharedPreferences sharedPref = await SharedPreferences.getInstance();
    sharedPref.setString('jwtToken', jwtToken);
    print('Token is saved to shared preferences.');
  }

  TextEditingController nameController = TextEditingController();
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController addressController = TextEditingController();

  @override
  void dispose() {
    usernameController.dispose();
    passwordController.dispose();
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
          body: Center(
            child: SingleChildScrollView(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  const Text("APAPEDIA",
                      style: TextStyle(
                          fontSize: 28,
                          fontWeight: FontWeight.w700)),
                  const SizedBox(
                    height: 10,
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
                        const Text("Edit Profile",
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
                                      controller: nameController,
                                      decoration: const InputDecoration(
                                        border: OutlineInputBorder(),
                                        labelText: "Name",
                                      ),
                                      validator: (String? value) {
                                        if (value == null || value.isEmpty) {
                                          return 'Masukkan nama anda';
                                        }
                                        return null;
                                      },
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  SizedBox(
                                    width:
                                    MediaQuery.of(context).size.width * 0.8,
                                    child: TextFormField(
                                      controller: usernameController,
                                      decoration: const InputDecoration(
                                        border: OutlineInputBorder(),
                                        labelText: "Username",
                                      ),
                                      validator: (String? value) {
                                        if (value == null || value.isEmpty) {
                                          return 'Masukkan username anda';
                                        }
                                        return null;
                                      },
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  SizedBox(
                                    width:
                                    MediaQuery.of(context).size.width * 0.8,
                                    child: TextFormField(
                                      controller: emailController,
                                      decoration: const InputDecoration(
                                        border: OutlineInputBorder(),
                                        labelText: "E-mail",
                                      ),
                                      validator: (String? value) {
                                        if (value == null || value.isEmpty) {
                                          return 'Masukkan e-mail anda';
                                        }
                                        return null;
                                      },
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  SizedBox(
                                    width:
                                    MediaQuery.of(context).size.width * 0.8,
                                    child: TextFormField(
                                      controller: addressController,
                                      decoration: const InputDecoration(
                                        border: OutlineInputBorder(),
                                        labelText: "Address",
                                      ),
                                      validator: (String? value) {
                                        if (value == null || value.isEmpty) {
                                          return 'Masukkan address anda';
                                        }
                                        return null;
                                      },
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  SizedBox(
                                      width:
                                      MediaQuery.of(context).size.width * 0.8,
                                      child: TextFormField(
                                        controller: passwordController,
                                        obscureText: true,
                                        decoration: const InputDecoration(
                                          border: OutlineInputBorder(),
                                          labelText: 'Password',
                                        ),
                                        validator: (String? value) {
                                          if (value == null || value.isEmpty) {
                                            return 'Masukkan password anda';
                                          }
                                          return null;
                                        },
                                      )),
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
                                      final response = await http.put(
                                          Uri.parse("http://localhost:8082/api/customer/edit/d0efc38f-8c2f-4aae-8455-8f5dbf0a1c07"),
                                          headers: {
                                            'Content-Type': 'application/json', // Set the content type to JSON
                                          },
                                          body: jsonEncode({
                                            "nameUser": nameController.text,
                                            "username": usernameController.text,
                                            "email": emailController.text,
                                            "address": addressController.text,
                                            "password":passwordController.text}
                                          )
                                      );
                                      if(response.statusCode == 200) {
                                        final response = await http.post(
                                            Uri.parse("http://localhost:8082/api/authentication/login"),
                                            headers: {
                                              'Content-Type': 'application/json', // Set the content type to JSON
                                            },
                                            body: jsonEncode({
                                              "username": usernameController.text,
                                              "password":passwordController.text}
                                            )
                                        );
                                        if(response.statusCode == 200) {
                                          final Map<String?, dynamic> data = json.decode(response.body);
                                          String jwtToken = data['jwtToken'];
                                          saveData(jwtToken);
                                          Navigator.push(
                                              context, MaterialPageRoute(builder: (context) => const ViewCatalogScreen()));
                                        }
                                      } else {
                                        print(response.body);
                                      }

                                    },
                                    child: const Text(
                                      'Save',
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
                  )
                ],
              ),
            ),
          )),
    );
  }
}
