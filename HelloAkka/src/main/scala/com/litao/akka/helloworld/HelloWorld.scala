package com.litao.akka.helloworld

import akka.actor.{Actor, Props}

/**
 * Created by Tao Li on 7/12/15.
 */
class HelloWorld extends Actor {

  override def preStart(): Unit = {
    val greeter = context.actorOf(Props[Greeter], "greeter")
    greeter ! Greeter.Greet
  }

  override def receive: Receive = {
    case Greeter.Done => context.stop(self)
  }
}
