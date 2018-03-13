package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Order;

public class OrderRepository extends AbstractRepository {
	
	public List<Order> findMetStatusBehalveShippedEnCancelled() {
		return getEntityManager()
				.createNamedQuery("Order.findMetStatusBehalveShippedEnCancelled", Order.class)
//				.setFirstResult(vanafRij)
//				.setMaxResults(aantalRijen)
				.setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph(Order.MET_CUSTOMER))
				.getResultList();
	}
	
	public Optional<Order> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Order.class, id));
	}
}
