package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

/**
 * Azure Functions with Azure ServiceBus topic&&Queue trigger.
 */
public class ServiceBusTriggerJava {

    @FunctionName("ServiceBusTriggerJava")
    public void serviceBusTrigger(
            @ServiceBusQueueTrigger(name = "mySbMsg", queueName = "mysbqueue", connection = "AzureServiceBus") String message,
            final ExecutionContext context) {
        context.getLogger().info("Java ServiceBus Queue trigger function processed a message: " + message);
    }

    @FunctionName("ServiceBusOutJava")
    public String serviceBusOut(
            @HttpTrigger(name = "req", methods = { "get",
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @ServiceBusQueueOutput(name = "message", queueName = "mysboutqueue", connection = "AzureServiceBus") OutputBinding<String> message,
            final ExecutionContext context) {

        String output = request.getBody().orElse("testservicebus");
        message.setValue(output);
        context.getLogger().info("Java Http trigger function processed a message: " + output);
        return output;

    }

  
}
