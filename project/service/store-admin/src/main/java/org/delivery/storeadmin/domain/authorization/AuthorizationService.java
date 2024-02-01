package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var storeUserEntity = storeUserService.getRegisterUser(username);
        var storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
            storeUserEntity.get().getStoreId(), StoreStatus.REGISTERED
        );
        
        // 1. localhost:8081에서 입력한 사용자 이메일(username)이 있다면 User 객체에 담음.
        // 2. 사용자가 입력한 암호를 가져와 UserDetails 에 있는 암호와 hash 로 비교.
        return storeUserEntity.map(it -> {
            var userSession = UserSession.builder()
                .userId(it.getId())
                .email(it.getEmail())
                .password(it.getPassword())
                .status(it.getStatus())
                .role(it.getRole())
                .registeredAt(it.getRegisteredAt())
                .lastLoginAt(it.getLastLoginAt())
                .unregisteredAt(it.getUnregisteredAt())

                .storeId(storeEntity.get().getId())
                .storeName(storeEntity.get().getName())
                .build();

            return userSession;
        })
        .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}