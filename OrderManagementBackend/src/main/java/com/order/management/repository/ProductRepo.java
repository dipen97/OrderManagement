package com.order.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.order.management.model.Order;
import com.order.management.model.Product;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	public List<Product> findAllByOrder(Order order);

}
