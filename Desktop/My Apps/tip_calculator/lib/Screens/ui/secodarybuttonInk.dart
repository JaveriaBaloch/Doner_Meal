import 'package:flutter/material.dart';

SecondaryInk(String title,double height,double fontSize){
  return Ink(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [Color.fromARGB(255, 105, 4, 114), Color.fromARGB(255, 62, 2, 100)],
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
        ),
        borderRadius: BorderRadius.circular(26.0),
      ),
      child: Container(
        constraints: BoxConstraints(minWidth: 100, minHeight: height), // Adjust the size here
        alignment: Alignment.center,
        child: Text(
          title,
          style: TextStyle(
            fontSize: fontSize, // Adjust the font size
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
    );
}