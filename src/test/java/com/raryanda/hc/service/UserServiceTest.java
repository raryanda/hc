package com.raryanda.hc.service;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.SpringTestServiceConfig;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.dto.converter.UserDtoConverter;
import com.raryanda.hc.exception.DataNotFoundException;
import com.raryanda.hc.model.User;
import com.raryanda.hc.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringTestServiceConfig.class, SpringTestConverterConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase
@EntityScan("com.raryanda.hc.model")
@EnableJpaRepositories("com.raryanda.hc.repository")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;

    @Autowired
    UserDtoConverter converter;

    @Test
    public void getUserDetail() {
        UserDto userDto = userService.getUserDetail(1L);
        assertEquals(userDto.getUserName(), "User A");

        User user = repository.findById(1L).orElse(null);
        assert user != null;
        user.setUserName("R");
        repository.save(user);

        userDto = userService.getUserDetail(1L);
        assertEquals(userDto.getUserName(), "R");
    }

    @Test(expected = DataNotFoundException.class)
    public void getUserDetail_notFound() {
        userService.getUserDetail(4L);
    }
}
