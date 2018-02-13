package com.function;

import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerJava {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTriggerJava")
    @StorageAccount("AzureWebJobsStorage")
    public void blobHandler(
            @BlobTrigger(name = "content", path = "blobtrigger/{name}", dataType = "binary") byte[] content,
            @BindingName("name") String name, final ExecutionContext context) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: "
                + content.length + " Bytes");
    }

    /**
    * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents metatdata are provided as input to this function.
    */
    @FunctionName("BlobMetaDataJava")
    @StorageAccount("AzureWebJobsStorage")
    public void blobMetaData(
            @BlobTrigger(name = "content", path = "metadata/{blobname}.{blobextension}", dataType = "binary") byte[] content,
            @BindingName("blobname") String blobname, @BindingName("blobextension") String blobextension,
            final ExecutionContext context) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + blobname + "  Extension: "
                + blobextension + "\n  Size: " + content.length + " Bytes");
    }

    /**
    * This function will be invoked when a post request with file to http://localhost:7071/api/BlobOutJava The file will be save as test.txt.
    */
    @FunctionName("BlobOutJava")
    @StorageAccount("AzureWebJobsStorage")
    public String blobOutJava(
            @HttpTrigger(name = "req", methods = {
                    "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<byte[]> req,
            @BlobOutput(name = "output", path = "blobout/test.txt", dataType = "binary") OutputBinding<byte[]> output) {

        output.setValue(req.getBody());
        return req.getBody().length + "bytes";
    }

    /**
     * This function will be invoked when a message add to queue. And the message is the file name to make a copy.
     * Make sure the file exist or you will get an error
     */

    @FunctionName("BlobInJava")
    @StorageAccount("AzureWebJobsStorage")
    public void blobInJava(
            @QueueTrigger(name = "myQueueItem", queueName = "myqueue", connection = "AzureWebJobsStorage") String myQueueItem,
            @BlobInput(name = "input", path = "blobin/{queueTrigger}", dataType = "binary") byte[] myInputBlob,
            @BlobOutput(name = "output", path = "blobin/{queueTrigger}-Copy", dataType = "binary") OutputBinding<byte[]> myOutputBlob,
            final ExecutionContext context) {
        context.getLogger().info("Azure blob function is making a copy of " + myQueueItem);
        myOutputBlob.setValue(myInputBlob);
    }

}
