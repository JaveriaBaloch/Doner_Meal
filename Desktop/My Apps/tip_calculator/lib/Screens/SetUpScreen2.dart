// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/HomeScreen.dart';
import 'package:tip_calculator/Screens/ui/rewardAd.dart';
import 'package:tip_calculator/Screens/widgets/InputTextFlieds.dart';
import 'package:tip_calculator/Screens/widgets/logoAndText.dart';
import 'package:tip_calculator/Screens/widgets/primaryButton.dart';
import 'package:tip_calculator/Screens/widgets/purpleBg.dart';

class SetUpScreen2  extends StatefulWidget {
  @override
  _SetUpScreen2  createState() => _SetUpScreen2();
}

class _SetUpScreen2 extends State<SetUpScreen2>{
  TextEditingController userName = TextEditingController();
  @override
  Widget build(BuildContext context){    
    return Scaffold(
      appBar: null,
      body: Center(
      child: Container(
        padding: EdgeInsets.only(top: 60.0,left: 26.0, right: 26.0),
        constraints: BoxConstraints.expand(),

        decoration: PurpleBg(),
        child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          LogoAndText(Color.fromARGB(255, 255, 255, 255)),
           SizedBox(height: 40,),
          Container(
              width: 300.0, // Set the desired width  
                height: MediaQuery.of(context).size.height *0.3,
          child: Center(
            child: Column(children: <Widget>[
              InputTextFlied('Email', userName),
                        PrimaryButton(context: context, userName: userName, NextScreen: AdReward(), type: 'email')
          ]),
        )
        )
        ],
        ),
    )
    )
  );
}
}