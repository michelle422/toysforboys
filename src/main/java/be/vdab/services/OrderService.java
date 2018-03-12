package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Order;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {
	private OrderRepository orderRepository = new OrderRepository();
	
	public List<Order> findMetStatusBehalveShippedEnCancelled() {
		return orderRepository.findMetStatusBehalveShippedEnCancelled();
	}
	
	public Optional<Order> read(long id) {
		return orderRepository.read(id);
	}
}
