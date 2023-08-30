import 'package:flutter/material.dart';
import 'package:tip_calculator/FileManager/storageHelper.dart';
import 'package:tip_calculator/Screens/HistoryItem.dart';
import 'package:tip_calculator/Screens/ui/secodarybuttonInk.dart';
import 'package:tip_calculator/Screens/ui/secondaryStyleForm.dart';
import 'package:tip_calculator/models/HistoryObject.dart';

SecondaryButton(context, index) {
  return ElevatedButton(
    onPressed: () async {
      List<HistoryObject> retrievedData =
          await StorageHelper().readFromFile('data.json').then((value) => value);
      List<HistoryObject> Data = retrievedData;
      HistoryObject item = Data[index];
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => HistoryItem(item: item),
        ),
      );
    },
    child: SecondaryInk('Details',30,14.0),
    style: SecondaryStyleForm()
  );
}
