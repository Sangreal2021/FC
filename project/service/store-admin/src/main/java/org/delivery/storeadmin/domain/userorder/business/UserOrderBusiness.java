package org.delivery.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import org.delivery.storeadmin.domain.userorder.service.UserOrderService;
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserOrderBusiness {
    
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final SseConnectionPool sseConnectionPool;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    
    /*
    * 1. 주문
    * 2. 주문 내역 찾기
    * 3. 스토어 찾기
    * 4. 연결된 세션 찾아서
    * 5. push
    **/
    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        var userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
            .orElseThrow(() -> new RuntimeException("사용자 주문내역 없음"));
        
        // user order menu
        var userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());
        
        // user order menu -> store menu
        var storeMenuResponseList = userOrderMenuList.stream()
            .map(userOrderMenuEntity -> {
                return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId());
            })
            .map(storeMenuEntity -> {
                return storeMenuConverter.toResponse(storeMenuEntity);
            })
            .collect(Collectors.toList());
        
        var userOrderResponse = userOrderConverter.toResponse(userOrderEntity);
        
        // 어떠한 메뉴를 주문했는지 response 에 담음
        var push = UserOrderDetailResponse.builder()
            .userOrderResponse(userOrderResponse)
            .storeMenuResponseList(storeMenuResponseList)
            .build();
        
        // 현재 연결된 사용자 즉, 가게와 연결이 되어있는 커넥션을 찾아옴
        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());
        
        // 사용자에게 push
        userConnection.sendMessage(push);
    }
}















