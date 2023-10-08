import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/Screens/ui/UpdateBtn.dart';
import 'package:tip_calculator/Screens/widgets/InputTextFlieds.dart';
import 'package:tip_calculator/Screens/widgets/bottomNavigation.dart';
import 'package:tip_calculator/Screens/widgets/logoAndText.dart';
import 'package:tip_calculator/utils/onTapNav.dart';

class Settings extends StatefulWidget {
  @override
  _SettingsState createState() => _SettingsState();
}

class _SettingsState extends State<Settings> {
  TextEditingController userName = TextEditingController();
  TextEditingController email = TextEditingController();

  @override
  void initState() {
    super.initState();
    loadUserData();
  }

  void loadUserData() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      userName.text = prefs.getString('name') ?? '';
      email.text = prefs.getString('email') ?? '';
    });
  }

  void saveUserData() async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('name', userName.text);
    prefs.setString('email', email.text);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: null,
      body: Container(
        padding: EdgeInsets.symmetric(vertical: 60, horizontal: 20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            LogoAndText(Color.fromARGB(255, 55, 2, 95)),
            SizedBox(height: 30,),
            Container(
              width: 300.0, // Set the desired width
              child: Center(
                child: Column(
                  children: <Widget>[
                    InputTextFlied('Name', userName),
                    InputTextFlied('Email', email),
                    UpdateButton(emailController: email, nameController: userName,context: context,)
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: CustomBottomNavigationBar(
        selectedIndex: 2,
        onItemSelected: (index) => onTabTapped(index, context),
      ),
    );
  }
}
