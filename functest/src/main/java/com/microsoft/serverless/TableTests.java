package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

/**
 * Azure Functions with Azure Storage table.
 */
public class TableTests {

    /**
     * This function will be invoked when a new http request is received at the specified path. 
     */
    public static String tableOut(byte[] data, @BindingName("tableBinding") OutputBinding<Person> output,
            ExecutionContext context) {
        String data_s = new String(data);
        context.getLogger().info("Name in Person is :" + data_s);
        output.setValue(new Person("testP", data_s + "RowKey", data_s));
        return data.length + " bytes";
    }

    /**
     * This function will be invoked when a new queue message is received. 
     */
    public static void tableIn(@BindingName("tableBinding")String personEntity,@BindingName("myQueueItem") String myQueueItem, ExecutionContext context ) {

        context.getLogger().info("Java Queue trigger function processed:" + myQueueItem);
        context.getLogger().info("Name in Person is :" + personEntity);
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