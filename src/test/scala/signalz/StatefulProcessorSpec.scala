package signalz

import org.scalatest.{FlatSpec, Matchers}
/**
  * Created by johnmcgill on 11/7/16.
  */
class StatefulProcessorSpec extends FlatSpec with Matchers {

  "StatefulProcessor" should "recur with continuously updated states" in {

    val sp = StatefulProcessor((i: Int) => i + 1, 50)

    1 to 30 map {_ =>
      sp.nextState()
    } shouldEqual (51 to 80) // TODO: Off by one?
  }

  "StatefulProcessor" should "preprocess state before each update" in {

    val sp = StatefulProcessor.withModifier[Int, Int](i => i + 1, 50, (i, b) => i - b)

    1 to 5 map {_ =>
      sp.nextState(10)
    } shouldEqual Vector(41, 32, 23, 14, 5)
  }

  // TODO: use Int tuple as state, and do some addition of counter value + mod 3 of some separate pre-processor counter?
  // Basically... something convoluted enough to test the flow as in a unit gen, but where we can easily verify / manually compute the values
}