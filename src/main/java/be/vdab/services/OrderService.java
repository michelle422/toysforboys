package be.vdab.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import be.vdab.entities.Order;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {
	private OrderRepository orderRepository = new OrderRepository();
	
	public List<Order> findMetStatusBehalveShippedEnCancelled(int vanafRij, int aantalRijen) {
		return orderRepository.findMetStatusBehalveShippedEnCancelled(vanafRij, aantalRijen);
	}
	
	public Optional<Order> read(long id) {
		return orderRepository.read(id);
	}
	
	public void updateStock(String[] checked, Map<String, String> fouten) {
		StringBuilder shipError = new StringBuilder("Shipping failed for order(s) ");
		StringBuilder notShipped = new StringBuilder();
		beginTransaction();
		try {
			Arrays.stream(checked).forEach(id -> orderRepository
					.read(Long.parseLong(id)).ifPresent(order -> order.updateOrder(id, notShipped)));
			commit();
		} catch (RollbackException ex) {
			if (ex.getCause() instanceof OptimisticLockException) {
				throw new RecordAangepastException();
			}
			shipError.append(notShipped);
			shipError.append("not enough stock");
			fouten.put("notShipped", shipError.toString());
		} catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
}
	
