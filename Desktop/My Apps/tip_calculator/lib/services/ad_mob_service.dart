// import 'dart:io';

// import 'package:google_mobile_ads/google_mobile_ads.dart';

// class AdHelper {

//   static String get bannerAdUnitId {
//     if (Platform.isAndroid) {
//       return 'ca-app-pub-9064052677511324/8410792380';
//     } else if (Platform.isIOS) {
//       return 'ca-app-pub-9064052677511324/2436410979';
//     } else {
//       throw UnsupportedError('Unsupported platform');
//     }
//   }

//   static String get interstitialAdUnitId {
//     if (Platform.isAndroid) {
//       return 'ca-app-pub-9064052677511324/8410792380';
//     } else if (Platform.isIOS) {
//       return 'ca-app-pub-9064052677511324/2436410979';
//     } else {
//       throw UnsupportedError('Unsupported platform');
//     }
//   }

//   static String get rewardedAdUnitId {
//     if (Platform.isAndroid) {
//       return 'ca-app-pub-9064052677511324/8410792380';
//     } else if (Platform.isIOS) {
//       return 'ca-app-pub-9064052677511324/2436410979';
//     } else {
//       throw UnsupportedError('Unsupported platform');
//     }
//   }
//   static final BannerAdListener bannerAdListener = BannerAdListener(
//   onAdLoaded: (ad) {print('Ad is loaded');},
//   onAdFailedToLoad: (ad, error) {
//     ad.dispose();
//     print(error);
//   },
//   onAdOpened: (ad){print('Ad is open');},
//   onAdClosed: (ad){print('Ad is closed');}
// );
// }