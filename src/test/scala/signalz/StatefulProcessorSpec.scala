package signalz

import com.scalaudio.amp.immutable.ugen.{OscState, SineStateGen}
import com.scalaudio.core.{AudioContext, CoreSyntax, ScalaudioConfig}
import com.scalaudio.core.engine.samplewise.AmpOutput
import com.scalaudio.core.types.PitchRichInt
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import scala.concurrent.duration._
/**
  * Created by johnmcgill on 11/7/16.
  */
class StatefulProcessorSpec extends FlatSpec with Matchers with CoreSyntax {

  implicit val audioContext = AudioContext(ScalaudioConfig(nOutChannels = 1))
  val defaultOscState = OscState(0, PitchRichInt(440).Hz, 0)

  "signalz" should "construct a stateful synth" in {

    val msp = StatefulProcessor(SineStateGen.nextState, defaultOscState)

    1 to 1000 foreach {_ =>
      println(msp.nextState().sample)
    }
  }

  "signalz" should "play audio from a stateful synth" in {

    val msp = StatefulProcessor(SineStateGen.nextState, defaultOscState)

    AmpOutput(msp.nextState.map(s => Array(s.sample))).play(5.seconds)
  }
}