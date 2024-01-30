package org.delivery.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.enums.StoreCategory;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {
    
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    
    public StoreResponse register(
        StoreRegisterRequest storeRegisterRequest
    ) {
        // 로직 : req -> entity 로 변환 -> response 로 변환
        var entity = storeConverter.toEntity(storeRegisterRequest);
        var newEntity = storeService.register(entity);
        var response = storeConverter.toResponse(newEntity);
        
        return response;
    }
    
    public List<StoreResponse> searchCategory(
        StoreCategory storeCategory
    ) {
        // entity 리스트 -> response 리스트로 변환
        var storeList = storeService.searchByCategory(storeCategory);
        
        // entity 의 내용이 response 가 들어간 리스트로 바뀜
        return storeList.stream()
            .map(storeConverter::toResponse)
            .collect(Collectors.toList());
    }
}
