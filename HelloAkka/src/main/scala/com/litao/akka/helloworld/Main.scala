package com.litao.akka.helloworld

import akka.actor._

object Main {
  def main(args: Array[String]) {
    val system = ActorSystem("Hello")
    val actor = system.actorOf(Props[HelloWorld], "hellWorld")
    system.actorOf(Props(classOf[Terminator], actor), "terminator")
  }
}

class Terminator(ref: ActorRef) extends Actor with ActorLogging {
  context watch ref

  override def receive: Receive = {
    case Terminated(_) =>
      log.info("{} has terminaterd, shutting down system", ref.path)
      context.system.shutdown()
  }
}