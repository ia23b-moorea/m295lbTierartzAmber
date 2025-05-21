package com.example.m295lbtierartzamber.controller;

import com.example.m295lbtierartzamber.repository.TierRepository;
import com.example.m295lbtierartzamber.service.TierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.m295lbtierartzamber.model.Tier;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



//@WebMvcTest(TierarztController.class)
@SpringBootTest
@AutoConfigureMockMvc

class TierarztControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TierRepository repository;

    @MockBean
    private TierService service;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void shouldReturnEmptyList() throws Exception {

        when(repository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/tiere")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void getListOfAnimals() throws Exception {

        Tier tier1 = Tier.builder().name("blue").tierart("cat").id(1L).build();
        Tier tier2 = Tier.builder().name("green").tierart("lizard").id(2L).build();
        Tier tier3 = Tier.builder().name("red").tierart("bird").id(3L).build();

        List<Tier> tierList = List.of(tier1, tier2, tier3);
        when(service.findAll()).thenReturn(tierList);

        mockMvc.perform(get("/api/tiere")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tierList)));
    }

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void shouldGetTiere() throws Exception {
        Tier tier = Tier.builder().name("blue").id(1L).build();

        when(service.findById(1L)).thenReturn(Optional.of(tier));

        mockMvc.perform(get("/api/tiere/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tier)));
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void createTest() throws Exception {

        Tier tier = Tier.builder().name("blue").id(1L).gewicht(200).build();

        when(service.save(tier)).thenReturn(tier);

        mockMvc.perform(post("/api/tiere")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tier)))
                    .andExpect(status().isCreated());

    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void createTestWithNoWeight() throws Exception {
        Tier tier = Tier.builder().name("blue").id(1L).gewicht(0).build();

       when(service.save(tier)).thenReturn(tier);

        mockMvc.perform(post("/api/tiere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tier)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    void usersRoleShouldNotBeAbleToCreate() throws Exception {

        Tier tier = Tier.builder().name("blue").id(1L).build();

        when(service.save(tier)).thenReturn(tier);

        mockMvc.perform(post("/api/tiere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tier)))
                .andExpect(status().isForbidden());

    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void createTestLargeWeight() throws Exception {

        Tier tier = Tier.builder().name("blue").gewicht(10000000.0).id(1L).build();

        when(service.save(tier)).thenReturn(tier);

        mockMvc.perform(post("/api/tiere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tier)))
                .andExpect(status().isCreated());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void createShouldFailBlankName() throws Exception {

        Tier tier = Tier.builder().name("").id(1L).build();

        when(service.save(tier)).thenReturn(tier);

        mockMvc.perform(post("/api/tiere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tier)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void shouldNotFindTiere() throws Exception {
        // negative test, cannot find animal
        mockMvc.perform(get("/api/tiere/5"))
                .andExpect(status().isNotFound()
            );
    }


}