import 'package:flutter/material.dart';

LogoAndText(Color colour){
  return  Row(
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(5), // Adjust the radius as needed
                child: Image(
                  image: AssetImage('assets/logo.png'),
                  width: 70,
                  height: 70,
                ),
              ),
              SizedBox(width: 10,),
              Text("Tip\nCalculator", style: TextStyle(color: colour,fontSize: 20,fontWeight: FontWeight.bold),)
            ],
          );
}