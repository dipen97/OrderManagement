package com.order.management.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.order.management.model.Order;
import com.order.management.model.Product;
import com.order.management.service.OrderService;


@RestController
@RequestMapping("/v1")
@CrossOrigin
public class OrderController {
	
	Logger log = LoggerFactory.getLogger(OrderController.class);
	
	
	private OrderService service;
	
	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}
	
//	-------------------------------------------ORDERS-------------------------------------------------------
	@GetMapping("/orders") //working
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getOrders() {
		log.info("REQUEST: " + "GET /orders @ " + LocalDateTime.now());
		return service.getOrders();
	}
	@GetMapping("/orders/{id}") //working
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getOrdersById() {
		log.info("REQUEST: " + "GET /orders @ " + LocalDateTime.now());
		return service.getOrders();
		//return service.getOrdersById(id);
	}
	
	@PostMapping("/orders/new") //working
	@ResponseStatus(HttpStatus.CREATED)
	public void newOrder(/*@RequestBody Order order*/) {
		log.info("REQUEST: " + "POST /orders/new @ " + LocalDateTime.now());
		service.newOrder(new Order());
	}
	
	
	@PostMapping("/orders/new2") //working
	@ResponseStatus(HttpStatus.CREATED)
	public void newOrderPlus(@RequestBody List<Product> products) {
		log.info("REQUEST: " + "POST /orders/new2 @ " + LocalDateTime.now());
		service.newOrderPlus(new Order(), products);
	}
	
	@Deprecated //no need to update the order ; obsolete
	@PutMapping("/orders/update")
	@ResponseStatus(HttpStatus.OK)
	public void updateOrder(@RequestBody Order order) {
		if (order.getId() == 0 || order.getCreated() == null) {
			log.info("REQUEST: " + "ERROR PUT /orders/update @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing order details");
		} else {
			log.info("REQUEST: " + "PUT /orders/update @ " + LocalDateTime.now());
			service.updateOrder(order);
		}
	}
	
	@DeleteMapping("/orders/delete") //working
	@ResponseStatus(HttpStatus.OK)
	public void deleteOrder(@RequestBody Order order) {
		if (order.getId() == 0) {
			log.info("REQUEST: " + "ERROR DELETE /orders/delete @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing order details");
		} else {
			log.info("REQUEST: " + "DELETE /orders/delete @ " + LocalDateTime.now());
			service.deleteOrder(order);
		}
	}
//	-------------------------------------------PRODUCTS-------------------------------------------------------
	@GetMapping("/products") //working
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getProductsByOrder(@RequestBody Order order) {
		if (order.getId() == 0 || order.getId() == null || order.getCreated() == null) {
			log.info("REQUEST: " + "ERROR GET /products @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing order details");
		}
		log.info("REQUEST: " + "GET /products @ " + LocalDateTime.now());
		return service.getProductsByOrder(order);
	}
	
	@GetMapping("/products/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	
	@PostMapping("/products/new") //working send id as a child of order within the product json
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(@RequestBody Product product) {
		if (product.getName() == null || product.getPrice() == 0 || product.getManufacturer() == null || product.getManufacturer() == "") {
			log.info("REQUEST: " + "ERROR POST /products/new @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing product details");
		} else {
			log.info("REQUEST: " + "POST /products/new @ " + LocalDateTime.now());
			service.newProduct(product);
		}
	}
	
	@PutMapping("/products/update") //working 
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@RequestBody Product product) {
		if (product.getId() == 0 || product.getId() == null || product.getName() == null || product.getPrice() == 0 || product.getManufacturer() == null || product.getManufacturer() == "") {
			log.info("REQUEST: " + "ERROR PUT /products/update @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing product details");
		} else {
			log.info("REQUEST: " + "PUT /products/update @ " + LocalDateTime.now());
			service.updateProduct(product);
		}
	}
	
	@DeleteMapping("/products/delete") //working
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@RequestBody Product product) {
		if (product.getId() == 0 || product.getId() == null || product.getName() == null || product.getPrice() == 0 || product.getManufacturer() == null || product.getManufacturer() == "") {
			log.info("REQUEST: " + "ERROR DELETE /products/delete @ " + LocalDateTime.now());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing product details");
		} else {
			log.info("REQUEST: " + "DELETE /products/delete @ " + LocalDateTime.now());
			service.deleteProduct(product);
		}
	}
	
}
