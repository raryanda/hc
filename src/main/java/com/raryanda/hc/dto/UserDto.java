package com.raryanda.hc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String groupName;
    private List<GroupModuleDto> modules;
}
