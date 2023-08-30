
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tip_calculator/FileManager/storageHelper.dart';
import 'package:tip_calculator/Screens/ui/secodarybuttonInk.dart';
import 'package:tip_calculator/Screens/ui/secondaryStyleForm.dart';
import 'package:tip_calculator/models/HistoryObject.dart';
import 'package:intl/intl.dart';
import 'package:tip_calculator/utils/sendemail.dart'; // Import the intl package

SaveButton(BuildContext context,group_bill, group_tip,group_total,String location,peoples,percentage,your_bill,your_tip){
return ElevatedButton(
          onPressed:()  async{
            print("this is location: $location");
            final prefs = await SharedPreferences.getInstance();
            String? name= prefs.getString('name');
            String? email=prefs.getString('email');
              String getMessage = 
              '''
        <p>Hello $name,\n\nAs Promised by Tip Cal, here is the new Calculation your have just done. \n</p>
        <div style="background:#4E0046;width:100%; margin:auto;padding-top:2rem;padding-bottom:4rem;">
        <h1 style="color:white; text-align:center;;padding-bottom:1rem;">New Calculation</h1>
        <table border="1"  style="background: white;color:#4E0046;border-collapse: collapse; padding:3rem;margin:auto;width:30rem;font-size:18px; border:#4E0046 3px">
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Group Bill</th>
            <td align="right" style="padding: 15px 1.5rem;">\$${(your_bill+your_tip).toStringAsFixed(2)}</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Group Tip</th>
            <td align="right" style="padding: 15px 1.5rem;">\$${group_tip}</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Group Total</th>
            <td align="right" style="padding: 15px 1.5rem;">\$${group_total}</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Reference</th>
            <td align="right" style="padding: 15px 1.5rem;">${location}</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Peoples</th>
            <td align="right" style="padding: 15px 1.5rem;">$peoples</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Percentage</th>
            <td align="right" style="padding: 15px 1.5rem;">$percentage%</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Your Bill</th>
            <td align="right" style="padding: 15px 1.5rem;">\$${your_bill.toStringAsFixed(2)}</td>
          </tr>
          <tr>
            <th align="left" style="padding: 15px 1.5rem;">Your Tip</th>
            <td align="right" style="padding: 15px 1.5rem;">\$${your_tip.toStringAsFixed(2)}</td>
          </tr>
        </table>
        </div>
      ''';
            SendEmailNotification(getMessage, name, email,'Your New Calculation!');
            DateTime currentDate = DateTime.now();

            DateFormat dateFormat = DateFormat('dd/MM/yyyy');
            String formattedDate = dateFormat.format(currentDate);
          List<HistoryObject> retrievedData =
               await StorageHelper().readFromFile('data.json');
                HistoryObject item = HistoryObject(
   retrievedData.length,
   group_bill,
   group_tip,
   group_total,
   location,
   peoples,
   percentage,
   your_bill,
   your_tip,
   formattedDate
 );
          print(location);
          retrievedData.add(item); // Append the new item to the list
          
          await StorageHelper.writeToFile('data.json', retrievedData);
// 
  // You can also print the updated data to verify
  for (var history in retrievedData) {
    print(history.toJson());
  }

  // Write the updated list back to the file

  },
  child: SecondaryInk('Save',45,16.0),
  style: SecondaryStyleForm()
 );
}
