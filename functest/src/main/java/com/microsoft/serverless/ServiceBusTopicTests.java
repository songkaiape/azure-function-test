package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

public class ServiceBusTopicTests {

    public static void serviceBusTopicTriggerTest(@BindingName("mySbMsg") String message, ExecutionContext context) {
        context.getLogger().info(
                "Java Queue trigger function processed a message:  "+message);
    }

    public static String serviceBusTopicOut(byte[] data,@BindingName("outputSbQueue") OutputBinding<String> outputSbQueue, ExecutionContext context) {
        String doc_String=new String(data);
        context.getLogger().info("Http process message"+doc_String);
        outputSbQueue.setValue(doc_String);
        return doc_String;
    }

  
}