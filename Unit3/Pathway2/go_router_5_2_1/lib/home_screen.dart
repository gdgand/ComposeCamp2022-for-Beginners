import 'package:flutter/material.dart';
import 'package:go_router_5_2_1/main.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          children: [
            const Text('Home'),
            TextButton(
              onPressed: () {
                final Future<bool?> resultFuture = showDialog<bool>(
                  context: root.currentContext!,
                  builder: (_) => Scaffold(
                    body: Center(
                      child: Column(
                        children: const [
                          Text('Shell'),
                          Expanded(
                            child: Text('A dialog'),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              },
              child: const Text("next page"),
            )
          ],
        ),
      ),
    );
  }
}
