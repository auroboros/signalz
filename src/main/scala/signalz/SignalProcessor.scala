package signalz

import scalaz._
import Scalaz._

/**
  * Created by johnmcgill on 10/25/16.
  */

case class SignalProcessor[A, B](process: A => B) {

  // At this point does this even merit a wrapper or is this just simple function composition?
  // Use scalaz Function1 functor or just use "andThen"?

  def map[C](postProcess: B => C) = SignalProcessor(process.map(postProcess))

  // Monoid for B so we can get initial state & then mappend to thread signal through? (Default process implementation?)
  def statefulMap[C](postProcess: B => C) = {

//    val mb = implicitly[Monoid[B]]
//    var storedState: B = mb.zero
    var storedState: Option[B] = None

    val instrumentedFunction = process map { transientB =>
      storedState = Some(transientB)
      storedState.get
    } map postProcess

    SignalProcessor(instrumentedFunction)
  }

  def flatMap[C](nextSignalProcessor: SignalProcessor[B, C]): SignalProcessor[A, C] = SignalProcessor(process.map(nextSignalProcessor.process))
}