package apapedia.order.restcontroller;

import apapedia.order.dto.request.CreateOrderDto;
import apapedia.order.dto.request.StatsDto;
import apapedia.order.dto.request.UpdateOrderDto;
import apapedia.order.model.Order;
import apapedia.order.model.OrderItem;
import apapedia.order.repository.OrderDb;
import apapedia.order.restservice.OrderRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/api/order")
public class OrderRestController {
    @Autowired
    private OrderRestService orderService;

    @Autowired
    private OrderDb orderDb;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addOrder(@RequestBody List<CreateOrderDto> createOrderDto, @PathVariable("userId") UUID userId) {
        orderService.createOrder(createOrderDto, userId);
        return ResponseEntity.ok("Order successfully created!");
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<Order>> getOrderCustomer(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getCustomerOrder(userId));
    }

    @GetMapping("/seller/{userId}")
    public ResponseEntity<List<Order>> getOrderSeller(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getSellerOrder(userId));
    }

    @GetMapping("seller/orderItem/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemByOrderId(@PathVariable("orderId") UUID orderId) {
        Order order = orderDb.findById(orderId).get();
        return ResponseEntity.ok(orderService.findByOrder(order));
    }
    
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable("orderId") UUID orderId) {
        var order = orderService.updateOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/seller/{userId}/stats")
    public ResponseEntity<List<StatsDto>> getStats(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getStats(userId));
    }
}