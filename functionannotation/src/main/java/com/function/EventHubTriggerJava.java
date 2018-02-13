package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

/**
 * Azure Functions with Azure EventHubs trigger.
 */
public class EventHubTriggerJava {
    /**
     * This function will be invoked when a new message is received at the specified eventhubs. The message contents are provided as input to this function.
     */
    @FunctionName("EventHubTriggerJava")
    public void eventhubTrigger(
            @EventHubTrigger(name = "eventhubs", eventHubName = "barneytesthub1", connection = "AzureEventHubConnection") String eventhbMessage,
            final ExecutionContext context) {
        context.getLogger().info("Java Eventhub Queue trigger function processed a message: " + eventhbMessage);
    }

    /**
     * This function will be invoked when a new http request is received at the http://localhost:7071/api/EventHubOutJava. 
     * A new message will be added to the specified EventHub queue.
     */
    @FunctionName("EventHubOutJava")
    public void eventHubOut(
            @HttpTrigger(name = "req", methods = { "get",
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @EventHubOutput(name = "eventhubs", eventHubName = "barneytesthub1", connection = "AzureEventHubConnection") OutputBinding<String> eventhbout,
            final ExecutionContext context) {

        String output = request.getBody().orElse("teststring");
        context.getLogger()
                .info("Java Eventhub Queue trigger function processed a message: " + output);
        eventhbout.setValue(output);
    }
}
