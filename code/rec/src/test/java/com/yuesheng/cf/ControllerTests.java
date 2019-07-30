package com.yuesheng.cf;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private String listUrl = new String("/getRecList");
    private String mapUrl = new String("/updateRecMap");

    private static final Logger log = LoggerFactory.getLogger(UtilTests.class);

    @BeforeAll
    public static void init() {
        log.info("@BeforeAll: init()");
    }

    @AfterAll
    public static void done() {
        log.info("@AfterAll: done()");
    }

    @BeforeEach
    public void setUp() throws Exception {
        log.info("@BeforeEach: setUp()");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        log.info("@AfterEach: tearDown()");
    }

    @ParameterizedTest(name = "{0} {1}")
    @DisplayName("TestCase1: Rec List Correct")
    @CsvSource({
            "Abbie32, 200",
            "ABC, 200"
    })
    public void listCorrectTest(String username, int status) throws Exception {
        mockMvc.perform(get(listUrl)
                .param("username", username))
                // assert response
                .andExpect(status().is(status));

    }

    @ParameterizedTest(name = "{0} {1}")
    @DisplayName("TestCase2: Map List Correct")
    @CsvSource({
            "200"
    })
    public void mapCorrectTest(int status) throws Exception {
        mockMvc.perform(get(mapUrl))
                // assert response
                .andExpect(status().is(status));

    }
}
