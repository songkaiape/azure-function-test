package com.microsoft.serverless;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.BindingName;
import com.microsoft.azure.serverless.functions.OutputBinding;
 /**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTests {

   

    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    public static void blobTriggerTest(byte[] content, @BindingName("name") String filename, ExecutionContext context) {
        context.getLogger().info(
                "blob trigger function processed blob \n Name: " + filename + "\n Blob size:" + content.length + "Bytes");
    }

    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    public static void blobMetaData(byte[] content, @BindingName("blobname") String filename,
            @BindingName("blobextension") String ext, ExecutionContext context) {
        context.getLogger().info(
                "Received " + content.length + " bytes for blob [Name: " + filename + "] [Extension: " + ext + "]");
    }    

    /**
    * This function will be invoked when a post request with file to http://localhost:7071/api/BlobOutJava The file will be save as test.txt.
    */
    public static String blobOut(byte[] data, @BindingName("output") OutputBinding<byte[]> output) {
        output.setValue(data);
        return data.length + " bytes";
    }

    /**
     * This function will be invoked when a message add to queue. And the message is the file name to make a copy.
     * Make sure the file exist or you will get an error
     */
    public static void blobIn(ExecutionContext context, @BindingName("name") String myQueueItem,
            @BindingName("myInputBlob") OutputBinding<String> myInputBlob,
            @BindingName("myOutputBlob") OutputBinding<String> myOutputBlob) {
    
        context.getLogger().info("Java Queue trigger function processed:" + myQueueItem);
        myOutputBlob.setValue(myInputBlob.toString());
    }
    
}