package com.order.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.management.model.Order;
import com.order.management.model.Product;
import com.order.management.repository.OrderRepo;
import com.order.management.repository.ProductRepo;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepo orderRepo;
	private ProductRepo productRepo;
	
	@Autowired
	public OrderServiceImpl(OrderRepo orderRepo, ProductRepo productRepo) {
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
	}
	
//	ORDER------------------------------------------

	@Override
	public void newOrder(Order order) {
		order.setCreated(LocalDateTime.now());
		orderRepo.save(order);
		
	}

	@Override
	public void newOrderPlus(Order order, List<Product> products) {
		System.out.println("error newOrderPlus");
		order.setCreated(LocalDateTime.now());
		System.out.println("error newOrderPlus123");
		orderRepo.save(order);
		order.setId(orderRepo.findByCreated(order.getCreated()).getId());
		for (Product product : products) {
			product.setOrder(order);
			productRepo.save(product);
		}
	}
	
	@Override
	public List<Order> getOrders() {
		return orderRepo.findAll();
	}
	
	@Override
	public List<Order> getOrdersById() {
		return orderRepo.findAll();
	}

	@Override
	public void updateOrder(Order order) {
		orderRepo.save(order); 
	}

	@Override
	public void deleteOrder(Order order) { 
//	get products, iterate and delete then remove order
		List<Product> products = productRepo.findAllByOrder(order);
		
		for (Product x : products) {
			deleteProduct(x);
		}
		
		orderRepo.delete(order);
	}
	
//	PRODUCT---------------------------------------

	@Override
	public List<Product> getProductsByOrder(Order order) {
		return productRepo.findAllByOrder(order);
	}
	
	

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public void newProduct(Product product) {
		productRepo.save(product);
		
	}

	@Override
	public void updateProduct(Product product) {
		productRepo.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}

	
	
}
