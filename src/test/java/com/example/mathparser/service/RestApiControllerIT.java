package com.example.mathparser.service;

import com.example.mathparser.dto.InputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldGetSortedHistory() throws Exception {
        final InputDto firstInput = new InputDto("3*3");

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("9")));

        final InputDto secondInput = new InputDto("3-3*10");

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(secondInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("-27")));

        mockMvc.perform(get("/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].operation", is("3-3*10")))
                .andExpect(jsonPath("$[0].result", is("-27")))
                .andExpect(jsonPath("$[0].dateTime", notNullValue()))
                .andExpect(jsonPath("$[1].operation", is("3*3")))
                .andExpect(jsonPath("$[1].result", is("9")))
                .andExpect(jsonPath("$[1].dateTime", notNullValue()));
    }

    @Test
    public void shouldReturnErrorWhenDivideByZero() throws Exception {
        final InputDto errorInput = new InputDto("3*3/0");

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(errorInput)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Cannot divide by 0.")));
    }

    @Test
    public void shouldReturnErrorWhenInvalidFormat() throws Exception {
        final InputDto errorInput = new InputDto("3*");

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(errorInput)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid equation format.")));
    }

    @Test
    public void shouldReturnErrorWhenUnknownOperation() throws Exception {
        final InputDto errorInput = new InputDto("3^9");

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(errorInput)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Unknown operation.")));
    }

}
