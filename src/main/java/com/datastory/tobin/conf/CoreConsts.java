package com.datastory.tobin.conf;

/**
 * CoreConsts
 * @author songhao
 * @since 2018-02-08
 */
public class CoreConsts {

    //------------------------start:与spark相关的-------------------------------------------
    public static final String SPARK_YARN_QUEUE = "spark.yarn.queue";
    public static final String SPARK_DRIVER_MEMORY = "spark.driver.memory";
    public static final String SPARK_EXECUTOR_MEMORY = "spark.executor.memory";

    public static final String SPARK_CORES_MAX = "spark.cores.max";
    public static final String SPARK_NUM_EXECUTOR = "spark.num.runner";
    public static final String SPARK_EXECUTOR_CORE = "spark.runner.core";

    public static final String SPARK_AKKA_TIMEOUT = "spark.akka.timeout";
    public static final String SPARK_DEFAULT_PARALLELISM = "spark.default.parallelism";
    public static final String SPARK_SPECULATION_MULTIPLIER = "spark.speculation.multiplier";

    public static final String SPARK_DEFAULT_USER = "spark.default.user";
    public static final String SPARK_YARN_JAR = "spark.yarn.jar";


    //------------------------start:与接口相关的------------------------------------------
    public static final String MINISO_TASK_STATUS_REQUEST_URL = "/taskset/status?taskset_id=%s";//任务状态请求的url
    public static final String PARAM_MINISO_RHINO_URL = "app.openapi.rhino.url";

    //------------------------start:与es的配置相关的------------------------------------------
    public static final String ES_MINISO_COMMENT_INDEX = "miniso_comment_es_index";
    public static final String ES_MINISO_COMMENT_TYPE = "miniso_comment_es_type";

    public static final String ES_MINISO_ITEM_INDEX = "miniso_item_es_index";
    public static final String ES_MINISO_ITEM_TYPE = "miniso_item_es_type";

    public static final String ES_MINISO_SALEINFO_TYPE = "miniso_sale_es_type";
    public static final String ES_MINISO_COMMON_TYPE = "miniso_common_es_type";

    public static final String ES_MINISO_CLUSTER = "es.miniso.cluster";
    public static final String ES_MINISO_CLUSTER_HOSTS = "es.miniso.cluster.hosts";

    //------------------------start:与算法组的情感算法相关------------------------------------------
    public static final String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "hbase.zookeeper.property.clientPort";
    public static final String ZOOKEEPER_SESSION_TIMEOUT = "zookeeper.session.timeout";

    //------------------------start:phoenix driver name-----------------------------------------
    public static final String PHOENIX_URI_TEMPLATE = "jdbc:phoenix:%s:%s:%s";
    public static final String PHOENIX_DRIVER_NAME = "org.apache.phoenix.jdbc.PhoenixDriver";
    public static final String HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";

    //------------------------start:数据库的表配置-------------------------------------------
    public static final String PARAM_MYSQL_MINISO = "mysql.miniso.url";

    public static final String SQL_MINISO_BRAND = "t_miniso_brand";
    public static final String SQL_MINISO_CATEGORY = "t_miniso_category";
    public static final String SQL_MINISO_CATEGORY_BRAND = "t_miniso_category_brand";
    public static final String SQL_MINISO_CRAWLER = "t_miniso_crawler";
    public static final String SQL_MINISO_CRAWLER_JOB = "t_miniso_crawler_job";
    public static final String SQL_MINISO_QUATZ_CONF = "t_miniso_quatz_conf";
    public static final String SQL_MINISO_ECOMMERCE_ITEM = "t_miniso_ecommerce_item";
    public static final String SQL_MINISO_DATAMONITOR = "t_miniso_data_monitor";

    public static final String SQL_BDE_TASK = "t_bde_task";
    public static final String SQL_RADAR_TOOLS_TABLE = "t_radar_tools";
    public static final String SQL_BDE_TRADE = "t_bde_trade";
    public static final String SQL_BDE_WEIBO_TOPIC= "t_bde_weibo_topic";
    public static final String SQL_BDE_TRIPE_TOPIC = "t_bde_tripe_topic";
    public static final String SQL_BDE_TASK_RELEATED = "t_bde_task_releated";
    public static final String SQL_BDE_BRAND_TOPIC = "t_bde_brand_topic";

    public static final String SQL_RADAR_SPOKESMAN_CONFIGURATION_TABLE = "t_radar_spokesman_configuration";
    public static final String SQL_RADAR_VARIETY_CONFIGURATION_TABLE = "t_radar_variety_configuration";
    public static final String SQL_RADAR_ECOMM_CONFIGURATION_TABLE = "t_radar_ecomm_configuration";
    public static final String SQL_RADAR_BRAND_CONFIGURATION_TABLE = "t_radar_brand_configuration";
}
