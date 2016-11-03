package signalz

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random
import scalaz._
import Scalaz._
/**
  * Created by johnmcgill on 11/2/16.
  */
class SignalProcessorSpec extends FlatSpec with Matchers {

  implicit val sigProcFunctorInstance = new SignalProcessorFunctor()

  "a" should "b" in {
    val sigProc = new SignalProcessor(Random.nextDouble)

    sigProc.map(_ * 100.ceil.toChar)

    1 to 1000 foreach { _ =>
      println(sigProc.getSignalOut)
    }
  }

  "a functor" should "b" in {
    val sigProc = new SignalProcessor(Random.nextDouble)

    val charSigProc = sigProc.map(d => (d * 100 + 30).ceil.toChar)

    1 to 1000 foreach { _ =>
      println(charSigProc.getSignalOut)
    }
  }
}
