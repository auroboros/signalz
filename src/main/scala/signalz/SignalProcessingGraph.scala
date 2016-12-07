package signalz

/**
  * Created by johnmcgill on 12/6/16.
  */
// TODO: Maybe instead of post-processing, take out counter completely?
// Can have frame increment func be auto-added before every graph (map function on graph to make it clear?)
trait SignalProcessingGraph {
  var currentFrame: Long = 0 // Current time in samples

  def postFrameProcessingAction(): Unit = {}

  def run(nFrames: Long)
  def runWhile(loopCondition: Unit => Boolean)
}

class FunctionGraph(val signalGraph: () => _) extends SignalProcessingGraph {

  def run(nFrames: Long) =
    1L to nFrames foreach { s =>
      signalGraph()
      currentFrame = s
      postFrameProcessingAction()
    }

  def runWhile(loopCondition: Unit => Boolean) =
    while (loopCondition()) {
      signalGraph()
      currentFrame += 1
      postFrameProcessingAction()
    }

}

class StreamGraph(stream: => Stream[_]) extends SignalProcessingGraph {

  def run(nFrames: Long) =
    stream.map{_ =>
      currentFrame += 1
      postFrameProcessingAction()
    }.take(nFrames.toInt)

  def runWhile(loopCondition: Unit => Boolean): Unit =
    stream.map{_ =>
      currentFrame += 1
      postFrameProcessingAction()
    }.takeWhile(loopCondition)
}