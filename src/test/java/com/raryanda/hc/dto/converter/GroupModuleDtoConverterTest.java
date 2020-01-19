package com.raryanda.hc.dto.converter;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.TestUtil;
import com.raryanda.hc.dto.GroupModuleDto;
import com.raryanda.hc.model.GroupModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTestConverterConfig.class)
public class GroupModuleDtoConverterTest {

    @Autowired
    GroupModuleDtoConverter converter;

    @Test
    public void convertGroupModuleDto() {
        GroupModule groupModule1 = TestUtil.getDummyGroupModule();
        groupModule1.setGroup(TestUtil.getDummyGroup());
        groupModule1.setModuleOrder(2L);

        GroupModuleDto groupModuleDto = converter.convertToDTO(groupModule1);
        assertEquals(groupModule1.getModule().getModuleName(), groupModuleDto.getModuleName());

        GroupModule groupModule2 = TestUtil.getDummyGroupModule();
        groupModule2.setGroup(TestUtil.getDummyGroup());
        groupModule2.setModuleOrder(1L);

        List<GroupModuleDto> groupModuleDtos = converter.convertToListDTO(Arrays.asList(groupModule1, groupModule2));
        assertThat(groupModuleDtos.get(0).getModuleOrder(), is(1L));
        assertThat(groupModuleDtos.get(1).getModuleOrder(), is(2L));
    }
}
