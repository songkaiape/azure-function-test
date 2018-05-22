package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

/**
 * Azure Functions with Azure EventHubs trigger.
 */
public class EventHubTests {

    /**
     * This function will be invoked when a new message is received at the specified eventhubs. The message contents are provided as input to this function.
     */
    public static void eventHubTriggerTest(@BindingName("myMessage") String message, ExecutionContext context) {
        context.getLogger().info(
                "Event Hub trigger function processed a message: " + message);
    }


    /**
     * This function will be invoked when a new http request is received at the http://localhost:7071/api/EventHubOutJava. 
     * A new message will be added to the specified EventHub queue.
     */
    public static String eventHubOut(byte[] data, @BindingName("outputMessage") OutputBinding<String> output, ExecutionContext context) {
        output.setValue(new String(data));
        context.getLogger().info(
                "Event Hub trigger function processed a message: " + new String(data));
        return "created message"+new String(data);
    }


    
}