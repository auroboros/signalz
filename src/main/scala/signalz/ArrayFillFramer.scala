package signalz

import scala.collection.mutable.ArrayBuffer

/**
  * Created by johnmcgill on 11/10/16.
  */
case class ArrayFillFramer(producer: () => Double, channels: Int = 1) {
  val frame : ArrayBuffer[Double] = ArrayBuffer.fill(3)(0.0)

  val nextFrame : () => Seq[Double] = () => {
    val newSample = producer()
    1 to channels foreach { n => frame.insert(n, newSample) }
    frame
  }
}
