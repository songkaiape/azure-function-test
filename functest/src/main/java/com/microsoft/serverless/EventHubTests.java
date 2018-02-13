package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

public class EventHubTests {

    public static void eventHubTriggerTest(@BindingName("myMessage") String message, ExecutionContext context) {
        context.getLogger().info(
                "Event Hub trigger function processed a message: " + message);
    }


    public static String eventHubOut(byte[] data, @BindingName("outputMessage") OutputBinding<String> output, ExecutionContext context) {
        output.setValue(new String(data));
        context.getLogger().info(
                "Event Hub trigger function processed a message: " + new String(data));
        return "created message"+new String(data);
    }


    
}