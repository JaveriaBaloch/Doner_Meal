import 'package:flutter/material.dart';

class CustomBottomNavigationBar extends StatelessWidget {
  
  final int selectedIndex;
  final ValueChanged<int> onItemSelected;

  CustomBottomNavigationBar({
    required this.selectedIndex,
    required this.onItemSelected,
  });
  

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      currentIndex: selectedIndex,
      onTap: onItemSelected,
      
      items: <BottomNavigationBarItem>[
        BottomNavigationBarItem(
          icon: Padding(
            padding: EdgeInsets.all(8.0),
            child: Icon(Icons.attach_money_sharp, size: 30),
          ),
          label: 'History',
        ),
        BottomNavigationBarItem(
          icon: Padding(
            padding: EdgeInsets.all(8.0),
            child: Icon(Icons.calculate_outlined, size: 30),
          ),
          label: 'Calculator',

        ),
        BottomNavigationBarItem(
          icon: Padding(
            padding: EdgeInsets.all(8.0),
            child: Icon(Icons.settings, size: 30),
          ),
          label: 'Settings',
        ),
      ],
      backgroundColor: Color.fromARGB(255, 86, 3, 97),
      selectedItemColor: Colors.white,
      unselectedItemColor: Colors.grey,
       selectedLabelStyle: TextStyle(fontWeight: FontWeight.bold), // Style for selected label
      unselectedLabelStyle: TextStyle(fontWeight: FontWeight.bold), // Style for unselected label
    );
  }
}
