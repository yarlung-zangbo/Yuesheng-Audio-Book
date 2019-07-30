package com.yuesheng.cf;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceTests {
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
    }

    @AfterEach
    public void tearDown() throws Exception {
        log.info("@AfterEach: tearDown()");
    }


}
