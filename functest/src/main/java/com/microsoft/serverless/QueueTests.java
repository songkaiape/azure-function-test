package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.HttpRequestMessage;


public class QueueTests {

    public static void queueTriggerTest(@BindingName("myQueueItem") String message, ExecutionContext context) {
        context.getLogger().info(
                "Java Queue trigger function processed a message:  "+message);
    }

    public static String queueOut(HttpRequestMessage request, ExecutionContext context) {
        context.getLogger().info("QueueOut process message"+request.getBody().toString());
        return request.getBody().toString();
    }
    
}