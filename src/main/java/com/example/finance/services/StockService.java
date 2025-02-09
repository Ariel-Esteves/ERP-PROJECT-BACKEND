package com.example.finance.services;

import com.example.finance.Repositories.StockRepository;
import com.example.finance.models.entities.MovementStockEntity;
import com.example.finance.models.entities.ProductEntity;
import com.example.finance.models.entities.StockEntity;
import com.example.finance.models.entities.dto.StockMovementDto;
import com.example.finance.models.entities.enums.MOVEMENTYPE;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
	private final StockRepository stockRepository;
	
	@Autowired
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	public StockEntity createStockMovimento(@Valid StockMovementDto stockMovementDto) {
		
		StockEntity stockEntity = findById(stockMovementDto.id());
		
		List<MovementStockEntity> estoqueMovimentoEntities = stockEntity.getMovementStockEntity();
		estoqueMovimentoEntities.add(MovementStockEntity.builder()
		                                                .quantity(stockMovementDto.quantity())
		                                                .id(0)
		                                                .movementType(stockMovementDto.quantity()
		                                                                              .compareTo(BigDecimal.ZERO) < 0 ? MOVEMENTYPE.SAIDA : MOVEMENTYPE.ENTRADA)
		                                                .build());
		stockEntity.setMovementStockEntity(estoqueMovimentoEntities);
		stockEntity.setQuantity(stockEntity.getQuantity().add(stockMovementDto.quantity()));
		stockRepository.save(stockEntity);
		return stockEntity;
	}
	
	public List<StockEntity> getAllStock() {
		return stockRepository.findAll();
	}
	
	public StockEntity createStock(@Valid StockEntity stockEntity) {
		return stockRepository.save(stockEntity);
	}
	
	public StockEntity createStock(ProductEntity productEntity) {
		StockEntity stock = StockEntity.builder()
		                               .quantity(BigDecimal.ZERO)
		                               .data(LocalDateTime.now())
		                               .id(0)
		                               .product(productEntity)
		                               .movementStockEntity(new ArrayList<>())
		                               .build();
		
		return stockRepository.save(stock);
	}
	
	public void deleteStock(long id) {
		stockRepository.deleteById(id);
	}
	
	public StockEntity findById(long id) {
		return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
	}
}
