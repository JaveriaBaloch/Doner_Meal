import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/History.dart';
import 'package:tip_calculator/Screens/HomeScreen.dart';
import 'package:tip_calculator/Screens/Settings.dart';

void onTabTapped(int index, BuildContext context) {
    // Navigate to the corresponding screen based on the index
    switch (index) {
      case 0:
        // Navigate to History screen
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => HistoryScreen()), // Replace with your actual screen
        );
        break;
      case 1:
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => HomeScreen())
          ); // Replace with your actual screen
        break;
      case 2:
        // Navigate to Settings screen
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => Settings())
          ); // Replace with your actual screen
        break;
    }
  }