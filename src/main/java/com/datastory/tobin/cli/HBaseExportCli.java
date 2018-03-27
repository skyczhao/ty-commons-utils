package com.datastory.tobin.cli;

import com.datastory.tobin.runner.HBaseExportRunner;
import com.yeezhao.commons.util.AdvCli;
import com.yeezhao.commons.util.CliRunner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * HBaseExportCli
 *
 * @author tobin
 * @since 2018-02-06
 */
public class HBaseExportCli implements CliRunner {

    private static Logger logger = Logger.getLogger(HBaseExportCli.class);

    private static final String CLI_PARAM_CRAWLER_JOB_ID = "crawlerJobId";
    private static final String CLI_PARAM_TABLE = "table";
    private static final String CLI_PARAM_FIELDS = "fields";

    public Options initOptions() {
        Options options = new Options();
        options.addOption(CLI_PARAM_CRAWLER_JOB_ID, true, "爬虫任务ID");
        options.addOption(CLI_PARAM_TABLE, true, "目标表");
        options.addOption(CLI_PARAM_FIELDS, true, "目标字段, 逗号分隔");
        return options;
    }

    public boolean validateOptions(CommandLine commandLine) {
        return commandLine.hasOption(CLI_PARAM_CRAWLER_JOB_ID) && commandLine.hasOption(CLI_PARAM_TABLE);
    }

    public void start(CommandLine commandLine) {
        String crawlerJobId = commandLine.getOptionValue(CLI_PARAM_CRAWLER_JOB_ID);
        String table = commandLine.getOptionValue(CLI_PARAM_TABLE);
        String fields = commandLine.getOptionValue(CLI_PARAM_FIELDS, "");

        Map<String, String> params = new HashMap<String, String>();
        params.put("crawlerJobId", crawlerJobId);
        params.put("table", table);
        params.put("fields", fields);
        new HBaseExportRunner().start(params);
    }

    public static void main(String[] args) {
        AdvCli.initRunner(args, HBaseExportCli.class.getSimpleName(), new HBaseExportCli());
    }
}
