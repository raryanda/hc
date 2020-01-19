package com.raryanda.hc.service;

import com.raryanda.hc.dto.GroupDto;

import java.util.List;

public interface GroupService {
    List<GroupDto> getAllGroups();

    GroupDto setGroupModuleOrder(Long groupId, Long moduleId, String operation);
}
