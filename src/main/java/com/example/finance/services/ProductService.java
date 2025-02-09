package com.example.finance.services;

import com.example.finance.Repositories.ProductRepository;
import com.example.finance.models.entities.ProductEntity;
import com.example.finance.models.entities.StockEntity;
import com.example.finance.models.entities.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final StockService stockService;
	
	public ProductService(ProductRepository productRepository, StockService stockService) {
		this.productRepository = productRepository;
		this.stockService = stockService;
	}
	
	public ProductDto createProduct(@Valid ProductDto productDto) {
		ProductEntity productSaved = productRepository.save(convertToProductEntity(productDto));
		StockEntity stockSaved = stockService.createStock(productSaved);
		productSaved.setStock(stockSaved);
		return convertToProductDto(productRepository.save(productSaved));
	}
	
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}
	
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
	
	public ProductEntity findProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	public ProductEntity convertToProductEntity(ProductDto productDto) {
		return ProductEntity.builder().name(productDto.name()).price(productDto.price()).stock(null).id(0).build();
	}
	
	public ProductDto convertToProductDto(ProductEntity productEntity) {
		
		return new ProductDto(productEntity.getName(), productEntity.getPrice(), productEntity.getStock().getId());
	}
}
