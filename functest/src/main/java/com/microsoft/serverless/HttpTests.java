package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.HttpRequestMessage;
import com.microsoft.azure.serverless.functions.HttpResponseMessage;
import com.microsoft.azure.serverless.functions.annotation.BindingName;

import java.util.Optional;

public class HttpTests {

    public static HttpResponseMessage<String> httpTriggerJava(ExecutionContext context, 
            HttpRequestMessage<Optional<String>> request) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);

        if (name == null) {
            name="Stranger";
        }
        return request.createResponse(200, "Hello, " + name);
        
    }

    public static String httpTriggerJava(ExecutionContext context, @BindingName("name") String name){
        if (name == null) {
            name="Stranger";
        }
        return "Hello, " + name;
    }

}