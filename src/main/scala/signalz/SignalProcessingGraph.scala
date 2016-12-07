package signalz

/**
  * Created by johnmcgill on 12/6/16.
  */

trait SignalProcessingGraph {
  var currentFrame: Long = 0 // Current time in samples

  def run(nFrames: Long)
  def runWhile(loopCondition: Unit => Boolean)
}

class FunctionGraph(val signalGraph: () => _) extends SignalProcessingGraph {

  def run(nFrames: Long) =
    1L to nFrames foreach { s =>
      signalGraph()
      currentFrame = s
    }

  def runWhile(loopCondition: Unit => Boolean) =
    while (loopCondition()) {
      signalGraph()
      currentFrame += 1
    }

}

class StreamGraph(stream: => Stream[_]) extends SignalProcessingGraph {

  def run(nFrames: Long) =
    stream.map(_ => currentFrame += 1).take(nFrames.toInt)

  def runWhile(loopCondition: Unit => Boolean): Unit =
    stream.map(_ => currentFrame += 1).takeWhile(loopCondition)
}