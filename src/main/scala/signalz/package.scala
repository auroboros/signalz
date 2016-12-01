/**
  * Created by johnmcgill on 11/28/16.
  */
package object signalz {
  type StreamProducer[A,B] = Function1[A, Stream[B]]

  implicit def emptyParensFunc2UnitStreamProducer[A](producer: () => Stream[A]): StreamProducer[Unit, A] = (u: Unit) => producer()

}
