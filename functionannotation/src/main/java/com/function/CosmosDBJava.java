package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

public class CosmosDBJava {
    /**
    * This function will be invoked when a message add to the queue.The documentDB use as input to get the item with the name of the message
    */
    @FunctionName("CosmosDBInJava")
    public void cosmosDBIn(
            @QueueTrigger(name = "message", queueName = "cosmosdbqueue", connection = "AzureWebJobsStorage") String message,
            @DocumentDBInput(name = "documents", databaseName = "Tasks", collectionName = "test1", 
            connection = "AzureWebJobsCosmosDB",dataType="string",id="{queueTrigger}") String document,
            final ExecutionContext context) {
        context.getLogger().info("Java Queue trigger receive a message" + message);
        context.getLogger().info("Java CosmosDB trigger function processed a document content: " + document);
    }

    /**
    * This function will be invoked when a post request with file to http://localhost:7071/api/CosmosDBOutJava. A new document will add to the output documentdb
    */
    @FunctionName("CosmosDBOutJava")
    public String cosmosDBOutOut(
            @DocumentDBOutput(name = "documents", databaseName = "Tasks", collectionName = "test1", connection = "AzureWebJobsCosmosDB") OutputBinding<String> documents,
            @HttpTrigger(name = "req", methods = { "get",
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        String httpbodystring = request.getBody().orElse("testbodystring");
        HashMap<String, String> testmap = new HashMap<String, String>();
        testmap.put(httpbodystring, "testvalue");

        context.getLogger().info("Java cosmos trigger function processs a document: " +testmap.toString());

        documents.setValue(testmap.toString());
        return testmap.toString();
    }
}
