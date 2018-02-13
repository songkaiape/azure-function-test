package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;

public class BlobTests {

    public static void blobTriggerTest(byte[] content, @BindingName("name") String filename, ExecutionContext context) {
        context.getLogger().info(
                "blob trigger function processed blob \n Name: " + filename + "\n Blob size:" + content.length + "Bytes");
    }

    public static void blobMetaData(byte[] content, @BindingName("blobname") String filename,
            @BindingName("blobextension") String ext, ExecutionContext context) {
        context.getLogger().info(
                "Received " + content.length + " bytes for blob [Name: " + filename + "] [Extension: " + ext + "]");
    }    

    public static String blobOut(byte[] data, @BindingName("output") OutputBinding<byte[]> output) {
        output.setValue(data);
        return data.length + " bytes";
    }

    
    public static void blobIn(ExecutionContext context, @BindingName("name") String myQueueItem,
            @BindingName("myInputBlob") OutputBinding<String> myInputBlob,
            @BindingName("myOutputBlob") OutputBinding<String> myOutputBlob) {
    
        context.getLogger().info("Java Queue trigger function processed:" + myQueueItem);
        myOutputBlob.setValue(myInputBlob.toString());
    }
    
}