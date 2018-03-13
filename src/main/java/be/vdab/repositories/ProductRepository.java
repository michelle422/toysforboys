package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Product;

public class ProductRepository extends AbstractRepository {
	public Optional<Product> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Product.class, id));
	}
}
