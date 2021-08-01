package jms.dan.productos.repository;

import jms.dan.productos.dto.OrderDetailDTO;
import jms.dan.productos.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

@Repository
public class OrderDetailRepository implements IOrderDetailRepository {
    private static final String BASEURL = "http://localhost:8081/api-orders/details/";

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public OrderDetailDTO getOrderDetailById(Integer id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        WebClient webClient = WebClient.create(BASEURL + id);

        return circuitBreaker.run(() -> {
            ResponseEntity<OrderDetailDTO> response = webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(OrderDetailDTO.class)
                    .block();

            if (response != null && response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
                return response.getBody();
            } else {
                throw new ApiException(
                        HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        "Order Detail not found",
                        HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } , throwable -> defaultOrderDetailById());
    }

    private OrderDetailDTO defaultOrderDetailById() {
        return null;
    }
}
