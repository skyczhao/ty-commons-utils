#!/bin/bash

nohup sh run.sh WeiboQuartz >/dev/null 2>&1 &
nohup sh run.sh NewsAndForumQuartz -catId 2 >/dev/null 2>&1 &
nohup sh run.sh NewsAndForumQuartz -catId 3 >/dev/null 2>&1 &

nohup sh run.sh ShoppingCrawlerQuartz -api_type 1 >/dev/null 2>&1 &
nohup sh run.sh ShoppingCrawlerQuartz -api_type 3 >/dev/null 2>&1 &
nohup sh run.sh ShoppingCrawlerQuartz -api_type 5 >/dev/null 2>&1 &

nohup sh run.sh CommentCrawlerQuartz -api_type 4 >/dev/null 2>&1 &
nohup sh run.sh CommentCrawlerQuartz -api_type 6 >/dev/null 2>&1 &
nohup sh run.sh CommentCrawlerQuartz -api_type 7 >/dev/null 2>&1 &

nohup sh run.sh MinisoCrawlerAnalysisCli >/dev/null 2>&1 &