package com.example.vehicles;

import com.example.vehicles.controller.BoatController;
import com.example.vehicles.entity.BoatEnty;
import com.example.vehicles.service.BoatService;
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
public class BoatTest {

    private MockMvc mockMvc;

    @Mock
    private BoatService boatService;

    @InjectMocks
    private BoatController boatController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boatController)
                .addFilters()
                .build();



    }

    @Test
    public void findTest_success() throws Exception {
        List<BoatEnty> boats = new ArrayList<>();
        BoatEnty boat1 = new BoatEnty();
        boat1.setId(1);
        boat1.setType("Navy");


        boats.add(boat1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(boatService.getAll(params)).thenReturn(boats);

        mockMvc.perform(get("/boat"))
                .andExpect(status().isOk());

        verify(boatService, times(1)).getAll(params);
        verifyNoMoreInteractions(boatService);

    }

    @Test
    public void addBoat_success() throws Exception{
        BoatEnty boat2 = new BoatEnty();

        boat2.setType("Navy");


        when(boatService.add(boat2)).thenReturn(false);
        //doNothing().when(boatService).add(boat2);

        mockMvc.perform(
                put("/boat")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(boat2)))
                .andExpect(status().isOk());

        verify(boatService, times(1)).add(boat2);
        verifyNoMoreInteractions(boatService);

    }

    @Test
    public void update_success() throws Exception {
        BoatEnty boatEntity = new BoatEnty();

       boatEntity.setType("Navy");

        boatEntity.setId(1);
        when(boatService.get(boatEntity.getId())).thenReturn(boatEntity);
        when(boatService.update(boatEntity)).thenReturn(boatEntity);

        mockMvc.perform(
                post("/boat/{id_boat}",boatEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(boatEntity)))
                .andExpect(status().isOk());
//        verify(boatService, times(1)).get(1);
        verify(boatService, times(1)).update(boatEntity);
        verifyNoMoreInteractions(boatService);
    }

    @Test
    public void test_delete_boat_success() throws Exception {
        BoatEnty boatEntity = new BoatEnty();

        boatEntity.setType("Navy");


        boatEntity.setId(1);

        when(boatService.get(boatEntity.getId())).thenReturn(boatEntity);
        doNothing().when(boatService).remove(boatEntity.getId());
        mockMvc.perform(
                delete("/boat/{id_boat}", boatEntity.getId()))
                .andExpect(status().isOk());
        verify(boatService, times(1)).remove(boatEntity.getId());
        verifyNoMoreInteractions(boatService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
