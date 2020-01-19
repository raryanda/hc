package com.raryanda.hc.dto.converter;

import com.raryanda.hc.model.Group;
import com.raryanda.hc.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupDtoConverterImpl implements GroupDtoConverter {

    @Autowired
    GroupModuleDtoConverter groupModuleDtoConverter;

    @Override
    public GroupDto convertToDTO(Group group) {
        GroupDto result = new GroupDto();
        result.setGroupName(group.getGroupName());
        if (group.getModules() != null)
            result.setModules(groupModuleDtoConverter.convertToListDTO(group.getModules()));

        return result;
    }
}
