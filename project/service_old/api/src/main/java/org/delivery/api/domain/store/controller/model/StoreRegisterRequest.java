package org.delivery.api.domain.store.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {
    
    @NotBlank // "", " ", null 모두 안됨
    private String name;
    
    @NotBlank
    private String address;
    
    @NotNull // 카테고리는 Enum 이라서 문자 X
    private StoreCategory storeCategory;
    
    @NotBlank
    private String thumbnailUrl;
    
    @NotNull
    private BigDecimal minimumAmount;
    
    @NotNull
    private BigDecimal minimumDeliveryAmount;
    
    @NotBlank
    private String phoneNumber;
}
