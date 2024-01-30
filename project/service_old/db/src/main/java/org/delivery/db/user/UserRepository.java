package org.delivery.db.user;

import org.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<UserEntity, Long> 의미
//  1. 어떠한 Entity 를 참고할건지 지정.
//  2. AutoIncrement 된 PK 즉 Id 라는 어노테이션을 가진 변수의 타입
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    // select * from user where id =? and status = ? order by id desc limit 1
    // findFirstByIdAndStatusOrderByIdDesc 분석
    //  'FirstBy' : limit 1
    //	'Id' : 첫번째 파라미터
    //	'Status' : 두번째 파라미터
    //	나머지 구문은 위의 쿼리의 내용 반영
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);
    
    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
