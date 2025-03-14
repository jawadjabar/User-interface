import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter UI Example'),
        backgroundColor: Colors.blue,
        bottom: TabBar(
          controller: _tabController,
          tabs: [
            Tab(icon: Icon(Icons.input), text: 'Input'),
            Tab(icon: Icon(Icons.list), text: 'List/Grid'),
          ],
        ),
      ),
      body: TabBarView(
        controller: _tabController,
        children: [
          InputScreen(),
          ListGridViewScreen(),
        ],
      ),
    );
  }
}

class InputScreen extends StatelessWidget {
  final TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        children: [
          TextField(
            controller: _controller,
            decoration: InputDecoration(
              labelText: 'Enter Name',
              border: OutlineInputBorder(),
            ),
          ),
          SizedBox(height: 20),
          ElevatedButton(
            onPressed: () {
              final inputText = _controller.text;
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text('Submitted: $inputText')),
              );
            },
            child: Text('Submit'),
          ),
        ],
      ),
    );
  }
}

class ListGridViewScreen extends StatelessWidget {
  final List<String> items = List.generate(6, (index) => 'Item ${index + 1}');

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: items.length,
              itemBuilder: (context, index) {
                return Card(
                  child: ListTile(
                    leading: Icon(Icons.star),
                    title: Text(items[index]),
                  ),
                );
              },
            ),
          ),
          Divider(),
          Expanded(
            child: GridView.count(
              crossAxisCount: 2,
              children: List.generate(items.length, (index) {
                return Card(
                  child: Center(
                    child: Text(items[index]),
                  ),
                );
              }),
            ),
          ),
        ],
      ),
    );
  }
}