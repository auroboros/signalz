package signalz

import scalaz.Functor

/**
  * Created by johnmcgill on 10/25/16.
  */
case class StateFunction[A,B](processor: A => B)

case class SignalProcessor[A <: StateFunction[_, _]](stateFunction: A) {

  case class InnerProcessor[C, D](stateFunction: StateFunction[C,D]){
    def computeNext(input: C): D = stateFunction.processor(input)
  }

  val innerProcessor = InnerProcessor(stateFunction)
}

class SignalProcessorFunctor extends Functor[SignalProcessor] {
  override def map[A, B](fa: SignalProcessor[A])(f: (A) => B): SignalProcessor[B] =
    SignalProcessor(StateFunction(fa.innerProcessor.stateFunction.processor andThen f))
}