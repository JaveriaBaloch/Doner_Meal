import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/utils/dailog.dart';
import 'package:tip_calculator/utils/sendemail.dart';

class PrimaryButton extends StatelessWidget {
  final BuildContext context;
  final TextEditingController userName;
  final StatefulWidget NextScreen;
  final String type;

  PrimaryButton({
    required this.context,
    required this.userName,
    required this.NextScreen,
    required this.type,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () async {
        if (type == 'name' && userName.text.isEmpty) {
 
  DialogAlertMessage(context, "Enter your name Please");

        }if(type == 'name' && userName.text.isNotEmpty){
            final prefs = await SharedPreferences.getInstance();
            prefs.setString('name', userName.text);
                    Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => NextScreen),
                    );
             Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => NextScreen),
            );
        }
        if (type == 'email') {
          final emailRegExp = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$');
          if (!emailRegExp.hasMatch(userName.text) || userName.text.isEmpty) {    
           
           
           DialogAlertMessage(context, "Enter a valid email, please");

          } 
          if(emailRegExp.hasMatch(userName.text) && userName.text.isNotEmpty){
            // Save email in preferences
            final prefs = await SharedPreferences.getInstance();
                        String? name= prefs.getString('name');

String message = '''
<div style="background:#4E0046;width:100%; margin:auto;padding-top:2rem;padding-bottom:4rem;">
  <h1 style="background:#4E0046;width:100%; margin:auto;padding-bottom:2rem; text-align:center;color:white">Welcome to your Tip Cal World</h1>
  <p style="background:white;width:30rem; margin:auto;padding:2rem; color:#4E0046">Hi $name,<br><br>
  Welcome to Tip Cal! We hope you're doing well. Now we can keep you updated about your tips, your group money division, and even the locations where you spend your money. You will receive email notifications with calculations. The app also displays your history, which is the only thing you can see. Even the Tip Cal App doesn't have access to your saved history.<br>
  So feel secure and use the app to solve your money division problems anywhere, anytime.<br>
  If you have any query reach out to us on this email address, We will be very happy to have you.

  Have a nice day!<br><br>
  <br><br>
  Tip Cal,<br>
  Best Regards
  </p>
</h1>
''';
                SendEmailNotification(message, name, userName.text,"Welcome to your Tip Cal World!");
            prefs.setString('email', userName.text);
             Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => NextScreen),
            );
          }
        }

      },
      child: Ink(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [
              Color.fromARGB(255, 201, 134, 252),
              Color.fromARGB(255, 114, 154, 246)
            ],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
          borderRadius: BorderRadius.circular(26.0),
        ),
        child: Container(
          constraints: BoxConstraints(minWidth: 38, minHeight: 46),
          alignment: Alignment.center,
          child: Text(
            'Next',
            style: TextStyle(
              fontSize: 18.0,
            ),
          ),
        ),
      ),
      style: ElevatedButton.styleFrom(
        padding: EdgeInsets.symmetric(horizontal: 80, vertical: 35),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8.0),
        ),
        primary: Colors.transparent,
        onPrimary: Colors.white,
        shadowColor: Colors.transparent,
        elevation: 4.0,
      ),
    );
  }
}
