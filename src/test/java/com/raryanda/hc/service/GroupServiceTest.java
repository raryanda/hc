package com.raryanda.hc.service;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.SpringTestServiceConfig;
import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.dto.converter.GroupDtoConverter;
import com.raryanda.hc.dto.converter.GroupModuleDtoConverter;
import com.raryanda.hc.exception.DataNotFoundException;
import com.raryanda.hc.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringTestServiceConfig.class, SpringTestConverterConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase
@EntityScan("com.raryanda.hc.model")
@EnableJpaRepositories("com.raryanda.hc.repository")
public class GroupServiceTest {

    @Autowired
    GroupService groupService;

    @Autowired
    GroupRepository repository;

    @Autowired
    GroupDtoConverter converter;

    @Autowired
    GroupModuleDtoConverter groupModuleDtoConverter;

    @Test
    public void getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        assertEquals(groups.get(0).getGroupName(), "Group A");
        assertEquals(groups.get(1).getGroupName(), "Group B");
        assertEquals(groups.get(2).getGroupName(), "Group C");

        assertEquals(groups.get(0).getModules().get(1).getModuleName(), "Category");
        assertEquals(groups.get(1).getModules().get(1).getModuleName(), "News");
        assertEquals(groups.get(2).getModules().get(1).getModuleName(), "FlashSale");
    }

    @Test
    public void setGroupModuleOrder() {
        GroupDto groupDto = groupService.setGroupModuleOrder(1L, 1L, "up");
        assertEquals(groupDto.getModules().get(0).getModuleName(), "Category");
        assertEquals(groupDto.getModules().get(1).getModuleName(), "Promo");

        groupDto = groupService.setGroupModuleOrder(1L, 1L, "up");
        assertEquals(groupDto.getModules().get(0).getModuleName(), "Category");
        assertEquals(groupDto.getModules().get(2).getModuleName(), "FlashSale");

        groupDto = groupService.setGroupModuleOrder(1L, 4L, "down");
        assertEquals(groupDto.getModules().get(3).getModuleName(), "FlashSale");

        groupDto = groupService.setGroupModuleOrder(1L, 4L, "down");
        assertEquals(groupDto.getModules().get(4).getModuleName(), "FlashSale");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setGroupModuleOrder_noOperation() {
        groupService.setGroupModuleOrder(1L, 1L, "");
    }

    @Test(expected = DataNotFoundException.class)
    public void setGroupModuleOrder_notFoundGroup() {
        groupService.setGroupModuleOrder(4L, 1L, "up");
    }

    @Test(expected = DataNotFoundException.class)
    public void setGroupModuleOrder_notFoundModule() {
        groupService.setGroupModuleOrder(1L, 6L, "up");
    }
}
