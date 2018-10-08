package com.example.vehicles;

import com.example.vehicles.controller.CarController;
import com.example.vehicles.entity.CarEntity;
import com.example.vehicles.model.CarModel;
import com.example.vehicles.service.CarService;
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
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/db-config.xml", "classpath*:spring/application-config.xml"})
@WebAppConfiguration
public class CarTest {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .addFilters()
                .build();

        CarEntity car2 = new CarEntity();

        car2.setYearModel(2018);
        car2.setProvider("Mazda");
        car2.setDoorsNumber(3);
        car2.setModel("M2");

        CarEntity car1 = new CarEntity();

        car1.setYearModel(2015);
        car1.setProvider("Nossan");
        car1.setDoorsNumber(3);
        car1.setModel("n222");


    }

    @Test
    public void findTest_success() throws Exception {
        List<CarEntity> cars = new ArrayList<>();
        CarEntity car1 = new CarEntity();
        car1.setId(1);
        car1.setYearModel(2018);
        car1.setProvider("Mazda");

        cars.add(car1);

        List<SearchCriteria> params = Arrays.asList(
                //new SearchCriteria("provider",":","Mazda")
        );

        when(carService.getAll(params)).thenReturn(cars);

        mockMvc.perform(get("/car"))
                .andExpect(status().isOk());

        verify(carService, times(1)).getAll(params);
        verifyNoMoreInteractions(carService);

    }

    @Test
    public void addCar_success() throws Exception{
        CarEntity car2 = new CarEntity();

        car2.setYearModel(2018);
        car2.setProvider("Mazda");
        car2.setDoorsNumber(3);
        car2.setModel("M2");

        when(carService.add(car2)).thenReturn(false);
        //doNothing().when(carService).add(car2);

        mockMvc.perform(
                put("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(car2)))
                .andExpect(status().isOk());

        verify(carService, times(1)).add(car2);
        verifyNoMoreInteractions(carService);

    }

    @Test
    public void update_success() throws Exception {
        CarEntity carEntity = new CarEntity();

        carEntity.setYearModel(2018);
        carEntity.setProvider("Mazda");
        carEntity.setDoorsNumber(3);
        carEntity.setModel("M2");

        carEntity.setId(1);
        when(carService.get(carEntity.getId())).thenReturn(carEntity);
        when(carService.update(carEntity)).thenReturn(carEntity);

        mockMvc.perform(
                post("/car/{id_car}",carEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carEntity)))
                .andExpect(status().isOk());
//        verify(carService, times(1)).get(1);
        verify(carService, times(1)).update(carEntity);
        verifyNoMoreInteractions(carService);
    }

    @Test
    public void test_delete_car_success() throws Exception {
        CarEntity carEntity = new CarEntity();

        carEntity.setYearModel(2018);
        carEntity.setProvider("Mazda");
        carEntity.setDoorsNumber(3);
        carEntity.setModel("M2");

        carEntity.setId(1);

        when(carService.get(carEntity.getId())).thenReturn(carEntity);
        doNothing().when(carService).remove(carEntity.getId());
        mockMvc.perform(
                delete("/car/{id_car}", carEntity.getId()))
                .andExpect(status().isOk());
        verify(carService, times(1)).remove(carEntity.getId());
        verifyNoMoreInteractions(carService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
