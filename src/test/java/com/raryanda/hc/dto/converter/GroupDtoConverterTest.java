package com.raryanda.hc.dto.converter;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.TestUtil;
import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.model.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTestConverterConfig.class)
public class GroupDtoConverterTest {

    @Autowired
    GroupDtoConverter converter;

    @Autowired
    GroupModuleDtoConverter groupModuleDtoConverter;

    @Test
    public void convertGroupDto() {
        Group group = TestUtil.getDummyGroup();

        GroupDto groupDto = converter.convertToDTO(group);
        assertEquals(group.getGroupName(), groupDto.getGroupName());

        List<GroupDto> groupDtos = converter.convertToListDTO(Collections.singletonList(group));
        assertEquals(group.getGroupName(), groupDtos.get(0).getGroupName());
    }
}
