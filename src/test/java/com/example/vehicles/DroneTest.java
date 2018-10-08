package com.example.vehicles;

import com.example.vehicles.controller.DroneController;
import com.example.vehicles.entity.DroneEntity;
import com.example.vehicles.service.DroneService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/db-config.xml", "classpath*:spring/application-config.xml"})
@WebAppConfiguration
public class DroneTest {

    private MockMvc mockMvc;

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(droneController)
                .addFilters()
                .build();




    }

    @Test
    public void findTest_success() throws Exception {
        List<DroneEntity> drones = new ArrayList<>();
        DroneEntity drone1 = new DroneEntity();
        drone1.setId(1);
        drone1.setType("Boeing");

        drones.add(drone1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(droneService.getAll(params)).thenReturn(drones);

        mockMvc.perform(get("/drone"))
                .andExpect(status().isOk());

        verify(droneService, times(1)).getAll(params);
        verifyNoMoreInteractions(droneService);

    }

    @Test
    public void addAirplane_success() throws Exception{
        DroneEntity drone2 = new DroneEntity();

        drone2.setType("Boeing");
        drone2.setId(3);

        when(droneService.add(drone2)).thenReturn(false);
        //doNothing().when(droneService).add(drone2);

        mockMvc.perform(
                put("/drone")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(drone2)))
                .andExpect(status().isOk());

        verify(droneService, times(1)).add(drone2);
        verifyNoMoreInteractions(droneService);

    }

    @Test
    public void update_success() throws Exception {
        DroneEntity droneEntity = new DroneEntity();

        droneEntity.setType("Boeing");


        droneEntity.setId(1);
        when(droneService.get(droneEntity.getId())).thenReturn(droneEntity);
        when(droneService.update(droneEntity)).thenReturn(droneEntity);

        mockMvc.perform(
                post("/drone/{id_drone}",droneEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(droneEntity)))
                .andExpect(status().isOk());
//        verify(droneService, times(1)).get(1);
        verify(droneService, times(1)).update(droneEntity);
        verifyNoMoreInteractions(droneService);
    }

    @Test
    public void test_delete_drone_success() throws Exception {
        DroneEntity droneEntity = new DroneEntity();

        droneEntity.setType("Boeing");


        droneEntity.setId(1);

        when(droneService.get(droneEntity.getId())).thenReturn(droneEntity);
        doNothing().when(droneService).remove(droneEntity.getId());
        mockMvc.perform(
                delete("/drone/{id_drone}", droneEntity.getId()))
                .andExpect(status().isOk());
        verify(droneService, times(1)).remove(droneEntity.getId());
        verifyNoMoreInteractions(droneService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
