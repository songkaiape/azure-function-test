package com.function;

import java.time.*;
import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;

/**
 * Azure Functions with Timer trigger.
 */
public class TimerTriggerJava {
    /**
     * This function will be invoked periodically according to the specified schedule.
     */
    @FunctionName("TimerTriggerJava")
    public void timerHandler(@TimerTrigger(name = "timerInfo", schedule = "0 2 * * * *") String timerInfo,
            final ExecutionContext context) {
        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());
    }
}
