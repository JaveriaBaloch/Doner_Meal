import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/Screens/ui/secodarybuttonInk.dart';
import 'package:tip_calculator/Screens/ui/secondaryStyleForm.dart';
import 'package:tip_calculator/utils/sendemail.dart';
import 'package:tip_calculator/utils/dailog.dart';

class UpdateButton extends StatelessWidget {
  final TextEditingController emailController;
  final TextEditingController nameController;
  final context;

  UpdateButton({
    required this.emailController,
    required this.nameController,
    required this.context
  });
  _showAlertDialog(context,message){
   
    DialogAlertMessage(context, message);
  }

  void _saveDataToPreferences() async {

    final prefs = await SharedPreferences.getInstance();
    String? name= prefs.getString('name');
    String? email=prefs.getString('email');
    String message='Hi $name,\n\nWe wanted to inform you that your email address has been successfully updated to $name or ${email}. If you have any questions or concerns, please feel free to reach out to us.\n\nBest Regards,\nTip Calculator Team';
    SendEmailNotification(message, name, email,"Profile Update");
    prefs.setString('name', nameController.text);
    prefs.setString('email', emailController.text);
  }

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () async {
        final emailRegExp = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$');

        if (nameController.text.isEmpty) {
          _showAlertDialog(context, 'Please enter your name.');
        } else if (!emailRegExp.hasMatch(emailController.text) || emailController.text.isEmpty) {
          _showAlertDialog(context, 'Please enter a valid email.');
        } else {
          _saveDataToPreferences();
          _showAlertDialog(context, 'Data updated successfully and notification sent!');
        }
      },
      child: SecondaryInk('Update', 45, 16.0),
      style: SecondaryStyleForm(),
    );
  }
}
