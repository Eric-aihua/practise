package com.eric.akka.sample

import akka.actor._

/**
 * Created by Eric on 2015/11/24.
 */



class StudentActor(teacherActorRef: ActorRef) extends Actor with ActorLogging{

  def receive={
    //如果接受到的是InitSignal消息就直接发往TeacherActor
    case InitSignal =>{
      log.info("Transfer message to TeacherActor");
      teacherActorRef ! QuoteRequest
    }
    case QuoteReponse(responseMsg)=>{
      log.info("Received message is: "+responseMsg)
    }

  }

}
