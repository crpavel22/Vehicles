package com.example.vehicles;

import com.example.vehicles.controller.TruckController;
import com.example.vehicles.entity.TruckEntity;
import com.example.vehicles.service.TruckService;
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
public class TruckTest {

    private MockMvc mockMvc;

    @Mock
    private TruckService truckService;

    @InjectMocks
    private TruckController truckController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(truckController)
                .addFilters()
                .build();




    }

    @Test
    public void findTest_success() throws Exception {
        List<TruckEntity> trucks = new ArrayList<>();
        TruckEntity truck1 = new TruckEntity();
        truck1.setId(1);
        truck1.setType("Boeing");

        trucks.add(truck1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(truckService.getAll(params)).thenReturn(trucks);

        mockMvc.perform(get("/truck"))
                .andExpect(status().isOk());

        verify(truckService, times(1)).getAll(params);
        verifyNoMoreInteractions(truckService);

    }

    @Test
    public void addAirplane_success() throws Exception{
        TruckEntity truck2 = new TruckEntity();

        truck2.setType("Boeing");
        truck2.setId(3);

        when(truckService.add(truck2)).thenReturn(false);
        //doNothing().when(truckService).add(truck2);

        mockMvc.perform(
                put("/truck")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(truck2)))
                .andExpect(status().isOk());

        verify(truckService, times(1)).add(truck2);
        verifyNoMoreInteractions(truckService);

    }

    @Test
    public void update_success() throws Exception {
        TruckEntity truckEntity = new TruckEntity();

        truckEntity.setType("Boeing");


        truckEntity.setId(1);
        when(truckService.get(truckEntity.getId())).thenReturn(truckEntity);
        when(truckService.update(truckEntity)).thenReturn(truckEntity);

        mockMvc.perform(
                post("/truck/{id_truck}",truckEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(truckEntity)))
                .andExpect(status().isOk());
//        verify(truckService, times(1)).get(1);
        verify(truckService, times(1)).update(truckEntity);
        verifyNoMoreInteractions(truckService);
    }

    @Test
    public void test_delete_truck_success() throws Exception {
        TruckEntity truckEntity = new TruckEntity();

        truckEntity.setType("Boeing");


        truckEntity.setId(1);

        when(truckService.get(truckEntity.getId())).thenReturn(truckEntity);
        doNothing().when(truckService).remove(truckEntity.getId());
        mockMvc.perform(
                delete("/truck/{id_truck}", truckEntity.getId()))
                .andExpect(status().isOk());
        verify(truckService, times(1)).remove(truckEntity.getId());
        verifyNoMoreInteractions(truckService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
