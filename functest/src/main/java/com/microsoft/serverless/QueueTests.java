package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.HttpRequestMessage;

/**
 * Azure Functions with Azure Storage Queue trigger.
 */
public class QueueTests {

    /**
     * This function will be invoked when a new message is received at the specified path. The message contents are provided as input to this function.
     */
    public static void queueTriggerTest(@BindingName("myQueueItem") String message, ExecutionContext context) {
        context.getLogger().info(
                "Java Queue trigger function processed a message:  "+message);
    }

    /**
     * This function will be invoked when a http request is received. The message contents are provided as output to this function.
     */
    public static String queueOut(HttpRequestMessage request, ExecutionContext context) {
        context.getLogger().info("QueueOut process message"+request.getBody().toString());
        return request.getBody().toString();
    }
    
}