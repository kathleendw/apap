import 'package:flutter/material.dart';
import 'package:frontend_mobile/screens/auth/login.dart';
import 'package:frontend_mobile/screens/auth/register.dart';
import 'package:frontend_mobile/screens/top-up.dart';
import 'package:frontend_mobile/screens/profile-page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Apapedia',
      routes: {
        // '/': (context) => const TopUpFormScreen(),
        '/': (context) => const LoginFormScreen(),
        '/': (context) => const ProfilePage(),
        '/register': (context) => const RegisterFormScreen(),
      },
    );
  }
}
