package apapedia.order.dto;

import apapedia.order.dto.request.CreateOrderDto;
import apapedia.order.dto.request.UpdateOrderDto;
import apapedia.order.dto.response.OrderResponseDTO;
import apapedia.order.model.Order;

public interface OrderMapper {
    OrderResponseDTO orderToOrderResponseDTO(Order order);

    Order createOrderDTOToOrder(CreateOrderDto createOrderDto);
    
    Order updateOrderDTOToOrder(UpdateOrderDto updateOrderDto);
}
