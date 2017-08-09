name := "SparkStreaming"
scalaVersion := "2.11.8"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.10
libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.0"
// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming_2.10
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.0"
// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka_2.10
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.0"
libraryDependencies += "com.googlecode.json-simple" % "json-simple" % "1.1.1"
libraryDependencies += "org.apache.spark" % "spark-streaming-flume_2.10" % "1.6.0"
libraryDependencies += "org.apache.spark" % "spark-graphx_2.10" % "1.6.0"
// https://mvnrepository.com/artifact/org.apache.hbase/hbase-client
libraryDependencies += "org.jfree" % "jfreechart" % "1.0.19"
// https://mvnrepository.com/artifact/org.apache.hbase/hbase-client
libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.2.0"
libraryDependencies += "org.apache.hbase" % "hbase" % "1.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.2.0"
libraryDependencies += "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.6.0"
// https://mvnrepository.com/artifact/org.twitter4j/twitter4j-stream
//libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.6"
//libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.6"
// https://mvnrepository.com/artifact/com.restfb/restfb
libraryDependencies += "com.restfb" % "restfb" % "1.40.0"
// https://mvnrepository.com/artifact/com.cloudera.sparkts/sparkts
libraryDependencies += "com.cloudera.sparkts" % "sparkts" % "0.4.1"
// https://mvnrepository.com/artifact/org.scalaj/scalaj-http_2.10
libraryDependencies += "org.scalaj" % "scalaj-http_2.10" % "2.3.0"
// https://mvnrepository.com/artifact/org.json/json
libraryDependencies += "org.json" % "json" % "20160810"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor_2.10
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.3.14"






assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}
