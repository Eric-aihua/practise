package com.eric.akka.sample

import akka.actor.{ActorLogging, Actor}

import scala.util.Random

/**
 * Created by Eric on 2015/11/24.
 */
class TeacherActor extends Actor with ActorLogging {
  val responseMessage = Array("Moderation is for cowards", "Anything worth doing is worth overdoing", "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def receive ={
    case QuoteRequest => {
      val quoteResponse = QuoteReponse(responseMessage(Random.nextInt(responseMessage.length)))
      log.info(quoteResponse.toString)
      //将相应信息返回到StudentActor
      sender ! quoteResponse
    }
  }
}
