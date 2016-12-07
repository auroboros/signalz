package signalz

/**
  * Created by johnmcgill on 12/6/16.
  */

trait SignalProcessingGraph {

  def run(nIterations: Long)

  def runWhile(loopCondition: Unit => Boolean)
}

class FunctionGraph(val signalGraph: () => _) extends SignalProcessingGraph {

  def run(nIterations: Long) = 1L to nIterations foreach { _ => signalGraph() }

  def runWhile(loopCondition: Unit => Boolean) = while (loopCondition()) {
    signalGraph()
  }

}

class StreamGraph(stream: => Stream[_]) extends SignalProcessingGraph {

  def run(nIterations: Long) = stream.take(nIterations.toInt)

  def runWhile(loopCondition: Unit => Boolean): Unit = stream.takeWhile(_ => loopCondition())
}