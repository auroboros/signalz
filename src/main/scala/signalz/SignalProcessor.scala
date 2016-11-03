package signalz

import scala.concurrent.Future
import scala.util.Random
import scalaz.Alpha.{A, B}
import scalaz.{Bifunctor, Functor}

/**
  * Created by johnmcgill on 10/25/16.
  */

class SignalProcessor[A](signalOut: => A){

  def getSignalOut = signalOut
}

class SignalProcessorFunctor extends Functor[SignalProcessor] {
  override def map[A, B](fa: SignalProcessor[A])(f: (A) => B): SignalProcessor[B] =
    new SignalProcessor(f(fa.getSignalOut))
}