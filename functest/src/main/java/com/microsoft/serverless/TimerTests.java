package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;

public class TimerTests {

    public static void timerTriggerTest(ExecutionContext context) {
        context.getLogger().info(
                "Java Timer trigger function executed!");
    }

    
}