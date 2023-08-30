import 'package:flutter/material.dart';

SmallCard(String smallText,String bigText){
return Card(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(16.0), // Adjust the radius as needed
    ),
    
    child: Container(
      // Add your card content here
      child: Center(
        child: Padding(
          padding: EdgeInsets.only(top: 14, right: 14,bottom: 14,left: 14),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                bigText,
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color.fromARGB(255, 86, 3, 97)),
              ),
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