package com.yuesheng.cf;

import com.yuesheng.cf.utility.RecList;
import com.yuesheng.cf.utility.RecMap;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilTests {
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

    @Test
    @DisplayName("TestCase1: Rec Map - Null Input")
    public void RecMapNullTest() {
        List<Object> markList = null;
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        Map<Integer, Map<Integer, Double>> res = null;

        assertEquals(res, RecMap.GetRecMap(markList, trainDict),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase2: Rec Map - Empty Input")
    public void RecMapEmptyTest() {
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        Map<Integer, Map<Integer, Double>> res = null;

        assertEquals(res, RecMap.GetRecMap(markList, trainDict),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase3: Rec Map - Correct Input")
    public void RecMapCorrectTest() {
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        Map<Integer, Map<Integer, Double>> res = new HashMap<>();

        Object[] obj = {"Bob", 1, 1};
        markList.add((Object) obj);

        assertEquals(res, RecMap.GetRecMap(markList, trainDict),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase4: Rec Map - Func")
    public void RecMapFuncTest() {
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        Map<Integer, Map<Integer, Double>> res = new HashMap<>();

        String[] arr = {"Alice", "Bob", "Chris"};
        for (int i = 0; i < 4; i++) {
            Object[] obj = {arr[i * 2 % 3], i, 1};
            markList.add((Object) obj);
        }
        Map<Integer, Double> val = new HashMap<>();
        val.put(3, 1.0);
        res.put(0, val);
        val = new HashMap<>();
        val.put(0, 1.0);
        res.put(3, val);

        assertEquals(res, RecMap.GetRecMap(markList, trainDict),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase5: Rec List - Null Input")
    public void RecListNullTest() {
        String username = "Alice";
        Map<String, ArrayList<Integer>> trainDict = null;
        Map<Integer, Map<Integer, Double>> recMap = null;
        int sim_limit = 1;
        int rec_limi = 1;
        ArrayList<Integer> res = null;

        assertEquals(res, RecList.GetRecList(username, trainDict, recMap, sim_limit, rec_limi),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase6: Rec List - Empty Input")
    public void RecListEmptyTest() {
        String username = "Alice";
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        Map<Integer, Map<Integer, Double>> recMap = new HashMap<>();
        int sim_limit = 1;
        int rec_limi = 1;
        ArrayList<Integer> res = null;

        assertEquals(res, RecList.GetRecList(username, trainDict, recMap, sim_limit, rec_limi),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase7: Rec List - Correct Input")
    public void RecListCorrectTest() {
        String username = "Alice";
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        String[] arr = {"Alice", "Bob", "Chris"};
        for (int i = 0; i < 4; i++) {
            Object[] obj = {arr[i * 2 % 3], i, 1};
            markList.add((Object) obj);
        }

        Map<Integer, Map<Integer, Double>> recMap = RecMap.GetRecMap(markList, trainDict);
        int sim_limit = 1;
        int rec_limi = 1;
        ArrayList<Integer> res = new ArrayList<>();

        assertEquals(res, RecList.GetRecList(username, trainDict, recMap, sim_limit, rec_limi),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase8: Rec List - Func")
    public void RecListFuncTest() {
        String username = "Alice";
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        String[] arr = {"Alice", "Bob", "Chris", "David"};
        for (int i = 0; i < 15; i++) {
            Object[] obj = {arr[i * 2 % 4], i % 13, 1};
            markList.add((Object) obj);
        }

        Map<Integer, Map<Integer, Double>> recMap = RecMap.GetRecMap(markList, trainDict);
        int sim_limit = 5;
        int rec_limi = 5;
        ArrayList<Integer> res = new ArrayList<>();
        res.add(3);
        res.add(5);
        res.add(7);
        res.add(9);
        res.add(11);

        assertEquals(res, RecList.GetRecList(username, trainDict, recMap, sim_limit, rec_limi),
                () -> " should be " + res);
    }

    @Test
    @DisplayName("TestCase8: Rec List - Func Exception")
    public void RecListFuncExceptionTest() {
        String username = "Alice";
        List<Object> markList = new ArrayList<>();
        Map<String, ArrayList<Integer>> trainDict = new HashMap<>();
        String[] arr = {"Alice", "Bob", "Chris", "David"};
        for (int i = 0; i < 15; i++) {
            Object[] obj = {arr[i * 2 % 4], i % 13, 1};
            markList.add((Object) obj);
        }

        Map<Integer, Map<Integer, Double>> recMap = RecMap.GetRecMap(markList, trainDict);
        int sim_limit = 2;
        int rec_limi = 2;
        ArrayList<Integer> res = new ArrayList<>();
        res.add(3);
        res.add(5);
        res.add(7);

        assertEquals(res, RecList.GetRecList(username, trainDict, recMap, sim_limit, rec_limi),
                () -> " should be " + res);
    }
}
