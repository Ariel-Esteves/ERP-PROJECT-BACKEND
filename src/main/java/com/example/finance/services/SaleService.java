package com.example.finance.services;

import com.example.finance.Repositories.*;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.MapperDto;
import com.example.finance.models.entities.dto.MovementSaleDto;
import com.example.finance.models.entities.dto.MovementWalletDto;
import com.example.finance.models.entities.dto.SaleDto;
import com.example.finance.models.entities.enums.MOVEMENTYPE;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
	
	private final SalesRepository salesRepository;
	private final SalesTypeRepository salesTypeRepository;
	private final PersonService personService;
	private final MovementSalesRepository movementSalesRepository;
	private final ProductService productService;
	private final StockRepository stockRepository;
	private final WalletRepository walletRepository;
	private final MovementStockRepository stockMovementRepository;
	
	private final MovementWalletRepository movementWalletRepository;
	private final WalletService walletService;
	private final UserRepository userRepository;
	private final StockService stockService;
	private final PersonRepository personRepository;
	
	@Autowired
	public SaleService(SalesRepository salesRepository, SalesTypeRepository salesTypeRepository, PersonService personService,
	                   MovementSalesRepository movementSalesRepository, ProductService productService, StockRepository stockRepository,
	                   WalletService walletService, MovementStockRepository stockMovementRepository, MovementWalletRepository movementWalletRepository,
	                   UserRepository userRepository, StockService stockService, PersonRepository personRepository, WalletRepository walletRepository)
	{
		this.salesRepository = salesRepository;
		this.salesTypeRepository = salesTypeRepository;
		this.personService = personService;
		this.movementSalesRepository = movementSalesRepository;
		this.productService = productService;
		this.stockRepository = stockRepository;
		this.walletService = walletService;
		this.stockMovementRepository = stockMovementRepository;
		this.movementWalletRepository = movementWalletRepository;
		this.userRepository = userRepository;
		this.stockService = stockService;
		this.personRepository = personRepository;
		this.walletRepository = walletRepository;
	}
	
	@PostConstruct
	private void createSaleType() {
		List.of("in", "out").forEach(name -> salesTypeRepository.save(SaleTypeEntity.builder().name(name).id(0).build()));
		
	}
	
	public SaleDto createSale(@Valid SaleDto saleDto) {
		PersonEntity person = personRepository.findById(saleDto.person()).orElseThrow(() -> new EntityNotFoundException("Person not found"));
		SaleTypeEntity type = SaleTypeEntity.builder().name("out").id(0).build();
		
		ArrayList<MovementSaleEntity> movementSaleEntity = saleDto.movementSale().stream().map(movementSaleDto -> {
			ProductEntity productFound = productService.findProduct(movementSaleDto.product());
			
			return MovementSaleEntity.builder()
			                         .paymentValue(movementSaleDto.paymentValue())
			                         .quantity(movementSaleDto.quantity())
			                         .product(productFound)
			                         .id(0)
			                         .build();
		}).collect(Collectors.toCollection(ArrayList::new));
		
		SaleEntity saleSaved = salesRepository.save(SaleEntity.builder()
		                                                      .data(LocalDateTime.now())
		                                                      .paymentValue(saleDto.paymentValue())
		                                                      .person(person)
		                                                      .saleType(type)
		                                                      .movementSales(movementSaleEntity)
		                                                      .id(0)
		                                                      .build());
		
		WalletEntity wallet = walletService.findWalletByPersonId(person.getId());
		
		MovementWalletEntity movementWallet = MovementWalletEntity.builder()
		                                                          .data(LocalDateTime.now())
		                                                          .paymentValue(saleDto.paymentValue())
		                                                          .movementType(MOVEMENTYPE.ENTRADA)
		                                                          .sale(saleSaved)
		                                                          .wallet(wallet)
		                                                          .id(0)
		                                                          .build();
		
		wallet.getMovement().add(movementWallet);
		wallet.setBalance(wallet.getBalance().add(saleDto.paymentValue()));
		walletRepository.save(wallet);
		
		saleSaved.setMovementWallet(new ArrayList<>(Arrays.asList(movementWallet)));
		
		saleDto.movementSale().forEach((product) -> {
				StockEntity stock = productService.findProduct(product.product()).getStock();
			MovementStockEntity stockMovement = MovementStockEntity.builder()
			                                                       .quantity(product.quantity().negate())
			                                                       .movementType(MOVEMENTYPE.SAIDA)
			                                                       .stock(stock)
			                                                       .id(0)
			                                                       .build();
			stock.getMovementStockEntity().add(stockMovement);
			stock.setQuantity(stock.getQuantity().subtract(product.quantity()));
			stockRepository.save(stock);
		});
		salesRepository.save(saleSaved);
		
		return MapperDto.convertToSaleDto(saleSaved);
	}
	
	public SaleTypeEntity createSaleType(@Valid SaleTypeEntity saleTypeEntity) {
		return salesTypeRepository.save(saleTypeEntity);
	}
	
	public List<SaleTypeEntity> getAllSaleTypes() {
		return salesTypeRepository.findAll();
	}
	
	public SaleTypeEntity getSaleTypeById(long id) {
		return salesTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("SaleType not found"));
	}
	
	
	public List<SaleDto> getAllSales() {
		return salesRepository.findAll().stream().map(MapperDto::convertToSaleDto).toList();
	}
	
	public SaleEntity getSaleById(long id) {
		return salesRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
	}
	
	public SaleEntity updateSale(long id, SaleEntity saleDetails) {
		SaleEntity saleEntity = getSaleById(id);
		saleEntity.setPaymentValue(saleDetails.getPaymentValue());
		saleEntity.setData(saleDetails.getData());
		saleEntity.setSaleType(saleDetails.getSaleType());
		saleEntity.setPerson(saleDetails.getPerson());
		return salesRepository.save(saleEntity);
	}
	
	public void deleteSale(long id) {
		salesRepository.deleteById(id);
	}
	
	public List<MovementSaleEntity> getMovementSales() {
		return movementSalesRepository.findAll();
	}
	
	public ArrayList<SaleDto> findByPersonId(long id) {
		ArrayList<SaleDto> sales = salesRepository.findByPersonId(id).orElseThrow(() -> new RuntimeException("Person not found")).stream().map(MapperDto::convertToSaleDto).collect(Collectors.toCollection(ArrayList::new));
		return sales;
	}
	
}
