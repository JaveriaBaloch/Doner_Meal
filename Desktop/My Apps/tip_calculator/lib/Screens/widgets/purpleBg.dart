import 'package:flutter/material.dart';

PurpleBg(){
  return const BoxDecoration(
          image: DecorationImage(
              image: AssetImage("assets/bg.png"),
              fit: BoxFit.cover),
        );
}