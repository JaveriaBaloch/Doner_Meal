import 'package:flutter/material.dart';

BarCard(title,text){
  return  Card(
                          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                          child: Padding(
                            padding: EdgeInsets.all(10),
                            child: Text(
                              
                              '$title:  $text',
                              style: TextStyle(
                                fontSize: 18,
                                fontWeight: FontWeight.bold,
                                color: Color.fromARGB(255, 86, 3, 97),
                              ),
                              textAlign: TextAlign.center,
                            ),
                          ),
                        );
}