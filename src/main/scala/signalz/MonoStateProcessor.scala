package signalz

import com.scalaudio.amp.immutable.ugen.OscState
import com.scalaudio.core.types.PitchRichInt

import scalaz._

/**
  * Created by johnmcgill on 10/25/16.
  */

case class MonoStateProcessor[A](process: A => A, modify: Option[A => A] = None, initState: Option[A] = None)(implicit aMonoid: Monoid[A]) {

  var state : A = initState.getOrElse(aMonoid.zero)

  val coreFunc = modify.map(f => f.andThen(process)).getOrElse(process)

  def nextState : A = {
    state = coreFunc(state)
    state
  }
}

object OscStateMonoid extends Monoid[OscState] {
  override def append(a: OscState, b: => OscState): OscState = b
  override def zero: OscState = OscState(0, PitchRichInt(440).Hz, 0)
}