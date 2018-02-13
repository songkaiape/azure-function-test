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

    public static String cosmosDBOut(byte[] data, @BindingName("testdocuments") OutputBinding<String> documents,
            ExecutionContext context) {
        context.getLogger().info("Http process message" + new String(data));
        LinkedHashMap<String, String> outdata = new LinkedHashMap<String, String>();

        String doc = new String(data);
        outdata.put("id", doc);
      //documents.setValue(outdata.toString());
        documents.setValue("{\"id\":\"testEEEE\"}");
        return doc;

    }

    public static void cosmosDBIn(@BindingName("inputdocument") String documents,
            @BindingName("myQueue") String myQueueItem, ExecutionContext context) {
        context.getLogger().info(myQueueItem);
        context.getLogger().info("Documents updated:  " + documents);
    }
}
