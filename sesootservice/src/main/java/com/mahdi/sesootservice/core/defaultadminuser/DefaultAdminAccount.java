package com.mahdi.sesootservice.core.defaultadminuser;

import com.mahdi.sesootservice.entity.Admin;
import com.mahdi.sesootservice.entity.Enum.Role;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mahdi.sesootservice"})
@RequiredArgsConstructor
public class DefaultAdminAccount {

    private final AdminService adminService;

    @Bean
    public CommandLineRunner appCommandLinRunner(AdminService adminService){
        return (args -> {
            Person person = Person.builder()
                    .fullName("admin")
                    .email("admin@gmail.com")
                    .password("admin")
                    .enabled(true)
                    .role(Role.ROLE_ADMIN)
                    .build();
            Admin admin = new Admin(person);
            adminService.addAdmin(admin);
        });
    }
}
