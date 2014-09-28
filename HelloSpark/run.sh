#!/bin/bash

sbt package

SPARK_HOME="/Users/litao_buptsse/Develop/code/workspace/spark/demo/spark-1.1.1-SNAPSHOT-bin-2.3.0-cdh5.0.0"
$SPARK_HOME/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  target/scala-2.10/hellospark_2.10-1.0.jar
