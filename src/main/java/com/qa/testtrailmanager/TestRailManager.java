package com.qa.testtrailmanager;

import com.beust.ah.A;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {

    public static String TEST_RUN_ID= "1";
    public static String TEST_RAIL_USERNAME= "smruti.parekh@laitmatus.com";
    public static String TEST_RAIL_PASSWORD= "Smruti@1991";

    public static String TEST_RAIL_ENGINE_URL="https://smrutiautomation.testrail.io/";
    public static int TEST_CASE_PASS_STATUS=1;
    public static int TEST_CASE_FAIL_STATUS=5;

    public static void addResultsForTestCase(String testCaseId,int status,String error)
    {
        APIClient client=new APIClient(TEST_RAIL_ENGINE_URL);
        client.setUser(TEST_RAIL_USERNAME);
        client.setPassword(TEST_RAIL_PASSWORD);


        Map<String,Object> data=new HashMap <String,Object>();
        data.put("status_id",status);
        data.put("Comment","This test is executed via open cart test automation code" +error);

        try {
            client.sendPost("add_result_for_case/"+TEST_RUN_ID+"/"+ testCaseId,data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (APIException e) {
            throw new RuntimeException(e);
        }


    }





}
