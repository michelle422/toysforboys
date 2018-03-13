package be.vdab.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

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
	
	public void updateStock(long id) {
		beginTransaction();
		try {
			orderRepository.read(id).ifPresent(order -> order.updateOrder());
			orderRepository.read(id).ifPresent(order -> order.getOrderDetails()
						.forEach(orderdetail -> orderdetail.updateProduct()));
			commit();
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
}
