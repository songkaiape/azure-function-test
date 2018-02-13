package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

/**
 * Azure Functions with Azure ServiceBus topic trigger.
 */
public class ServiceBusTopic {
    
    @FunctionName("ServiceBusTopicTriggerJava")
    public void serviceBusTopicTrigger(
            @ServiceBusTopicTrigger(name = "mySbMsg", topicName = "mytopicqueue", subscriptionName="mysubscription",connection = "AzureServiceBus") String message,
            final ExecutionContext context) {
        context.getLogger().info("Java ServiceBus Topic trigger function processed a message: " + message);
    }

  
    @FunctionName("ServiceBusTopicOutJava")
    public String serviceBusTopicOut(
            @HttpTrigger(name = "req", methods = { "get",
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @ServiceBusTopicOutput(name = "message", topicName = "mytopicoutqueue", subscriptionName="mysubscription",connection = "AzureServiceBus") OutputBinding<String> message,
            final ExecutionContext context) {

        String output = request.getBody().orElse("testservicebus");
        message.setValue(output);
        context.getLogger().info("Java Http trigger function processed a message: " + output);
        return output;

    }
}
