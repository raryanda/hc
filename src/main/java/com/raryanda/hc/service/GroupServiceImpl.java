package com.raryanda.hc.service;

import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.dto.converter.GroupDtoConverter;
import com.raryanda.hc.exception.DataNotFoundException;
import com.raryanda.hc.model.Group;
import com.raryanda.hc.model.GroupModule;
import com.raryanda.hc.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository repository;

    @Autowired
    GroupDtoConverter converter;

    private static final String OPERATION_UP = "up";
    private static final String OPERATION_DOWN = "down";
    private static final List<String> operations = Collections.unmodifiableList(Arrays.asList(OPERATION_UP, OPERATION_DOWN));

    @Override
    public List<GroupDto> getAllGroups() {
        return converter.convertToListDTO(repository.findAll());
    }

    @Override
    public GroupDto setGroupModuleOrder(Long groupId, Long moduleId, String operation) {
        if (!operations.contains(operation.toLowerCase())) {
            throw new UnsupportedOperationException("Operation is not supported");
        }

        Group group = repository.findById(groupId).orElseThrow(() -> new DataNotFoundException("Group", "id", groupId));
        List<GroupModule> modules = group.getModules();
        modules.sort(Comparator.comparingLong(GroupModule::getModuleOrder));

        GroupModule moduleToMove = modules.stream()
                .filter(mod -> mod.getModule().getModuleId().equals(moduleId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Module", "id", moduleId));
        if (moduleToMove != null) {
            int moduleIdx = modules.indexOf(moduleToMove);
            if (operation.equalsIgnoreCase(OPERATION_DOWN) && moduleIdx != modules.size() - 1) {
                // move order down
                GroupModule moduleToSwap = modules.get(moduleIdx + 1);
                moduleToMove.setModuleOrder(moduleToMove.getModuleOrder() + 1);
                moduleToSwap.setModuleOrder(moduleToSwap.getModuleOrder() - 1);
            } else if (operation.equalsIgnoreCase(OPERATION_UP) && moduleIdx != 0) {
                // move order up
                GroupModule moduleToSwap = modules.get(moduleIdx - 1);
                moduleToMove.setModuleOrder(moduleToMove.getModuleOrder() - 1);
                moduleToSwap.setModuleOrder(moduleToSwap.getModuleOrder() + 1);
            } else {
                // already at the edge, do nothing
                return converter.convertToDTO(group);
            }
        }
        return converter.convertToDTO(repository.save(group));
    }
}
