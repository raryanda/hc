package com.raryanda.hc;

import com.raryanda.hc.service.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SpringTestServiceConfig {

    @Bean
    public GroupService getGroupService() {
        return new GroupServiceImpl();
    }

    @Bean
    public ModuleService getModuleService() {
        return new ModuleServiceImpl();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
