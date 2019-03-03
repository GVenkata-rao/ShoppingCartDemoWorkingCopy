package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
