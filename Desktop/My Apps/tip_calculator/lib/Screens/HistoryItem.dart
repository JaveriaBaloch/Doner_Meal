import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/widgets/IconCard.dart';
import 'package:tip_calculator/Screens/widgets/barCard.dart';
import 'package:tip_calculator/Screens/widgets/percentageCard.dart';
import 'package:tip_calculator/Screens/widgets/smallCard.dart';
import 'package:tip_calculator/models/HistoryObject.dart';
import 'package:tip_calculator/Screens/widgets/bottomNavigation.dart';
import 'package:tip_calculator/utils/onTapNav.dart';

class HistoryItem extends StatelessWidget {
  final HistoryObject item; // Declare the type of item

  HistoryItem({required this.item}); // Use the this keyword to assign the parameter

  @override
  Widget build(BuildContext context) {
    String locationText = item.location ?? 'None';
    bool containsAlphabets = locationText.contains(RegExp(r'[a-zA-Z]'));
    print(item);
    return Scaffold(
      appBar: null,
      body: Center(
        child: Container(
          padding: EdgeInsets.only(top: 0,left: 30,right: 30),
        
          child: Expanded(
          
          child: ListView(
  children: [
    SmallCard("Your total bill",'\$${(item.your_bill+item.your_tip).toStringAsFixed(2)}'),
    Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Flexible(
          flex: 2,
          child: IconCard("No. of People", item.peoples.toString(), Icon(Icons.people, size: 50,color: Color.fromARGB(255, 86, 3, 97),)),
        ),
        Flexible(
          flex: 2,
          child: CircularPercentageProgressBar(percentage: double.parse(item.percentage.toString()), text: "Tip percentage"),
        ),
      ],
    ),
    BarCard("Location", containsAlphabets ? locationText : "None"),
    Row(
      children: [
        Flexible(
          flex: 2,
          child: SmallCard("Your tip amount", '\$${item.your_tip.toStringAsFixed(2)}'),
        ),
        Flexible(
          flex: 2,
          child: SmallCard("Your bill amount", '\$${item.your_bill.toStringAsFixed(2)}'),
        ),
      ],
    ),
    Row(
  children: [
    Flexible(
      child: SmallCard("Group bill amount", '\$${item.group_total}'),
    ),
    Flexible(
      child: SmallCard("Group tip amount", '\$${(double.parse(item.group_tip)).toStringAsFixed(2)}'),
    ),
  ],
  
),
  
    SmallCard("Group total", '\$${item.group_bill.toStringAsFixed(2)}'),
  
  ],
),
          )
        ),
      ),
        bottomNavigationBar: CustomBottomNavigationBar( selectedIndex: 0,
onItemSelected: (index) => onTabTapped(index,context)
    )
    );
  }
}
