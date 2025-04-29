package apapedia.frontend_web.service;

import java.util.List;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import apapedia.frontend_web.dto.response.OrderDTO;
import apapedia.frontend_web.dto.response.StatsDTO;
import apapedia.frontend_web.dto.response.StatsDTO;

@Service
public class OrderServiceImpl implements OrderService {
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<OrderDTO> getSellerOrder(UUID userId) {
        String url = "http://localhost:8083/api/order/seller/" + userId.toString();
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<OrderDTO>>() {}
        );

        return response.getBody();
    }

    @Override
    public OrderDTO updateOrder(UUID orderId) {
        String url = "http://localhost:8083/api/order/" + orderId.toString();
        ResponseEntity<OrderDTO> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            null,
            new ParameterizedTypeReference<OrderDTO>() {}
        );
    
        return response.getBody();
    }

    @Override
    public List<StatsDTO> getStats(String userId) {
        String url = "http://localhost:8083/api/order/seller/" + userId + "/stats";
        ResponseEntity<List<StatsDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<StatsDTO>>() {}
        );

        return response.getBody();
    }

}