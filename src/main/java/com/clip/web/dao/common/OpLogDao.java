package com.clip.web.dao.common;

import com.clip.core.base.dao.BaseSpringJdbcDao;
import com.clip.web.model.common.OpLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class OpLogDao extends BaseSpringJdbcDao {

    private static Log logger = LogFactory.getLog("OpLogDao");

    public OpLog addLog(final OpLog log) {
        try {
            jdbcTemplate.update("Insert into op_log(target_service,target_obj,action,detail,user,happen_time,result,method) values (?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, log.getTargetService());
                    preparedStatement.setString(2, log.getTargetObj());
                    preparedStatement.setString(3, log.getAction());
                    preparedStatement.setString(4, log.getDetail());
                    preparedStatement.setString(7, log.getResult());
                    preparedStatement.setString(5, log.getUser());
                    preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                    preparedStatement.setString(8, log.getMethod());
                }
            });
        } catch (Exception e) {
            logger.error(e);
        }
//        logger.info("log dao write ok:" + System.currentTimeMillis());
        return log;
    }

}
