import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/Screens/HomeScreen.dart';
import 'package:tip_calculator/Screens/SetUpScreen1.dart';

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  late SharedPreferences prefs;
  bool? isLoggedIn;

  @override
  void didChangeDependencies() async {
    super.didChangeDependencies();
    
    // Initialize the SharedPreferences object
    prefs = await SharedPreferences.getInstance();
    
    // Get the value of isLoggedIn from SharedPreferences
    isLoggedIn = prefs.getBool('isLoggedIn');
    
    // Rebuild the widget tree with the updated isLoggedIn value
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: isLoggedIn == null
            ? CircularProgressIndicator()
            : isLoggedIn!
                ? HomeScreen()
                : SetUpScreen1(),
      ),
    );
  }
}
