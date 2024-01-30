package org.delivery.db.userordermenu;

import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// DB 사용할 수 있도록 준비!

public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenuEntity, Long> {
    
    // 어떠한 주문을 했을 시 해당 주문아이디(userOrderId)가 존재하고,
    // 그 주문아이디에 해당되는 등록된 모든 메뉴의 리스트를 리턴하는 메소드
    // select * from user_order_menu where user_order_id = ? and status = ?
    List<UserOrderMenuEntity> findAllByUserOrderIdAndStatus(Long userOrderId, UserOrderMenuStatus status);
}
