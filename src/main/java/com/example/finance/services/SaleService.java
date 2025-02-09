package com.example.finance.services;

import com.example.finance.Repositories.*;
import com.example.finance.models.entities.*;
import com.example.finance.models.entities.dto.MovementSaleDto;
import com.example.finance.models.entities.dto.SaleDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
	
	private final SalesRepository salesRepository;
	private final SalesTypeRepository salesTypeRepository;
	private final PersonService personService;
	private final MovementSalesRepository movementSalesRepository;
	private final ProductService productService;
	private final StockRepository stockRepository;
	
	private final MovementStockRepository stockMovementRepository;
	
	private final MovementWalletRepository movementWalletRepository;
	private final WalletRepository walletRepository;
	private final UserRepository userRepository;
	private final StockService stockService;
	private final PersonRepository personRepository;
	
	@Autowired
	public SaleService(SalesRepository salesRepository, SalesTypeRepository salesTypeRepository, PersonService personService,
	                   MovementSalesRepository movementSalesRepository, ProductService productService, StockRepository stockRepository,
	                   WalletRepository walletRepository, MovementStockRepository stockMovementRepository, MovementWalletRepository movementWalletRepository,
	                   UserRepository userRepository, StockService stockService, PersonRepository personRepository)
	{
		this.salesRepository = salesRepository;
		this.salesTypeRepository = salesTypeRepository;
		this.personService = personService;
		this.movementSalesRepository = movementSalesRepository;
		this.productService = productService;
		this.stockRepository = stockRepository;
		this.walletRepository = walletRepository;
		this.stockMovementRepository = stockMovementRepository;
		this.movementWalletRepository = movementWalletRepository;
		this.userRepository = userRepository;
		this.stockService = stockService;
		this.personRepository = personRepository;
	}
	
	
	public SaleDto createSale(@Valid SaleDto saleDto) {
		PersonEntity person = personRepository.findById(saleDto.person()).orElseThrow(() -> new EntityNotFoundException("Person not found"));
		SaleTypeEntity type = SaleTypeEntity.builder().name("out").id(0).build();
		UserEntity user = userRepository.findById(saleDto.user()).orElseThrow(() -> new EntityNotFoundException("User not found"));
		
		List<MovementWalletEntity> movementWallet = List.of(MovementWalletEntity.builder()
		                                                                        .paymentValue(saleDto.paymentValue())
		                                                                        .wallet(walletRepository.findById(person.getWallet().getId())
		                                                                                                .orElseThrow(() -> new EntityNotFoundException(
				                                                                                                "Wallet not found")))
		                                                                        .id(0)
		                                                                        .build(),
		
		                                                    MovementWalletEntity.builder()
		                                                                        .paymentValue(saleDto.paymentValue())
		                                                                        /*.wallet(walletRepository.findById(user.getPerson().getWallet().getId())
																										.orElseThrow(() -> new EntityNotFoundException(
																												"Wallet not found"))) */.wallet(
				                                                                        walletRepository.findById(person.getWallet().getId())
				                                                                                        .orElseThrow(() -> new EntityNotFoundException("Wallet not found")))
		                                                                        .id(0)
		                                                                        .build());
		
		
		List<MovementSaleEntity> movementSaleEntity = saleDto.movementSale().stream().map(movementSaleDto -> {
			ProductEntity productFound = productService.findProduct(movementSaleDto.product());
			
			return MovementSaleEntity.builder()
			                         .paymentValue(movementSaleDto.paymentValue())
			                         .quantity(movementSaleDto.quantity())
			                         .product(productFound)
			                         .id(0)
			                         .build();
		}).toList();
		
		SaleEntity saleSaved = salesRepository.save(SaleEntity.builder()
		                                                      .data(LocalDateTime.now())
		                                                      .paymentValue(saleDto.paymentValue())
		                                                      .person(person)
		                                                      .saleType(type)
		                                                      .movementSale(movementSaleEntity)
		                                                      .movementWallet(movementWallet)
		                                                      .id(0)
		                                                      .build());
		
		return new SaleDto(saleSaved.getPaymentValue(), saleSaved.getSaleType().getId(), saleSaved.getPerson().getId(), saleDto.user(),
		                   saleSaved.getMovementSale().stream().map(e -> new MovementSaleDto(e.getId(), e.getPaymentValue(), e.getQuantity())).toList()
		                   );
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
	
	
	public List<SaleEntity> getAllSales() {
		return salesRepository.findAll();
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
	
	
}
