package com.clip.web.aspect.log;

import com.clip.web.model.common.OpLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogSession {

    private static Log logger = LogFactory.getLog("LogSession");

    private static ExecutorService executor = Executors.newScheduledThreadPool(10);

    public static void submit(OpLog log) {
        LogWorker worker = new LogWorker(log);
        executor.submit(worker);
//        logger.info("submit log write");
    }

}
