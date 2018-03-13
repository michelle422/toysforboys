package be.vdab.services;

import java.util.Optional;

import be.vdab.entities.Product;
import be.vdab.repositories.ProductRepository;

public class ProductService extends AbstractService {
	private static ProductRepository productRepository = new ProductRepository();
	
	public Optional<Product> read(long id) {
		return productRepository.read(id);
	}
}
