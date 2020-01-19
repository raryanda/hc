package com.raryanda.hc;

import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.dto.GroupModuleDto;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.model.Group;
import com.raryanda.hc.model.GroupModule;
import com.raryanda.hc.model.Module;
import com.raryanda.hc.model.User;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static UserDto getDummyUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName("R");
        userDto.setGroupName("G");

        return userDto;
    }

    public static GroupDto getDummyGroupDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName("G");
        groupDto.setModules(getDummyGroupModulesDto());

        return groupDto;
    }

    public static List<GroupModuleDto> getDummyGroupModulesDto() {
        GroupModuleDto groupModuleDto1 = new GroupModuleDto();
        groupModuleDto1.setModuleName("M1");
        groupModuleDto1.setModuleOrder(1L);

        GroupModuleDto groupModuleDto2 = new GroupModuleDto();
        groupModuleDto2.setModuleName("M2");
        groupModuleDto2.setModuleOrder(2L);

        return Arrays.asList(groupModuleDto1, groupModuleDto2);
    }

    public static Module getDummyModule() {
        Module module = new Module();
        module.setModuleId(1L);
        module.setModuleName("M1");

        return module;
    }

    public static GroupModule getDummyGroupModule() {
        GroupModule groupModule = new GroupModule();
        groupModule.setModule(getDummyModule());

        return groupModule;
    }

    public static Group getDummyGroup() {
        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupName("G");

        return group;
    }

    public static User getDummyUser() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("U");
        user.setGroup(getDummyGroup());

        return user;
    }
}
