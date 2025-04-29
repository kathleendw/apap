// ignore_for_file: use_build_context_synchronously

import 'dart:convert';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:frontend_mobile/screens/auth/register.dart';
import 'package:frontend_mobile/screens/top-up.dart';
import 'package:frontend_mobile/screens/view-catalog.dart';
import 'package:frontend_mobile/utils/color_palette.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:frontend_mobile/components/drawer.dart';
import 'package:shared_preferences/shared_preferences.dart';
// import 'package:jejakarbon_flutter/apps/homepage/homePage.dart';

class LoginFormScreen extends StatefulWidget{
  static const routeName = '/login';
  const LoginFormScreen({super.key});

  @override
  State<LoginFormScreen> createState() => _LoginFormScreenState();
}

class _LoginFormScreenState extends State<LoginFormScreen> {
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

  Future<String?> getCurrentUserId() async {
    SharedPreferences sharedPref = await SharedPreferences.getInstance();
    String? jwtToken = sharedPref.getString('jwtToken');

    if (jwtToken != null) {
      // Decode the JWT token and extract the user ID
      final List<String> parts = jwtToken.split('.');
      if (parts.length == 3) {
        final Map<String, dynamic> payload = json.decode(
          utf8.decode(base64Url.decode(base64Url.normalize(parts[1]))),
        );
        return payload['idUser'];
      }
    }
    return null;
  }

  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

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
                  const Icon(
                    Icons.android,
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
                        const Text("Hello, Login to Explore!",
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
                                        String? userId = await getCurrentUserId();
                                        if (userId != null) {
                                          print('User ID: $userId');
                                        } else {
                                          print('Failed to retrieve user ID from token.');
                                        }
                                        Navigator.push(
                                            context, MaterialPageRoute(builder: (context) => const ViewCatalogScreen()));
                                            // context, MaterialPageRoute(builder: (context) => const TopUpFormScreen()));
                                      } else {
                                        print("gagal login");
                                      }

                                    },
                                    child: const Text(
                                      'Login',
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
                  RichText(
                      text: TextSpan(
                        text: 'Belum punya akun? ',
                        style: const TextStyle(
                          color: Colors.grey,
                          fontSize: 16
                        ),
                        children: <TextSpan>[
                          TextSpan(
                            text: 'Register',
                            style: const TextStyle(
                              fontSize: 16,
                              color: Colors.blue
                            ),
                            recognizer: TapGestureRecognizer()
                              ..onTap = () {
                                Navigator.push(
                                    context, MaterialPageRoute(builder: (context) => const RegisterFormScreen()));
                              }
                          )
                        ]
                      )
                  )
                ],
              ),
            ),
          )),
    );
  }
}
