package com.courses.spring_boot_microservices.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.spring_boot_microservices.order.client.InventoryClient;
import com.courses.spring_boot_microservices.order.dto.OrderRequest;
import com.courses.spring_boot_microservices.order.dto.OrderResponse;
import com.courses.spring_boot_microservices.order.model.Order;
import com.courses.spring_boot_microservices.order.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private InventoryClient inventoryClient;

        public List<OrderResponse> getAllOrders() {
                List<Order> orders = orderRepository.findAll();
                List<OrderResponse> response = orders.stream()
                                .map(order -> new OrderResponse(
                                                order.getId(),
                                                order.getOrderNumber(),
                                                order.getSkuCode(),
                                                order.getPrice(),
                                                order.getQuantity()))
                                .toList();
                return response;
        }

        public OrderResponse placeOrder(OrderRequest orderRequest) {
                if (!inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {
                        throw new RuntimeException("Product with SKU Code " + orderRequest.skuCode()
                                        + " is not in stock with required quantity " + orderRequest.quantity());
                }

                Order order = Order
                                .builder()
                                .skuCode(orderRequest.skuCode())
                                .price(orderRequest.price())
                                .quantity(orderRequest.quantity())
                                .build();
                orderRepository.save(order);
                log.info("Order Placed Succesfully!");
                return new OrderResponse(
                                order.getId(),
                                order.getOrderNumber(),
                                order.getSkuCode(),
                                order.getPrice(),
                                order.getQuantity());
        }
}
