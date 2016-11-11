package signalz

import org.scalatest.{FlatSpec, Matchers}
/**
  * Created by johnmcgill on 11/7/16.
  */
class StatefulProcessorSpec extends FlatSpec with Matchers {

  "StatefulProcessor" should "recur with continuously updated states" in {

    val sp = StatefulProcessor((i : Int) => i + 1, 50)

    1 to 30 map {_ =>
      sp.nextState()
    } shouldEqual (51 to 80) // TODO: Off by one?
  }

  "StatefulProcessor" should "preprocess state before each update" in {

    val outsider = 10

    val sp = StatefulProcessor((i : Int) => i + 1, 50, Some((i: Int) => i - outsider))

    1 to 5 map {_ =>
      sp.nextState()
    } shouldEqual Vector(41, 32, 23, 14, 5)
  }
}