// ignore_for_file: use_build_context_synchronously

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend_mobile/screens/view-catalog.dart';
import 'package:frontend_mobile/utils/color_palette.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:frontend_mobile/components/drawer.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:frontend_mobile/model/Order.dart';
// import 'package:jejakarbon_flutter/apps/homepage/homePage.dart';

class OrderHistoryFormScreen extends StatefulWidget{
  static const routeName = '/order-history';
  const OrderHistoryFormScreen({super.key});

  @override
  State<OrderHistoryFormScreen> createState() => _OrderHistoryFormScreenState();
}

class _OrderHistoryFormScreenState extends State<OrderHistoryFormScreen> {
  final _formKey = GlobalKey<FormState>();

  Future<List<Order>?> fetchOrder() async {
    try {
      final response = await http.get(
        Uri.parse("http://localhost:8083/order/customer/d0efc38f-8c2f-4aae-8455-8f5dbf0a1c07"),
        headers: {
          "Content-type": "application/json",
        },
      );
      if (response.statusCode == 200) {
        final List<dynamic> jsonResponse = json.decode(response.body);
          return jsonResponse.map((data) => Order.fromJson(data)).toList();
        } else {
          throw Exception("Failed to load orders");
        }
    } catch (e) {
      print(e.toString());
      return null;
    }
  }

  Future<void> updateOrderStatus(String order, int status, int totalPrice, String customer, String seller) async {
    final updateUrl = 'http://localhost:8083/order/$order';
    final requestBody = {
      "status": status,
      "totalPrice": totalPrice,
      "customer": customer,
      "seller": seller,
    };

    try {
      final response = await http.put(
        Uri.parse(updateUrl),
        headers: {
          "Content-type": "application/json",
        },
        body: jsonEncode(requestBody),
      );
      if (response.statusCode == 200) {
        // Status updated successfully
        print('Order status updated successfully');
        // Refresh the page after updating the status
        setState(() {});
      } else {
        throw Exception('Failed to update order status');
      }
    } catch (e) {
      print(e.toString());
      throw Exception('Failed to update order status');
    }
  }

  showLoading(BuildContext context) {
    return showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext context) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
        title: Text('Order History'),
      ),
      body: FutureBuilder<List<Order>?>(
        future: fetchOrder(),
        builder: (context, AsyncSnapshot<List<Order>?> snapshot) {
          if (snapshot.data == null) {
          return const Center(child: CircularProgressIndicator());
        } else {
          if (snapshot.data!.isEmpty) {
            return Center(
              child: Text(
                'No orders',
                style: TextStyle(color: Color(0xff59A5D8), fontSize: 20),
              ),
            );
          } else {
              return ListView.builder(
                itemCount: snapshot.data!.length,
                itemBuilder: (_, index) => Container(
                  margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                  padding: const EdgeInsets.all(20.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(15.0),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.black,
                        blurRadius: 1.0,
                      )
                    ],
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "Status: ${snapshot.data![index].status}",
                            style: const TextStyle(
                              fontSize: 18.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          Text(
                            "Total Price: ${snapshot.data![index].totalPrice}",
                            style: const TextStyle(
                              fontSize: 18.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 10),
                      Table(
                        border: TableBorder.all(),
                        children: [
                          TableRow(
                            children: [
                              TableCell(
                                child: Text('Product Name'),
                              ),
                              TableCell(
                                child: Text('Quantity'),
                              ),
                            ],
                          ),
                          for (var item in snapshot.data![index].orderItem)
                            TableRow(
                              children: [
                                TableCell(
                                  child: Text(item.productName),
                                ),
                                TableCell(
                                  child: Text(item.quantity.toString()),
                                ),
                              ],
                            ),
                        ],
                      ),
                      const SizedBox(height: 10),
                      ElevatedButton(
                        onPressed: () {
                          // Open a dialog to get the new status
                          _showChangeStatusDialog(context,snapshot.data![index].status, snapshot.data![index].id,
                              snapshot.data![index].totalPrice, snapshot.data![index].customer, snapshot.data![index].seller);
                        },
                        child: Text('Update Status'),
                      ),
                    ],
                  ),
                ),
              );
            }
          }
        },
      ),
    );
  }

  void _showChangeStatusDialog(BuildContext context, int currentStatus, String order, int totalPrice, String customer, String seller) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Change Order Status'),
        content: ChangeStatusForm(
          onStatusChanged: (newStatus) {
            Navigator.pop(context); // Close the dialog
            // Update the order status when the user provides the new status
            updateOrderStatus(order, newStatus, totalPrice, customer, seller);
          },
          currentStatus: currentStatus,
        ),
      ),
    );
  }
}

class ChangeStatusForm extends StatefulWidget {
  final void Function(int) onStatusChanged;
final int? currentStatus; 
  const ChangeStatusForm({Key? key, required this.onStatusChanged, this.currentStatus}) : super(key: key);

  @override
  _ChangeStatusFormState createState() => _ChangeStatusFormState();
}

class _ChangeStatusFormState extends State<ChangeStatusForm> {
  late int _selectedStatus;

  @override
  void initState() {
    super.initState();
    _selectedStatus = widget.currentStatus ?? 1;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        DropdownButtonFormField<int>(
          value: _selectedStatus,
          items: [
             DropdownMenuItem<int>(
              value: 0,
              child: Text('Menunggu konfirmasi penjual'),
            ),
            DropdownMenuItem<int>(
              value: 1,
              child: Text('dikonfirmasi penjual'),
            ),
            DropdownMenuItem<int>(
              value: 2,
              child: Text('menunggu kurir'),
            ),
            DropdownMenuItem<int>(
              value: 3,
              child: Text('dalam perjalanan'),
            ),
            DropdownMenuItem<int>(
              value: 4,
              child: Text('barang diterima'),
            ),
            DropdownMenuItem<int>(
              value: 5,
              child: Text('selesai'),
            ),
          ],
          onChanged: (value) {
            setState(() {
              _selectedStatus = value!;
            });
          },
          decoration: InputDecoration(labelText: 'Select Status'),
        ),
        SizedBox(height: 16),
        ElevatedButton(
          onPressed: () {
            // Call the callback with the selected status
            widget.onStatusChanged(_selectedStatus);
          },
          child: Text('Update Status'),
        ),
      ],
    );
  }
}
