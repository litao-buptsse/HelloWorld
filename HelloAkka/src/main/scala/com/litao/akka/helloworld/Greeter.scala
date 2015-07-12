package com.litao.akka.helloworld

import akka.actor.Actor

/**
 * Created by Tao Li on 7/12/15.
 */
object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  override def receive: Receive = {
    case Greeter.Greet =>
      println("Hello World!")
      sender() ! Greeter.Done
  }
}
