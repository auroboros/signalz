package signalz

import com.scalaudio.amp.immutable.ugen.{OscState, SineStateGen}
import com.scalaudio.core.types.PitchRichInt
import com.scalaudio.core.{AudioContext, CoreSyntax, ScalaudioConfig}
import org.scalatest.{FlatSpec, Matchers}
/**
  * Created by johnmcgill on 11/7/16.
  */
class MonoStateProcessorSpec extends FlatSpec with Matchers with CoreSyntax {

  implicit val audioContext = AudioContext(ScalaudioConfig(nOutChannels = 1))
  val defaultOscState = OscState(0, PitchRichInt(440).Hz, 0)

  "signalz" should "collect values from a stateful synth" in {

    val msp = MonoStateProcessor[Unit, OscState](SineStateGen.nextState, defaultOscState)

    1 to 1000 foreach {_ =>
      println(msp.coreFunc())
    }
  }

  "signalz" should "play audio from a stateful synth" in {

//    val msp = MonoStateProcessor(SineStateGen.nextState, defaultOscState)
//
//    AmpOutput(() => Array(msp.nextState.sample)).play(5.seconds)
  }
}
