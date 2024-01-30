package org.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// 주문 건에 대한 Response
//  -

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderResponse {
    
    private Long id;
    
    private UserOrderStatus status;
    
    private BigDecimal amount;
    
    private LocalDateTime orderedAt;
    
    private LocalDateTime acceptedAt;
    
    private LocalDateTime cookingStartedAt;
    
    private LocalDateTime deliveryStartedAt;
    
    private LocalDateTime receivedAt;
}
