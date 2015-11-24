package com.eric.akka.sample

import akka.actor.{Props, ActorSystem}

/**
 * Created by Eric on 2015/11/24.
 */
object StudentSimulatorApp extends App{
  val system=ActorSystem("StudentAndTeacherSample")

  val teacherActorRef=system.actorOf(Props[TeacherActor],"teacherActor")
  val studentActorRef=system.actorOf(Props(new StudentActor(teacherActorRef)),"studentActor")

  studentActorRef ! InitSignal
  Thread.sleep(2000)
  system.shutdown()


}
