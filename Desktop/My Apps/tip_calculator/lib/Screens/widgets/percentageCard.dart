import 'package:flutter/material.dart';

class CircularPercentageProgressBar extends StatelessWidget {
  final double percentage;
  final String text;

  CircularPercentageProgressBar({required this.percentage, required this.text});

  @override
  Widget build(BuildContext context) {
    return 
      Card(
        shape:RoundedRectangleBorder(
 borderRadius: BorderRadius.circular(25.0), // Adjus
        ),
        child:
        Container(
          padding: EdgeInsets.only(top: 10, right: 10,bottom: 10,left: 10),
           width: 150,
           height: 120,
          alignment: Alignment.center,
          child:
         Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [

              Stack(
                alignment: Alignment.center,
                children: [
                   SizedBox(
                    width: 70, height: 70, 
                      child: CircularProgressIndicator(
                          value: percentage / 100,
                          strokeWidth: 10,
                          backgroundColor: Color.fromARGB(255, 247, 223, 255),
                          valueColor: AlwaysStoppedAnimation<Color>(Color.fromARGB(255, 107, 12, 136)),
                      )
                  ),
                  Text(
                    '$percentage%',
                    style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      color: Color.fromARGB(255, 107, 12, 136)
                    ),
                  ),
                ],
              ),
           
            Text(text,  style: TextStyle(
   fontSize: 13,
   fontWeight: FontWeight.bold,
   color: Color.fromARGB(255, 71, 0, 93)
 ),),
          ],
        )
        ),
      );
  }
}
