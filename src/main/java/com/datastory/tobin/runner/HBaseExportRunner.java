package com.datastory.tobin.runner;

import com.datastory.tobin.conf.CoreConf;
import com.datastory.tobin.utils.MinisoPhoenixDriver;
import com.google.gson.Gson;
import com.yeezhao.commons.util.encypt.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * HBaseExportRunner
 * @author tobin
 * @since 2018-02-06
 */
public class HBaseExportRunner {

    private static final Logger LOG = Logger.getLogger(HBaseExportRunner.class);
    private static final Gson GSON = new Gson();

    private String crawlerJobId = null;
    private String table = null;
    private String fields = null;
    private MinisoPhoenixDriver phoenixDriver = null;

    public boolean start(Map<String, String> args) {

        boolean flag = false;
        try {
            // 1. 初始化
            setup(args);

            // 2. 实际操作
            flag = run(args);

        } catch (Exception e) {
            LOG.error(HBaseExportRunner.class.getSimpleName() + " runtime error:" + e, e);
        } finally {
            // 3. 清理回收空间
            try {
                cleanup(args);
            } catch (Exception e) {
                LOG.error(HBaseExportRunner.class.getSimpleName() + " cleanup error " + e, e);
            }
        }
        return flag;
    }

    private void setup(Map<String, String> args) {
        if (args.containsKey("crawlerJobId")) {
            crawlerJobId = args.get("crawlerJobId");
        }
        if (args.containsKey("table")) {
            table = args.get("table");
        }
        if (args.containsKey("fields")) {
            fields = args.get("fields");
        }

        phoenixDriver = MinisoPhoenixDriver.getNewInstance(CoreConf.getInstance());
    }

    private boolean run(Map<String, String> args) {

        if (StringUtils.isEmpty(crawlerJobId)) {
            LOG.error("empty crawlerJobId");
            return false;
        }
        if (StringUtils.isEmpty(table)) {
            LOG.error("empty table");
            return false;
        }

        String sql = String.format("select * " +
                        "from \"%s\" " +
                        "where \"pk\" like '%s%%' ",// +
//                        "order by \"review_count\" desc " +
//                        "limit 50",
                table,
                Md5Util.md5(crawlerJobId).substring(0, 3) + "%|" + crawlerJobId);
        LOG.info("sql: " + sql);
        ResultSet result = phoenixDriver.query(sql);

        try {
            if (StringUtils.isEmpty(fields)) {
                while (result.next()) {
                    Map<String, String> line = MinisoPhoenixDriver.buildRs2Map(result);
                    System.out.println(GSON.toJson(line));
                }
            } else {
                String[] parts = fields.split(",");
                while (result.next()) {
                    Map<String, String> line = MinisoPhoenixDriver.buildRs2Map(result);
                    StringBuilder sb = new StringBuilder();
                    for (String part : parts) {
                        if (line.containsKey(part)) {
                            sb.append(line.get(part));
                        }
                        sb.append("\t");
                    }
                    System.out.println(sb.toString());
                }
            }
        } catch (SQLException e) {
            LOG.error("parse line error", e);
        }
        return false;
    }

    private void cleanup(Map<String, String> args) {
        if (phoenixDriver != null) {
            phoenixDriver.close();
        }
    }
}
