package com.raryanda.hc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.raryanda.hc.TestUtil;
import com.raryanda.hc.dto.GroupDto;
import com.raryanda.hc.dto.UserDto;
import com.raryanda.hc.model.Module;
import com.raryanda.hc.service.GroupService;
import com.raryanda.hc.service.ModuleService;
import com.raryanda.hc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    GroupService groupService;

    @MockBean
    ModuleService moduleService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getUserDetail() throws Exception {
        UserDto userDto = TestUtil.getDummyUserDto();
        when(userService.getUserDetail(anyLong())).thenReturn(userDto);

        // bad request because no id provided
        mockMvc.perform(get("/users"))
                .andExpect(status().isBadRequest());

        MvcResult result = mockMvc.perform(get("/users?userId=1"))
                .andExpect(status().isOk())
                .andReturn();

        UserDto resultDto = mapper.readValue(result.getResponse().getContentAsString(), UserDto.class);
        assertEquals(userDto.getUserName(), resultDto.getUserName());
        assertEquals(userDto.getGroupName(), resultDto.getGroupName());
    }

    @Test
    public void group() throws Exception {
        List<GroupDto> groupDto = Arrays.asList(TestUtil.getDummyGroupDto(), TestUtil.getDummyGroupDto());
        when(groupService.getAllGroups()).thenReturn(groupDto);
        when(groupService.setGroupModuleOrder(any(), any(), any())).thenReturn(groupDto.get(0));

        // empty groupId
        List<GroupDto> resultDto = (List<GroupDto>) getResultFromRequest(groupDto, "get", "/groups", null);
        assertEquals(resultDto.size(), 2);
        assertEquals(groupDto.get(0).getGroupName(), resultDto.get(0).getGroupName());
        assertEquals(groupDto.get(1).getGroupName(), resultDto.get(1).getGroupName());

        // empty moduleId
        resultDto = (List<GroupDto>) getResultFromRequest(groupDto, "get", "/groups?groupId=1", null);
        assertEquals(resultDto.size(), 2);
        assertEquals(groupDto.get(0).getGroupName(), resultDto.get(0).getGroupName());
        assertEquals(groupDto.get(1).getGroupName(), resultDto.get(1).getGroupName());

        // empty operation
        resultDto = (List<GroupDto>) getResultFromRequest(groupDto, "get", "/groups?groupId=1&moduleId=1", null);
        assertEquals(resultDto.size(), 2);
        assertEquals(groupDto.get(0).getGroupName(), resultDto.get(0).getGroupName());
        assertEquals(groupDto.get(1).getGroupName(), resultDto.get(1).getGroupName());

        resultDto = (List<GroupDto>) getResultFromRequest(groupDto, "get", "/groups?groupId=1&moduleId=1&operation=up", null);
        assertEquals(resultDto.size(), 1);
        assertEquals(groupDto.get(0).getGroupName(), resultDto.get(0).getGroupName());
        assertEquals(groupDto.get(0).getModules().size(), resultDto.get(0).getModules().size());
    }

    @Test
    public void getAllModules() throws Exception {
        List<Module> modules = Collections.singletonList(TestUtil.getDummyModule());
        when(moduleService.getAllModules()).thenReturn(modules);

        List<Module> resultDto = (List<Module>) getResultFromRequest(modules, "get", "/modules", null);
        assertEquals(modules.size(), 1);
        assertEquals(modules.get(0).getModuleId(), resultDto.get(0).getModuleId());
        assertEquals(modules.get(0).getModuleName(), resultDto.get(0).getModuleName());
    }

    private Object getResultFromRequest(Object object, String requestType, String url, Object request) throws Exception {
        MockHttpServletRequestBuilder requestBuilder;

        switch (requestType.toLowerCase()) {
            case "post":
                requestBuilder = post(url)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON);
                break;
            case "get":
                requestBuilder = get(url);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String resultString = result.getResponse().getContentAsString();
        if (object instanceof List) {
            Class clasz = !((List) object).isEmpty() ? ((List) object).get(0).getClass() : object.getClass();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clasz);
            return mapper.readValue(resultString, listType);
        }
        return mapper.readValue(resultString, object.getClass());
    }
}
