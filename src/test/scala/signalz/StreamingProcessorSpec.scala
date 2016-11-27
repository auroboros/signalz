package signalz

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by johnmcgill on 11/7/16.
  */
class StreamingProcessorSpec extends FlatSpec with Matchers {

  "StreamingProcessor" should "recur with continuously updated states" in {

    val sp = StreamingProcessor((i: Int) => i + 1, 50)

    sp.outStream.take(30) shouldEqual (50 to 79) // TODO: Off by one?
  }

  "StreamingProcessor" should "preprocess state before each update" in {

    val sp = StreamingProcessor.withModifier[Int, Int](i => i + 1, 50, (i, b) => i - b)

    sp.outStream((1 to 10).toStream).take(6) shouldEqual List(50, 49, 47, 44, 40, 35)
  }
}