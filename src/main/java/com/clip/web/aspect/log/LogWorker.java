package com.clip.web.aspect.log;

import com.clip.web.dao.common.OpLogDao;
import com.clip.web.model.common.OpLog;
import com.clip.web.utils.ClassBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogWorker implements Runnable {

    private static Log logger = LogFactory.getLog("LogWorker");

    private OpLog log;

    public LogWorker(OpLog log) {
        this.log = log;
    }

    public void run() {
        try {
            OpLogDao dao = (OpLogDao) ClassBeanFactory.getBean("opLogDao");
            dao.addLog(log);
        } catch (Exception ignored) {
            logger.error(ignored);
        }
//        logger.info("log write ok");
    }

}
