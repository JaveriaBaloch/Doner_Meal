
import 'package:flutter/material.dart';
import 'package:google_mobile_ads/google_mobile_ads.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/Screens/SetUpScreen1.dart';
import 'package:tip_calculator/Screens/ui/rewardAd.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // Initialize Google Mobile Ads
  MobileAds.instance.initialize();

  SharedPreferences prefs = await SharedPreferences.getInstance();

  // Get user's email from preferences
  var email = prefs.getString('email');
  print(email);

  runApp(
    MaterialApp(
      debugShowCheckedModeBanner: false,
      home: email == null ? SetUpScreen1() : AdReward(),
    ),
  );

}