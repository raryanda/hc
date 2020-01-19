package com.raryanda.hc;

import com.raryanda.hc.dto.converter.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SpringTestConverterConfig {

    @Bean
    public GroupDtoConverter getGroupDtoConverter() {
        return new GroupDtoConverterImpl();
    }

    @Bean
    public GroupModuleDtoConverter geGroupModuleDtoConverter() {
        return new GroupModuleDtoConverterImpl();
    }

    @Bean
    public UserDtoConverter getUserDtoConverter() {
        return new UserDtoConverterImpl();
    }
}
