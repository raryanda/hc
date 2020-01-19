package com.raryanda.hc.controller;

import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.model.Module;
import com.raryanda.hc.service.GroupService;
import com.raryanda.hc.service.ModuleService;
import com.raryanda.hc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    ModuleService moduleService;

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUserDetail(@RequestParam Long userId) {
        return userService.getUserDetail(userId);
    }

    @GetMapping(value = "groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupDto> group(
            @RequestParam(required = false) Long groupId,
            @RequestParam(required = false) Long moduleId,
            @RequestParam(required = false) String operation) {
        if (groupId == null || moduleId == null || StringUtils.isEmpty(operation)) {
            return groupService.getAllGroups();
        } else {
            return Collections.singletonList(groupService.setGroupModuleOrder(groupId, moduleId, operation));
        }
    }

    @GetMapping(value = "modules", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    /*private List<GroupModuleDto> convertToDto(List<GroupModule> modules) {
        return modules
                .stream()
                .map(module -> {
                    GroupModuleDto moduleDto = new GroupModuleDto();
                    moduleDto.setModuleName(module.getModule().getModuleName());
                    moduleDto.setModuleOrder(module.getModuleOrder());
                    return moduleDto;
                })
                .sorted(Comparator.comparingLong(GroupModuleDto::getModuleOrder))
                .collect(Collectors.toList());
    }*/
}


