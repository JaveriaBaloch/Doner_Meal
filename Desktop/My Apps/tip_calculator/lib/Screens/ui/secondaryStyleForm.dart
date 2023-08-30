import 'package:flutter/material.dart';

SecondaryStyleForm(){
  return   ElevatedButton.styleFrom(
    padding: EdgeInsets.symmetric(horizontal: 20, vertical: 12), // Adjust padding here
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(8.0),
    ),
    primary: Colors.transparent,
    onPrimary: Colors.white,
    shadowColor: Colors.transparent,
    elevation: 4.0,
);
}