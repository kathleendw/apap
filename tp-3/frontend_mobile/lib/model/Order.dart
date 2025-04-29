import 'dart:convert';

List<Order> orderFromJson(String str) => List<Order>.from(json.decode(str).map((x) => Order.fromJson(x)));

String orderToJson(List<Order> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Order {
    String id;
    DateTime createdAt;
    DateTime updatedAt;
    int status;
    int totalPrice;
    String customer;
    String seller;
    List<OrderItem> orderItem;

    Order({
        required this.id,
        required this.createdAt,
        required this.updatedAt,
        required this.status,
        required this.totalPrice,
        required this.customer,
        required this.seller,
        required this.orderItem,
    });

    factory Order.fromJson(Map<String, dynamic> json) => Order(
        id: json["id"],
        createdAt: DateTime.parse(json["createdAt"]),
        updatedAt: DateTime.parse(json["updatedAt"]),
        status: json["status"],
        totalPrice: json["totalPrice"],
        customer: json["customer"],
        seller: json["seller"],
        orderItem: List<OrderItem>.from(json["orderItem"].map((x) => OrderItem.fromJson(x))),
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "createdAt": createdAt.toIso8601String(),
        "updatedAt": updatedAt.toIso8601String(),
        "status": status,
        "totalPrice": totalPrice,
        "customer": customer,
        "seller": seller,
        "orderItem": List<dynamic>.from(orderItem.map((x) => x.toJson())),
    };
}

class OrderItem {
    String id;
    String productId;
    String orderId;
    int quantity;
    String productName;
    int productPrice;

    OrderItem({
        required this.id,
        required this.productId,
        required this.orderId,
        required this.quantity,
        required this.productName,
        required this.productPrice
    });

    factory OrderItem.fromJson(Map<String, dynamic> json) => OrderItem(
        id: json["id"],
        productId: json["productId"],
        orderId: json["orderId"],
        quantity: json["quantity"],
        productName: json["productName"],
        productPrice: json["productPrice"]
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "productId": productId,
        "orderId": orderId,
        "quantity": quantity,
        "productName": productName,
        "productPrice": productPrice
    };
}
