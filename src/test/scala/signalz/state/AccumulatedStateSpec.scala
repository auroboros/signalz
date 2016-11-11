package signalz.state

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by johnmcgill on 11/7/16.
  */
class AccumulatedStateSpec extends FlatSpec with Matchers {

//  implicit val audioContext = AudioContext()

  def tupleStateCompose[A, B](func1: A => A, func2: B => B, transformer: (A, B) => B): ((A, B)) => (A, B) = {
    (t: (A, B)) =>
      val newA = func1(t._1)
      (newA, func2(transformer(newA, t._2)))
  }

  "Nested tuple solution" should "handle nesting neatly?" in {

//    val compFunc: ((OscState, Int)) => (OscState, Int) = tupleStateCompose(SineStateGen.nextState, (x: Int) => x * 3, (a, b) => b)
//    val compFunc2: (((OscState, Int), Double)) => ((OscState, Int), Double) = tupleStateCompose(compFunc, (x: Double) => Random.nextDouble, (a, b) => b)

    // As chain grows, input type will get sufficiently more complex, though maybe this is just for the initial state? Rest will be recycled, but still
    // there is an asymmetry in easy accessibility (which side of tuple has non-nested data) depdending on input/output side of function (want to access in
    // following function, yet need to somehow retrieve state from deeply nested object on input side
  }

//  def listStateCompose[A,B](func1: A => A, func2: B => B, transformer: (A, B) => B): List[Any] => List[Any] = {
//    (t: (A, B)) =>
//      val newA = func1(t._1)
//      (newA, func2(transformer(newA, t._2)))
//  }
  "List solution" should "have referenceable types..." in {
    // List solution suffers from type checking, essentially have to pattern match on prior types and throw run-time errors if its screwed up. Could work
    // but will get exceedingly complex when chaining?
  }

  "Hash with ID solution" should "neatly assign IDs?" in {

  }
}
