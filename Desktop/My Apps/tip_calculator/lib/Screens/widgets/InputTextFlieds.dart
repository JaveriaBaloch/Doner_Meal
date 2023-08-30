import 'package:flutter/material.dart';

InputTextFlied(String placeholder,  userName){
  return Container(
    width: 290,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20),
              boxShadow: [
                BoxShadow(
                  color: Color.fromARGB(255, 175, 171, 171).withOpacity(0.5),
                  spreadRadius: 1,
                  blurRadius: 7,
                  offset: Offset(3, 3),
                ),
              ],
              border: Border.all(color: const Color.fromARGB(255, 156, 156, 156).withOpacity(0.5), width: 1.0),
            ),
          child:TextField(
                  style: TextStyle(
                  color: Color.fromARGB(255, 45, 5, 76),
                  fontSize: 15 // Text color
                ),
                decoration: InputDecoration(
                  contentPadding: EdgeInsets.only(left: 16.0,top:10, bottom: 10, right: 16), // Add left padding
                  hintText: placeholder,
                  border: OutlineInputBorder(
                      borderSide: BorderSide.none, // No border
                      borderRadius: BorderRadius.circular(70.0), // Adjust the value as needed
                    ),
                  filled: true,
                  fillColor: Colors.white,
                    hintStyle: TextStyle(
                    color: Colors.grey,
                    fontWeight: FontWeight.bold // Text color
// Color of the placeholder text
              ),
            ),
            controller: userName,
          )
  );
}