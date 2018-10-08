package com.example.vehicles;

import com.example.vehicles.controller.AmphibianController;
import com.example.vehicles.entity.AmphibianEntity;
import com.example.vehicles.service.AmphibianService;
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
public class AmphibianTest {

    private MockMvc mockMvc;

    @Mock
    private AmphibianService amphibianService;

    @InjectMocks
    private AmphibianController amphibianController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(amphibianController)
                .addFilters()
                .build();




    }

    @Test
    public void findTest_success() throws Exception {
        List<AmphibianEntity> amphibians = new ArrayList<>();
        AmphibianEntity amphibian1 = new AmphibianEntity();
        amphibian1.setId(1);
        amphibian1.setType("Boeing");

        amphibians.add(amphibian1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(amphibianService.getAll(params)).thenReturn(amphibians);

        mockMvc.perform(get("/amphibian"))
                .andExpect(status().isOk());

        verify(amphibianService, times(1)).getAll(params);
        verifyNoMoreInteractions(amphibianService);

    }

    @Test
    public void addAirplane_success() throws Exception{
        AmphibianEntity amphibian2 = new AmphibianEntity();

        amphibian2.setType("Boeing");
        amphibian2.setId(3);

        when(amphibianService.add(amphibian2)).thenReturn(false);
        //doNothing().when(amphibianService).add(amphibian2);

        mockMvc.perform(
                put("/amphibian")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(amphibian2)))
                .andExpect(status().isOk());

        verify(amphibianService, times(1)).add(amphibian2);
        verifyNoMoreInteractions(amphibianService);

    }

    @Test
    public void update_success() throws Exception {
        AmphibianEntity amphibianEntity = new AmphibianEntity();

        amphibianEntity.setType("Boeing");


        amphibianEntity.setId(1);
        when(amphibianService.get(amphibianEntity.getId())).thenReturn(amphibianEntity);
        when(amphibianService.update(amphibianEntity)).thenReturn(amphibianEntity);

        mockMvc.perform(
                post("/amphibian/{id_amphibian}",amphibianEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(amphibianEntity)))
                .andExpect(status().isOk());
//        verify(amphibianService, times(1)).get(1);
        verify(amphibianService, times(1)).update(amphibianEntity);
        verifyNoMoreInteractions(amphibianService);
    }

    @Test
    public void test_delete_amphibian_success() throws Exception {
        AmphibianEntity amphibianEntity = new AmphibianEntity();

        amphibianEntity.setType("Boeing");


        amphibianEntity.setId(1);

        when(amphibianService.get(amphibianEntity.getId())).thenReturn(amphibianEntity);
        doNothing().when(amphibianService).remove(amphibianEntity.getId());
        mockMvc.perform(
                delete("/amphibian/{id_amphibian}", amphibianEntity.getId()))
                .andExpect(status().isOk());
        verify(amphibianService, times(1)).remove(amphibianEntity.getId());
        verifyNoMoreInteractions(amphibianService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
