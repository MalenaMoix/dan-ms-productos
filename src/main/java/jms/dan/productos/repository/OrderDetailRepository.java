package jms.dan.productos.repository;

import jms.dan.productos.dto.OrderDetailDTO;
import jms.dan.productos.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Repository
public class OrderDetailRepository implements IOrderDetailRepository {
    private static final String BASEURL = "http://localhost:8081/api-orders/details/";

    @Override
    public OrderDetailDTO getOrderDetailById(Integer id) {
        WebClient webClient = WebClient.create(BASEURL + id);
        try {
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
        } catch (WebClientException e) {
            throw new ApiException(
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "An error has occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
