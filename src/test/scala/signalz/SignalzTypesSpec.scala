package signalz

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by johnmcgill on 11/28/16.
  */
class SignalzTypesSpec extends FlatSpec with Matchers {

  "Stream producer" should "produce a stream" in {
    val testStreamProducer: StreamProducer[Unit,Int] = () => Stream.continually(5)
    val materializedStream: Stream[Int] = testStreamProducer() // Use def & test long process for GC pauses?

    materializedStream.take(10).toList shouldEqual List.fill(10)(5)
  }
}
