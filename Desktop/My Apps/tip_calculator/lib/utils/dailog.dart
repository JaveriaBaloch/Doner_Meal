import 'package:flutter/material.dart';

DialogAlertMessage(BuildContext context, String message) {
   return showDialog(
  context: context,
  builder: (BuildContext context) {
    return AlertDialog(
      
      content: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(30)
          ),
        height: 100, // Set your desired height
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              message,
              style: TextStyle(
                color: Color.fromARGB(255, 114, 0, 127), // Text color
                fontSize: 17, 
                  fontWeight: FontWeight.bold// Button text color
                // Text size
              ),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 10),
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text(
                'Close',
                style: TextStyle(
                  color: Color.fromARGB(255, 255, 255, 255), 
                  fontWeight: FontWeight.bold// Button text color
                ),
              ),

              style: TextButton.styleFrom(backgroundColor: Color.fromARGB(255, 114, 0, 127)),
              
            ),
          ],
        ),
      ),
    );
  },
);
  }