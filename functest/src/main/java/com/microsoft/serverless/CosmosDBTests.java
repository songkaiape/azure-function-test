package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class CosmosDBTests {

    public static void cosmosDBTriggerTest(@BindingName("documents") ArrayList<LinkedHashMap> documents,
            ExecutionContext context) {
        context.getLogger().info("Documents modified:  " + documents.size());
        context.getLogger().info("First document Id:  " + documents.get(0).get("id"));

    }

    /**
    * This function will be invoked when a post request with file to http://localhost:7071/api/CosmosDBOutJava. A new document will add to the output documentdb
    */
    public static String cosmosDBOut(byte[] data, @BindingName("testdocuments") OutputBinding<String> documents,
            ExecutionContext context) {
        context.getLogger().info("Http process message" + new String(data));
        LinkedHashMap<String, String> outdata = new LinkedHashMap<String, String>();

        String doc = new String(data);
        outdata.put("id", doc);
        documents.setValue(outdata.toString());
        //documents.setValue("{\"id\":\"test\"}");
        return doc;

    }

    /**
    * This function will be invoked when a message add to the queue.The documentDB use as input to get the item with the name of the message
    */
    public static void cosmosDBIn(@BindingName("inputdocument") String documents,
            @BindingName("myQueue") String myQueueItem, ExecutionContext context) {
        context.getLogger().info(myQueueItem);
        context.getLogger().info("Documents updated:  " + documents);
    }
}
