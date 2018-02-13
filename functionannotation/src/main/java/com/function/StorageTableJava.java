package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import java.util.*;

/**
 * Azure Functions with Azure Storage table.
 */
public class StorageTableJava {
    /**
     * This function will be invoked when a new http request is received at the specified path. 
     */
    @FunctionName("TableOutputJava")
    public String tableOutputJava(
            @HttpTrigger(name = "req", methods = {
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @TableOutput(name = "tableBinding", tableName = "Person", connection = "AzureWebJobsStorage") OutputBinding<Person> tableout,
            final ExecutionContext context) {
        String httpbodystring = request.getBody().orElse("teststring");
        tableout.setValue(new Person(httpbodystring + "PartitionKey", httpbodystring + "Rowkey", httpbodystring));
        context.getLogger().info("write new peron object to table name: " + httpbodystring);
        return httpbodystring;

    }

    /**
     * This function will be invoked when a new queue message is received. 
     */

    @FunctionName("TableInputJava")
    public void tableInputJava(
            @QueueTrigger(name = "message", queueName = "tablequeue", connection = "AzureWebJobsStorage") String message,
            @TableInput(name = "tableBinding", tableName = "Person", rowKey = "{queueTrigger}", partitionKey = "Test", connection = "AzureWebJobsStorage") String tablein,
            final ExecutionContext context) {
        context.getLogger().info("Get a new queue message: " + message);
        context.getLogger().info("Get table person name:" + tablein);

    }

    public static class Person {
        public String PartitionKey;
        public String RowKey;
        public String Name;

        public Person(String p, String r, String n) {
            this.PartitionKey = p;
            this.RowKey = r;
            this.Name = n;
        }
    }
}
