package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;

/**
 * Azure Functions with Timer trigger.
 */
public class TimerTests {

    /**
     * This function will be invoked periodically according to the specified schedule.
     */
    public static void timerTriggerTest(ExecutionContext context) {
        context.getLogger().info(
                "Java Timer trigger function executed!");
    }

    
}