package twitter

import scala.collection.mutable.ArrayBuffer
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.twitter.TwitterUtils
import scalaj.http._
import org.json4s.jackson.Json
import java.net.URLEncoder

object PopularTags {
  val apikey: String = "TgmGQMf66t0OlnZZsMezFb9Wqb7VgQ3IjSAZDGrFdME"
  var keywords = ArrayBuffer[String]()

  def main(args: Array[String]): Unit = {
    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
    subMain()
  }

  def subMain() {
    val statement = readLine("Enter Your Statment : ")
    val windowTime = readLine("Enter Your Window Time : ")
    var sentToAPI = false;
    if (statement.contains(" ")) sentToAPI = true

    streamingPart(statement, sentToAPI)
  }

  def streamingPart(statement: String, contex: Boolean) {
    println(statement)
    if (statement.isEmpty()) {
      println("Input a Statment")
      subMain()
    }
    if (contex) {
      sentToKeywordAPI(statement)
    } else {
      keywords += statement
    }
    val sparkConf = new SparkConf().setAppName("PopularTagsTwitter").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val stream = TwitterUtils.createStream(ssc, None, keywords)

    val sentence = stream.map(status => status.getText())
    sentence.foreachRDD(rdd => rdd.foreach(sentToEmotionAndSentimentAPI))
    ssc.start()
    ssc.awaitTermination()
  }

  def sentToKeywordAPI(statement: String) = {
    val response = Http("https://apis.paralleldots.com/keywords").postForm
      .param("q", statement)
      .param("apikey", apikey)
      .option(HttpOptions.connTimeout(10000))
      .option(HttpOptions.readTimeout(50000))
      .asString

    dataOrganization(response.body, "keyword")
  }

  def sentToEmotionAndSentimentAPI(rdd: Any) = {
    println(rdd)
    val statement: String = rdd.toString()
    val emotionResponse = Http("https://apis.paralleldots.com/emotion").postForm
      .param("text", statement)
      .param("apikey", apikey)
      .option(HttpOptions.connTimeout(10000))
      .option(HttpOptions.readTimeout(50000))
      .asString

    val url = URLEncoder.encode(statement, "ASCII").replace("+", "%20")
    val sentimentResopnse = Http("https://apis.paralleldots.com/sentiment?sentence1=" + url + "&apikey=" + apikey)
      .option(HttpOptions.connTimeout(10000))
      .option(HttpOptions.readTimeout(50000))
      .asString

    val data = emotionResponse.body + "" + sentimentResopnse.body
    println(dataOrganization(data, "sentiEmo"))
  }

  def dataOrganization(data: String, contex: String) = {
    if (contex == "keyword") {
      if (data == "[]") {
        println("No keyword Found !!!")
        subMain()
      }
      val words = data.split("\"")
      var index: Int = 1
      while (index < words.length) {
        keywords += words(index)
        index = index + 2
      }
    } else if (contex == "sentiEmo") {
      // you have to develop and algorithm for manipulate the data
      data
    } else {
      println("Invalid Contex, Internal Error")
    }
  }

  // define it  
  def writeToHDFS() {

  }
  // define it
  def readFromHDFS() {

  }
}



