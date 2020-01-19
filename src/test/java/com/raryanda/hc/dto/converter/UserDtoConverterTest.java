package com.raryanda.hc.dto.converter;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.TestUtil;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTestConverterConfig.class)
public class UserDtoConverterTest {

    @Autowired
    UserDtoConverter converter;

    @Autowired
    GroupModuleDtoConverter groupModuleDtoConverter;

    @Test
    public void convertUserDto() {
        User user = TestUtil.getDummyUser();

        UserDto userDto = converter.convertToDTO(user);
        assertEquals(user.getUserName(), userDto.getUserName());
        assertEquals(user.getGroup().getGroupName(), userDto.getGroupName());

        List<UserDto> userDtos = converter.convertToListDTO(Collections.singletonList(user));
        assertEquals(user.getUserName(), userDtos.get(0).getUserName());
        assertEquals(user.getGroup().getGroupName(), userDtos.get(0).getGroupName());
    }
}
