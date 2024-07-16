package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.domain.entity.UserEntity;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.repository.UserRepositoy;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserRepositoy userRepositoy, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            var roleAdmin = roleRepository.findByName("ADMIN");
            if(userRepositoy.findByUsername("admin") == null && roleAdmin != null){

                UserEntity user = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
                        .email("admin@ecommerce.com")
                        .roles(new HashSet<>() {{
                            add(roleAdmin);
                        }})
                        .build();

                userRepositoy.save(user);
            }
        };
    }
}
