package com.example.finance.services;

import com.example.finance.Repositories.ProductRepository;
import com.example.finance.Repositories.StockRepository;
import com.example.finance.models.entities.ProductEntity;
import com.example.finance.models.entities.StockEntity;
import com.example.finance.models.entities.dto.MapperDto;
import com.example.finance.models.entities.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final StockService stockService;
	private final StockRepository stockRepository;
	
	public ProductService(ProductRepository productRepository, StockService stockService, StockRepository stockRepository) {
		this.productRepository = productRepository;
		this.stockService = stockService;
		this.stockRepository = stockRepository;
	}
	
	public ProductDto createProduct(@Valid ProductDto productDto) {
		ProductEntity productSaved = productRepository.save(MapperDto.convertToProductEntity(productDto));
		
		productSaved.setStock(StockEntity.builder()
		                                 .product(productSaved)
		                                 .data(LocalDateTime.now())
		                                 .quantity(productDto.stock())
				                         .id(0)
				                         .build()
		                     );
		StockEntity stock = stockRepository.save(productSaved.getStock());
		productSaved.setStock(stock);
		return MapperDto.convertToProductDto(productRepository.save(productSaved));
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
	
	
}
