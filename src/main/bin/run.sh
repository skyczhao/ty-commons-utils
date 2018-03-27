#!/bin/sh

print_usage ()
{
  echo "Usage: sh run.sh COMMAND"
  echo "where COMMAND is one of the follows:"
  echo "  HBaseExportCli                临时抽数"
  exit 1
}


if [ $# = 0 ] || [ $1 = "help" ]; then
  print_usage
fi

COMMAND=$1
shift

if [ "$JAVA_HOME" = "" ]; then
  echo "Error: JAVA_HOME is not set."
  exit 1
fi


JAVA=${JAVA_HOME}/bin/java
HEAP_OPTS="-Xms1024m -Xmx2048m"
PERM_OPTS="-XX:PermSize=1024M -XX:MaxPermSize=2048m"

JAR_NAME=`ls |grep jar|grep -v original-|grep dependencies`

CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar
CLASSPATH=${CLASSPATH}:conf
CLASSPATH=${CLASSPATH}:${JAR_NAME}
for f in lib/*.jar; do
  CLASSPATH=${CLASSPATH}:${f};
done

params=$@
echo "参数列表:${params}"
is_yarn_cluster=0
if [ "$COMMAND" = "ShoppingCrawlerQuartz" ]; then
    CLASS=
elif [ "$COMMAND" = "HBaseExportCli" ]; then
    CLASS=com.datastory.tobin.cli.HBaseExportCli
else
    CLASS=${COMMAND}
fi

# 设置hadoop用户，防止出现写hdfs时权限不足
export HADOOP_USER_NAME=dota

cmd=""
if [ "$is_yarn_cluster" = "1" ]; then
    cmd="spark-submit --master yarn-cluster --queue ds.radar --name miniso-${COMMAND} --class ${CLASS} ${JAR_NAME} ${params}"
    echo ${cmd}
    ${cmd}
else
    #"$JAVA" -Djava.io.tmpdir=${user.home}/logs/${project.artifactId} -Djava.awt.headless=true ${HEAP_OPTS} ${PERM_OPTS} -classpath "$CLASSPATH" ${CLASS} ${params}
    "$JAVA" -Dds.commons.logging.procName=${COMMAND} -Djava.io.tmpdir=${user.home}/logs/${project.artifactId} -Djava.awt.headless=true ${HEAP_OPTS} ${PERM_OPTS} -classpath "$CLASSPATH" ${CLASS} ${params}
fi
