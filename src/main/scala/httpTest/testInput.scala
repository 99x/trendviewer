package httpTest

import akka.pattern
import akka.util.Timeout
import scala.concurrent.Await
import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem
import akka.pattern.ask
import scala.concurrent.duration

object testInput {
  case object Count;
  class ScheduleActor extends Actor {
    var n = 0
    def receive = {
      case Count =>
        n += 1
        println(n)
    }
  }

  val system = ActorSystem("TestSystem")
  val actor = system.actorOf(Props[ScheduleActor], "Actor")

  actor ! Count
}