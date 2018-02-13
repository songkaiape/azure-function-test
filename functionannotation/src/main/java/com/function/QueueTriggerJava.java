package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

/**
 * Azure Functions with Azure Storage Queue trigger.
 */
public class QueueTriggerJava {
    /**
     * This function will be invoked when a new message is received at the specified path. The message contents are provided as input to this function.
     */
    @FunctionName("QueueTriggerJava")
    public void queueHandler(
            @QueueTrigger(name = "message", queueName = "triggerqueue", connection = "AzureWebJobsStorage") String message,
            final ExecutionContext context) {
        context.getLogger().info("Java Queue trigger function processed a message: " + message);
    }

    /**
     * This function will be invoked when a http request is received. The message contents are provided as output to this function.
     */
    @FunctionName("QueueOutJava")
    public String queueOut(
            @HttpTrigger(name = "req", methods = { "get",
                    "post" }, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @QueueOutput(name = "message", queueName = "outqueue", connection = "AzureWebJobsStorage") OutputBinding<String> message,
            final ExecutionContext context) {
        String output = request.getBody().orElse("teststring");
        context.getLogger().info("Java Queue trigger function processed a message: " + output);
        message.setValue(output);
        return output;
    }
}
