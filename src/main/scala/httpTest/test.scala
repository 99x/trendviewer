package httpTest

import scalaj.http._
import org.json4s.jackson.Json



object test {
  def main(args: Array[String]): Unit = {
    val word : String = "Mother Love is best"
    
    val apikey : String = "TgmGQMf66t0OlnZZsMezFb9Wqb7VgQ3IjSAZDGrFdME"
//    val response = Http("https://apis.paralleldots.com/keywords").postForm
//                    .param("q", word)
//                    .param("apikey", apikey)
//                    .asString
    
    
    val response = Http("https://apis.paralleldots.com/keywords").postForm
                   .param("q",word)
                   .param("apikey", apikey)
                   .asString
                   
                   
    println(response) 
    
 
  }
}