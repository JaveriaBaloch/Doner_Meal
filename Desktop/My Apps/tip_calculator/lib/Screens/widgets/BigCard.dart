import 'package:flutter/material.dart';

BigCard(String text, String text2){
  return  Card(
    
   shape:RoundedRectangleBorder(
    borderRadius: BorderRadius.circular(25.0), // Adjust the radius as needed
  ),
  child:Expanded(
                      child: Padding(
                        padding: EdgeInsets.only(top:17,bottom: 17, right: 28, left: 28),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(text,style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold,color: Color.fromARGB(255, 86, 3, 97),),),
                            Text(text2,style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold, color: Color.fromARGB(255, 86, 3, 97)),)

                          ]),
                      )
                    )
  );
}