package com.musinsa.api;

import com.musinsa.api.search.controller.SearchController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class MusinsaApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SearchController searchController;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("API 성공 테스트")
    public void Api_200() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("학생 조회 성공"))
//                .andExpect(jsonPath("$.data.name").value("yewon"))
//                .andExpect(jsonPath("$.data.dept").value("computer-system"))
                .andDo(print());
    }

    @Test
    @DisplayName("API 실패 테스트")
    public void Api_400() throws Exception {
        mockMvc.perform(get("/hello-world"))
            .andExpect(status().is4xxClientError())
//            .andExpect(jsonPath("$.message").value("학생 조회 실패"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andDo(print());
    }

    @Test
    @DisplayName("API 실패 테스트")
    public void Api_500() throws Exception {
        mockMvc.perform(get("/hello-world"))
            .andExpect(status().is5xxServerError())
//            .andExpect(jsonPath("$.message").value("학생 조회 실패"))
            .andExpect(jsonPath("$.data").isEmpty())
            .andDo(print());
    }

}
