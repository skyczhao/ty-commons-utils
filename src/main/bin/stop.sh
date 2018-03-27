#!/bin/bash

clazz='com.datastory.miniso.cli.quartz.ShoppingCrawlerQuartz|com.datastory.miniso.cli.quartz.CommentCrawlerQuartz|com.datastory.miniso.cli.quartz.WeiboQuartz|com.datastory.miniso.cli.quartz.NewsAndForumQuartz|com.datastory.miniso.cli.MinisoCrawlerAnalysisCli'

for i in `jps -lm | grep -E "$clazz" | awk '{print $1}'`
do
    echo "killing pid  "${i} ;
    kill ${i}
done
