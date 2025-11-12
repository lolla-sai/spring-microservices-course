package com.courses.spring_boot_microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.spring_boot_microservices.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
