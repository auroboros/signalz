package signalz

import scala.concurrent.Future
import scalaz.{Bifunctor, Functor}

/**
  * Created by johnmcgill on 10/25/16.
  */

case class SignalProcessor[A](process: A){
  // Need evidence to convert to some type? Monoid?

  // Maybe can actually be vanilla function composition, with state interceptor?
  def chain[B](toUnit: SignalProcessor[B]) = SignalProcessor(// chainable? )
}

class SignalProcessorFunctor extends Functor[SignalProcessor] {
  override def map[A, B](fa: SignalProcessor[A])(f: (A) => B): SignalProcessor[B] =
    SignalProcessor()
}