package apapedia.order.restservice;

import java.util.*;

import apapedia.order.dto.request.CreateOrderDto;
import apapedia.order.dto.request.StatsDto;
import apapedia.order.dto.request.UpdateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import apapedia.order.dto.request.CreateOrderDto;
import apapedia.order.dto.request.StatsDto;
import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import apapedia.order.model.Order;
import apapedia.order.model.OrderItem;
import apapedia.order.repository.OrderDb;
import apapedia.order.repository.OrderItemDb;
import org.springframework.stereotype.Service;

@Service
public class OrderRestServiceImpl implements OrderRestService {

    @Autowired
    OrderDb orderDb;

    @Autowired
    OrderItemDb orderItemDb;

    @Override
    public void createOrder(List<CreateOrderDto> createOrderDto, UUID userId) {
        Map<UUID, Order> sellerOrders = new HashMap<>();
        

        for (CreateOrderDto orderItem : createOrderDto) {
            OrderItem item = new OrderItem();
            item.setQuantity(orderItem.getQuantity());
            item.setProductId(orderItem.getProductId());
            item.setProductName(orderItem.getProductName());
            item.setProductPrice(orderItem.getPrice());

            UUID sellerId = orderItem.getSeller();

            Order sellerOrder = sellerOrders.getOrDefault(sellerId, new Order());
            sellerOrder.setSeller(sellerId);
            sellerOrder.setCustomer(userId);
            sellerOrder.setTotalPrice(sellerOrder.getTotalPrice() + (item.getProductPrice() * item.getQuantity()));

            Order createdOrder = orderDb.save(sellerOrder);
            item.setOrderId(createdOrder);
            orderItemDb.save(item);

            sellerOrders.put(sellerId, createdOrder);
        }
    }

    @Override
    public Order updateOrder(UUID orderId) {
    Order order = orderDb.findById(orderId).get();
    Integer status = order.getStatus();

        if (status != 5){
            order.setStatus(status + 1);
        }
        
        orderDb.save(order);
        return order;
    }

    @Override
    public List<Order> getCustomerOrder(UUID userId) {
        return orderDb.findAllByCustomer(userId);
    }

    @Override
    public List<Order> getSellerOrder(UUID userId) {
        return orderDb.findAllBySeller(userId);
    }

    @Override
    public List<StatsDto> getStats(UUID userId) {
        List<StatsDto> stats = new ArrayList<>();
        Map<String, Integer> statsMap = new HashMap<>();
        List<Order> orders = orderDb.findAllBySeller(userId);
        for (Order order:  orders) {
            if (order.getStatus() == 5) {
                for (OrderItem orderItem: order.getOrderItem()) {
                    String productName = orderItem.getProductName();
                    int quantity = orderItem.getQuantity();
                    if (statsMap.containsKey(productName)) {
                        // If yes, increment the count by the order item quantity
                        statsMap.put(productName, statsMap.get(productName) + quantity);
                    } else {
                        // If no, add the product name to the map with the order item quantity
                        statsMap.put(productName, quantity);
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : statsMap.entrySet()) {
            stats.add(new StatsDto(entry.getKey(), entry.getValue()));
        }

        return stats;
    }

    @Override
    public List<OrderItem> findByOrder(Order orderId) {
        return orderItemDb.findByOrderId(orderId);
    }

}