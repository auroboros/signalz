package experiments

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by johnmcgill on 12/1/16.
  */
class TakeMemoization extends FlatSpec with Matchers {

  var inc = 0

  def calculate():Int = {
    println(s"caculating")
    inc += 1
    inc
  }

  "stream memoization" should "not occur when consuming with take" in {
    def takeStream: Stream[Int] = Stream.continually(calculate())

    takeStream.take(5).foreach(println)

    val sixthTake = takeStream.take(1)
    println(sixthTake.head)

    sixthTake.head shouldBe 6
  }
}
