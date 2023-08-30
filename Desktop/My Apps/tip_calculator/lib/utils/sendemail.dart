 import 'package:mailer/mailer.dart';
import 'package:mailer/smtp_server/gmail.dart';
import 'package:tip_calculator/utils/env.dart';

Future<void> SendEmailNotification(getMessage,name,oldemail,subject) async {
   final smtpServer = gmail(emailId(), password());
   final message = Message()
     ..from = Address('tipcalculator34@gmail.com', 'Tip Cal')
     ..recipients.add(oldemail) // Recipient's email address
     ..subject = subject
     ..html = getMessage;
   try {
     final sendReport = await send(message, smtpServer);
     print('Message sent: ${sendReport.toString()}');
   } on MailerException catch (e) {
     print('Message not sent. Error: $e');
   }
 }