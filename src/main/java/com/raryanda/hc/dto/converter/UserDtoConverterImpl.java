package com.raryanda.hc.dto.converter;

import com.raryanda.hc.model.User;
import com.raryanda.hc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {

    @Autowired
    GroupModuleDtoConverter groupModuleDtoConverter;

    @Override
    public UserDto convertToDTO(User user) {
        UserDto result = new UserDto();
        result.setUserName(user.getUserName());
        result.setGroupName(user.getGroup().getGroupName());
        if (user.getGroup().getModules() != null)
            result.setModules(groupModuleDtoConverter.convertToListDTO(user.getGroup().getModules()));

        return result;
    }
}
