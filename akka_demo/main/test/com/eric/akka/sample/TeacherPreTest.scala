//package com.eric.akka.sample
//
///**
// * Created by Eric on 2015/11/24.
// */
//class TeacherPreTest extends TestKit(ActorSystem("StudentAndTeacherSample")) with WordSpecLike with MustMatchers with BeforeAndAfterAll {
//  //1. Sends message to the Print Actor. Not even a testcase actually
//  "A teacher" must {
//
//    "print a quote when a QuoteRequest message is sent" in {
//
//      val teacherRef = TestActorRef[TeacherActor]
//      teacherRef ! QuoteRequest
//    }
//  }
//
//  //2. Sends message to the Log Actor. Again, not a testcase per se
//  "A teacher with ActorLogging" must {
//
//    "log a quote when a QuoteRequest message is sent" in {
//
//      val teacherRef = TestActorRef[TeacherLogActor]
//      teacherRef ! QuoteRequest
//    }
//  }
//
//  override def afterAll() {
//    super.afterAll()
//    system.shutdown()
//  }
//}