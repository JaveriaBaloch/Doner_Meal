import 'package:flutter/material.dart';
import 'package:tip_calculator/Screens/ui/secondaryButton.dart';
import 'package:tip_calculator/models/HistoryObject.dart';
import 'package:tip_calculator/Screens/widgets/bottomNavigation.dart';
import 'package:tip_calculator/utils/onTapNav.dart';
import 'package:tip_calculator/FileManager/storageHelper.dart';

class HistoryScreen extends StatefulWidget {
  @override
  _HistoryScreenState createState() => _HistoryScreenState();
}

class _HistoryScreenState extends State<HistoryScreen> {
  final TextEditingController searchController = TextEditingController();
  bool isSearching = false;
  List<HistoryObject> retrievedData = [];

  @override
  void initState() {
    super.initState();
    fetchData();
  }

  void fetchData() async {
    retrievedData = await StorageHelper().readFromFile('data.json');
    setState(() {});
  }

  List<HistoryObject> getDisplayedData() {
    if (isSearching && searchController.text.isNotEmpty) {
      return retrievedData.reversed
          .where((item) => item.location.toString().toLowerCase()
              .toString()
              .contains(searchController.text.toLowerCase()))
          .toList();
    } else {
      return retrievedData.reversed.toList();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child:  Padding(
        padding: EdgeInsets.only(top:60.0,left: 5,right: 5, bottom: 0),
        child: Column(
          children: [
            Container(
              width: MediaQuery.of(context).size.width*0.9,
          child:
              TextField(
                controller: searchController,
                onChanged: (value) {
                  setState(() {
                    isSearching = value.isNotEmpty;
                  });
                },
                onSubmitted: (value) {
                  setState(() {
                    isSearching = value.isNotEmpty;
                  });
                },
                decoration: InputDecoration(
                  hintText: 'Search by references',
                  prefixIcon: Icon(Icons.search),
                  suffixIcon: isSearching
                      ? IconButton(
                          icon: Icon(Icons.clear),
                          onPressed: () {
                            searchController.clear();
                            setState(() {
                              isSearching = false;
                            });
                          },
                        )
                      : null,
                ),
              ),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: getDisplayedData().length,
                itemBuilder: (context, index) {
                  final displayedData = getDisplayedData();
                  return ListTile(
                    title: Card(
                      child: Padding(
                        padding: EdgeInsets.only(
                            top: 10, bottom: 10, left: 10, right: 0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Column(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  'Your bill:  \$${(displayedData[index].your_bill + displayedData[index].your_tip).toStringAsFixed(2)}',
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                    color: Color.fromARGB(255, 45, 5, 76),
                                    fontWeight: FontWeight.bold,
                                    fontSize: 16,
                                  ),
                                ),
                                SizedBox(height: 6),

                                Text(
                                    'Reference:  ${displayedData[index].location.isNotEmpty?displayedData[index].location:'None'}',
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      color: Color.fromARGB(255, 45, 5, 76),
                                      fontWeight: FontWeight.w500,
                                      fontSize: 14,
                                    ),
                                  ),
                                   SizedBox(height: 6),
                                Text(
                                  'Date: ${displayedData[index].date.toString()}',
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                      color: Color.fromARGB(255, 45, 5, 76),
                                      fontSize: 14,
                                      fontWeight: FontWeight.w500,
                                      ),

                                ),
                              ],
                            ),
                            SecondaryButton(
                                context, displayedData[index].index)
                          ],
                        ),
                      ),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
      ),
      bottomNavigationBar: CustomBottomNavigationBar(
        selectedIndex: 0,
        onItemSelected: (index) => onTabTapped(index, context),
      ),
    );
  }
}
