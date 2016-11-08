package signalz

import com.scalaudio.amp.immutable.ugen.{OscState, SineStateGen}
import com.scalaudio.core.AudioContext
import com.scalaudio.core.types.PitchRichInt
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._

/**
  * Created by johnmcgill on 11/7/16.
  */
class MonoStateProcessorSpec extends FlatSpec with Matchers {

  implicit val oscStateMonoid = OscStateMonoid

  "signalz" should "construct a stateful synth" in {
    implicit val audioContext = AudioContext()

    val msp = MonoStateProcessor(SineStateGen.nextState)

    1 to 1000 foreach {_ =>
      println(msp.nextState)
    }
  }
}
