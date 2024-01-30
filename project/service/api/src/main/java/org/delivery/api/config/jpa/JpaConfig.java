package org.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// api 프로젝트에서 db 프로젝트에 있는 클래스를 스캔하거나 참조할때
// 프로젝트 패키지 위치가 달라서 발생하는 문제를 해결하기 위한 config 파일

@Configuration
@EntityScan(basePackages = "org.delivery.db")
@EnableJpaRepositories(basePackages = "org.delivery.db")
public class JpaConfig {
}
