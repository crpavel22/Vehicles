package com.example.vehicles;

import com.example.vehicles.controller.AirplaneController;
import com.example.vehicles.entity.AirplaneEntity;
import com.example.vehicles.service.AirplaneService;
import com.example.vehicles.utils.SearchCriteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/db-config.xml", "classpath*:spring/application-config.xml"})
@WebAppConfiguration
public class AirplaneTest {

    private MockMvc mockMvc;

    @Mock
    private AirplaneService airplaneService;

    @InjectMocks
    private AirplaneController airplaneController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airplaneController)
                .addFilters()
                .build();




    }

    @Test
    public void findTest_success() throws Exception {
        List<AirplaneEntity> airplanes = new ArrayList<>();
        AirplaneEntity airplane1 = new AirplaneEntity();
        airplane1.setId(1);
        airplane1.setType("Boeing");

        airplanes.add(airplane1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(airplaneService.getAll(params)).thenReturn(airplanes);

        mockMvc.perform(get("/airplane"))
                .andExpect(status().isOk());

        verify(airplaneService, times(1)).getAll(params);
        verifyNoMoreInteractions(airplaneService);

    }

    @Test
    public void addAirplane_success() throws Exception{
        AirplaneEntity airplane2 = new AirplaneEntity();

        airplane2.setType("Boeing");
        airplane2.setId(3);

        when(airplaneService.add(airplane2)).thenReturn(false);
        //doNothing().when(airplaneService).add(airplane2);

        mockMvc.perform(
                put("/airplane")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(airplane2)))
                .andExpect(status().isOk());

        verify(airplaneService, times(1)).add(airplane2);
        verifyNoMoreInteractions(airplaneService);

    }

    @Test
    public void update_success() throws Exception {
        AirplaneEntity airplaneEntity = new AirplaneEntity();

        airplaneEntity.setType("Boeing");


        airplaneEntity.setId(1);
        when(airplaneService.get(airplaneEntity.getId())).thenReturn(airplaneEntity);
        when(airplaneService.update(airplaneEntity)).thenReturn(airplaneEntity);

        mockMvc.perform(
                post("/airplane/{id_airplane}",airplaneEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(airplaneEntity)))
                .andExpect(status().isOk());
//        verify(airplaneService, times(1)).get(1);
        verify(airplaneService, times(1)).update(airplaneEntity);
        verifyNoMoreInteractions(airplaneService);
    }

    @Test
    public void test_delete_airplane_success() throws Exception {
        AirplaneEntity airplaneEntity = new AirplaneEntity();

        airplaneEntity.setType("Boeing");


        airplaneEntity.setId(1);

        when(airplaneService.get(airplaneEntity.getId())).thenReturn(airplaneEntity);
        doNothing().when(airplaneService).remove(airplaneEntity.getId());
        mockMvc.perform(
                delete("/airplane/{id_airplane}", airplaneEntity.getId()))
                .andExpect(status().isOk());
        verify(airplaneService, times(1)).remove(airplaneEntity.getId());
        verifyNoMoreInteractions(airplaneService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
