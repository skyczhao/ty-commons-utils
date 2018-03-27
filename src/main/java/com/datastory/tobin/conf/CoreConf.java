package com.datastory.tobin.conf;

import org.apache.hadoop.conf.Configuration;

/**
 * CoreConf
 * @author songhao
 * @since 2018-02-08
 */
public class CoreConf extends Configuration {

    private volatile static CoreConf config;

    public static CoreConf getInstance() {
        if (null == config) {
            synchronized (CoreConf.class) {
                if (null == config) {
                    config = new CoreConf();
                }
            }
        }
        return config;
    }

    private CoreConf() {
        this.addResource("miniso-default.xml"); // 加载默认配置
        this.addResource("miniso-config.xml");  // 加载自定义配置, 覆盖默认配置
    }
}
