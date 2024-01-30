package com.example.jpa.user.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM 매핑

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user") // user 테이블과 매칭
public class UserEntity {

    // id 는 PK이고 MySQL에 의해 Auto-Generated될 것임.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
