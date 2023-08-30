class HistoryObject {
  dynamic index;
  dynamic group_bill;
  dynamic group_tip;
  dynamic group_total;
  dynamic location;
  dynamic peoples;
  dynamic percentage;
  dynamic your_bill;
  dynamic your_tip;
  String? date;

  HistoryObject(
    this.index,
    this.group_bill,
    this.group_tip,
    this.group_total,
    this.location,
    this.peoples,
    this.percentage,
    this.your_bill,
    this.your_tip,
    this.date
  );

  // Convert the object to a map for JSON serialization
  Map<String, dynamic> toJson() {
    return {
      'index':index,
      'group_bill': group_bill,
      'group_tip':  group_tip,
      'group_total': group_total,
      'location':  location,
      'peoples':  peoples,
      'percentage':  percentage,
      'your_bill':  your_bill,
      'your_tip':  your_tip,
      'date':  date
    };
  }

  // Create an instance from a map
  factory HistoryObject.fromJson(Map<String, dynamic> json) {
    return HistoryObject(
      json['index'],
      json['group_bill'],
      json['group_tip'],
      json['group_total'],
      json['location'],
      json['peoples'],
      json['percentage'],
      json['your_bill'],
      json['your_tip'],
      json['date']
    );
  }
}
