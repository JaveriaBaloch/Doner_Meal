import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/widgets/BigCard.dart';
import 'package:tip_calculator/Screens/widgets/InputTextFlieds.dart';
import 'package:tip_calculator/Screens/widgets/barCard.dart';
import 'package:tip_calculator/Screens/widgets/bottomNavigation.dart';
import 'package:tip_calculator/Screens/widgets/numberInput.dart';
import 'package:tip_calculator/Screens/widgets/secondaryButton.dart';
import 'package:tip_calculator/Screens/widgets/smallCard.dart';
import 'package:tip_calculator/utils/onTapNav.dart';

void onTextChanged(TextEditingController controller, Function(String) onChanged) {
  controller.addListener(() {
    onChanged(controller.text);
  });
}

class HomeScreen extends StatefulWidget {

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  
  TextEditingController total_Bill = TextEditingController();
  TextEditingController peoples = TextEditingController();
  TextEditingController references = TextEditingController();
  double your_total = 0.00;
  double your_tip = 0.00;
  double your_bill = 0.00;
  int tip_percent = 0;
  String tip = '0';
  double total_amount =0;
  String ref='';
   @override
  void initState() {
    
    super.initState();

    // Add text input formatters to limit input to numeric values
    total_Bill..addListener(() => total_tip_amount());
    peoples..addListener(() => total_tip_amount());
    // references..addListener(() => total_tip_amount()); // Add this line
//  myBanner
    // ..load()
    // ..show(
      // anchorType: AnchorType.bottom,
    // );
  }

@override
void dispose() {
  // myBanner.dispose();
  super.dispose();
}
  @override
  Widget build(BuildContext context) {
   
    return Scaffold(
      appBar: null,
      body: OrientationBuilder(
        
        builder: (BuildContext context, Orientation orientation) {
          return Center(
            child: Container(
              
              padding: EdgeInsets.only(top: 10.0, left: 20.0,right: 20),
              child: Column(
                children: <Widget>[
                  Expanded(
                    child: ListView(
                      children: [
                        SizedBox(height: 20,),
                        NumberInput('Total Bill', total_Bill),
                        BarCard('Tip: \$', tip),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              '$tip_percent%',
                              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 17, color: Color.fromARGB(255, 86, 3, 97)),
                            ),
                            Flexible(
                              flex: 1,
                              child:Slider(
                              value: tip_percent.toDouble(),
                              min: 0,
                              max: 100,
                              inactiveColor: Color.fromARGB(255, 219, 166, 250),
                              activeColor: Color.fromARGB(255, 86, 3, 97),
                              onChanged: (newValue) {
                                setState(() {
                                  // Update your state variables here based on newValue
                                  tip_percent = newValue.toInt();
                                  total_tip_amount(newValue);
                                });
                              },
                            ),


                            ),
                            Text(
                              '100%',
                              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 17, color: Color.fromARGB(255, 86, 3, 97)),
                            ),
                          ],
                        ),
                        NumberInput('Persons', peoples),
                        InputTextFlied('Reference: Location or Title (Optional)', references),
                        Container(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Expanded(
                                child: Center(
                                  child: SmallCard('Tip per\nperson', your_tip.toStringAsFixed(2)),
                                ),
                              ),
                              Expanded(
                                child: Center(
                                  child: SmallCard('Bill per\nperson', your_bill.toStringAsFixed(2)),
                                ),
                              ),

                            ],
                          ),
                        ),
                        Flexible(
                          child: BigCard('Your\nTotal', your_total.toStringAsFixed(2)),
                          ),
                        SaveButton(context,total_amount, tip,total_Bill.text,ref,peoples.text,tip_percent,your_bill,your_tip)
                      ],
                    ),
                  ),
                ],
              ),
            ),
          );
        },
      ),
      bottomNavigationBar: CustomBottomNavigationBar( selectedIndex: 1,
    onItemSelected: (index) => onTabTapped(index,context)
    )
    );
  }
  void total_tip_amount([double newValue = 0]) {
  if (total_Bill.text.isNotEmpty && peoples.text.isNotEmpty) {
    tip_percent = newValue.toInt();
    double? billValue = double.tryParse(total_Bill.text);
    int numberOfPeople = int.tryParse(peoples.text) ?? 1;
    if (billValue != null) {
      double tipValue = (newValue / 100) * billValue;
      tip = tipValue.toStringAsFixed(2);
      total_amount = billValue + tipValue;
      ref = references.text; // Update the location value
      your_tip = tipValue / numberOfPeople;
      your_bill = billValue / numberOfPeople;
      your_total = your_bill + your_tip;

      setState(() {
        // Updating the UI to reflect the changes
      });

      print('your_total: $your_total');
    } else {
      tip = 'Invalid input';
      your_tip = 0.00;
      your_bill = 0.00;
    }
  } else {
    tip = 'Enter bill amount and number of people';
    your_tip = 0.00;
    your_bill = 0.00;
  }
}

}
