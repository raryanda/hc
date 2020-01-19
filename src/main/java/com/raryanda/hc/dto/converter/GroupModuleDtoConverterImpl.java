package com.raryanda.hc.dto.converter;

import com.raryanda.hc.model.GroupModule;
import com.raryanda.hc.dto.GroupModuleDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class GroupModuleDtoConverterImpl implements GroupModuleDtoConverter {

    @Override
    public GroupModuleDto convertToDTO(GroupModule groupModule) {
        GroupModuleDto result = new GroupModuleDto();
        result.setModuleName(groupModule.getModule().getModuleName());
        result.setModuleOrder(groupModule.getModuleOrder());

        return result;
    }

    @Override
    public List<GroupModuleDto> convertToListDTO(List<GroupModule> groupModules) {
        List<GroupModuleDto> result = GroupModuleDtoConverter.super.convertToListDTO(groupModules);
        result.sort(Comparator.comparingLong(GroupModuleDto::getModuleOrder));

        return result;
    }
}
