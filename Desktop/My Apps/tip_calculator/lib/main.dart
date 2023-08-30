// import 'package:firebase_admob/firebase_admob.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/Screens/HomeScreen.dart';
import 'package:tip_calculator/Screens/SetUpScreen1.dart';

Future<void> main() async {
      WidgetsFlutterBinding.ensureInitialized();
      // FirebaseAdMob.instance.initialize(appId: 'ca-app-pub-9064052677511324~1107017435');
      // await MobileAds.instance.initialize();
      // RequestConfiguration requestConfiguration= RequestConfiguration(
        // testDeviceIds: ['1FA831D1-E0B6-4916-8F00-B78BA07491DA']
      // );
      // MobileAds.instance.updateRequestConfiguration(requestConfiguration);
      SharedPreferences prefs = await SharedPreferences.getInstance();
      // FirebaseAdMob.instance.initialize(
      // appId: 'ca-app-pub-9064052677511324~4686583061',
    // );
      var email = prefs.getString('email');
      print(email);
      runApp(
        MaterialApp(
          debugShowCheckedModeBanner: false,
          home: email == null ? SetUpScreen1() : HomeScreen()
          )
        );
    }