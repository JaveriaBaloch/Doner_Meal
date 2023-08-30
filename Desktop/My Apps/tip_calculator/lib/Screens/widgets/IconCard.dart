import 'package:flutter/material.dart';

IconCard(String smallText,String bigText,Icon icon){
return Card(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(16.0), // Adjust the radius as needed
    ),
    child: Container(
      width: 150,
      height: 120,
      // Add your card content here
      child: Center(
        child: Padding(
          padding: EdgeInsets.only(top: 10, right: 10,bottom: 10,left: 10),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
             Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children:[ 
                icon,
                Text(
                bigText,
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold, color: Color.fromARGB(255, 86, 3, 97)),
              )]),
              SizedBox(height: 10),
              Text(
                smallText,
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Color.fromARGB(255, 86, 3, 97)),
              ),
            ],
          ),
        ),
      ),
    ),
  );
}