package jms.dan.productos.repository;

import jms.dan.productos.dto.OrderDetailDTO;

public interface IOrderDetailRepository {
    OrderDetailDTO getOrderDetailById(Integer id);
}
