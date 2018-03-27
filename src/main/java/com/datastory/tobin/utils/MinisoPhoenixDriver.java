package com.datastory.tobin.utils;

import com.datastory.tobin.conf.CoreConsts;
import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MinisoPhoenixDriver
 *
 * @author tobin
 * @since 2018-02-04
 */
public class MinisoPhoenixDriver {

    private static final Logger LOG = Logger.getLogger(MinisoPhoenixDriver.class);

    private String connUri = null;
    private Connection conn = null;
    private Statement stmt = null;

    public MinisoPhoenixDriver(String zkQuorum, int zkPort, String hbaseZkPath, Properties properties)
            throws ClassNotFoundException, SQLException {

        Class.forName(CoreConsts.PHOENIX_DRIVER_NAME);

        // path:  /hbase-unsecure
        // zk: localhost:2181
        connUri = String.format(CoreConsts.PHOENIX_URI_TEMPLATE, zkQuorum, zkPort, hbaseZkPath);
        LOG.info("phoenix uri: " + connUri);

        if (properties != null) {
            conn = DriverManager.getConnection(connUri, properties);
        } else {
            conn = DriverManager.getConnection(connUri);
        }
        conn.setAutoCommit(true);
        stmt = conn.createStatement();
    }

    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOG.error("close statement error, " + e, e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error("close connection error, " + e, e);
            }
        }
    }

    /**
     * execute sql
     *
     * @param sql
     * @return boolean
     */
    public boolean execute(String sql) {
        boolean flag = false;

        try {
            flag = stmt.execute(sql);
        } catch (SQLException e) {
            LOG.error("execute error: " + sql, e);
        }

        return flag;
    }

    /**
     * query sql
     *
     * @param sql
     * @return result set
     */
    public ResultSet query(String sql) {
        ResultSet result = null;
        try {
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            LOG.error("query error: " + sql, e);
        }
        return result;
    }

    /**
     * 根据一行记录构建一个map并返回
     *
     * @param resultSet
     * @return
     */
    public static Map<String, String> buildRs2Map(ResultSet resultSet){
        Map<String, String> fieldValueMap = new HashMap<String, String>();
        try {
            int count = resultSet.getMetaData().getColumnCount();
            int fieldIndex = 0;
            while (fieldIndex++ < count) {
                //获取字段名
                String columnLabel = resultSet.getMetaData().getColumnName(fieldIndex);
                //获取字段值
                String val = resultSet.getString(columnLabel);
                //设置到Map结构中去
                fieldValueMap.put(columnLabel, val);
            }
        } catch (SQLException e) {
            LOG.error("parse ResultSet error", e);
        }
        return fieldValueMap;
    }

    public static MinisoPhoenixDriver getNewInstance(Configuration conf) {

        MinisoPhoenixDriver instance = null;
        try {
            instance = new MinisoPhoenixDriver(conf.get(CoreConsts.HBASE_ZOOKEEPER_QUORUM),
                    2181, "/hbase-unsecure", null);
        } catch (ClassNotFoundException e) {
            LOG.error("phoenix class error, " + e, e);
        } catch (SQLException e) {
            LOG.error("get connection error, " + e, e);
        }

        return instance;
    }
}

