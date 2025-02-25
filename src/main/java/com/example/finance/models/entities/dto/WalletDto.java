package com.example.finance.models.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public record WalletDto(long id, BigDecimal balance, LocalDateTime dateTime, long person, ArrayList<MovementWalletDto>movement) {

}
