package com.example.finance.models.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record WalletDto(BigDecimal balance, LocalDateTime dateTime, long person, List<MovementWalletDto> movement) {

}
