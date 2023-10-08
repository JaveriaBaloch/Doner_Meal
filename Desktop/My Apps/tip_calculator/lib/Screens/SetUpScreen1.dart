import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/SetUpScreen2.dart';
import 'package:tip_calculator/Screens/widgets/InputTextFlieds.dart';
import 'package:tip_calculator/Screens/widgets/logoAndText.dart';
import 'package:tip_calculator/Screens/widgets/primaryButton.dart';
import 'package:tip_calculator/Screens/widgets/purpleBg.dart';

class SetUpScreen1 extends StatefulWidget {
  @override
  _SetUpScreen1 createState() => _SetUpScreen1();
}

class _SetUpScreen1 extends State<SetUpScreen1> {
  final _formKey = GlobalKey<FormState>(); // Add a form key
  TextEditingController userName = TextEditingController();
  StatefulWidget screenToMove = SetUpScreen2();

  String? validateName(String value) {
    if (value.isEmpty) {
      return 'Name is required';
    }
    return null; // Return null if validation passes
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: null,
      body: Center(
        child: Container(
          padding: EdgeInsets.only(top: 60.0, left: 26.0, right: 26.0),
          constraints: BoxConstraints.expand(),
          decoration: PurpleBg(),
          child:  Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              LogoAndText(Color.fromARGB(255, 255, 255, 255)),
              SizedBox(height: 40,),
              Container(
                width: 300.0,
                height: MediaQuery.of(context).size.height *0.3,
                    // 0.5, // 60% of screen height
                child: Form(
                  key: _formKey, // Set the form key
                  child: Center(
                    child: Column(
                      children: <Widget>[
                        InputTextFlied(
                          'Name',
                          userName,
                        ),
                        PrimaryButton(context: context, userName: userName, NextScreen: SetUpScreen2(), type: 'name')
                      ]
                    ),
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
