package signalz

/**
  * Created by johnmcgill on 11/26/16.
  */
class StreamingProcessor[A](val process: A => A,
                            val initState: A) {

  def outStream: Stream[A] = recursiveProcess(initState)

  private def recursiveProcess(currentState: A): Stream[A] =
    currentState #:: recursiveProcess(process(currentState))
}

class StreamingProcessorWithModifier[A, B](val process: A => A,
                                           val initState: A,
                                           val modify: (A, B) => A) {

  def outStream(inStream: Stream[B]): Stream[A] = recursiveProcess(initState, inStream.tail) // TODO: Use tail only since init state is fixed?

  private def recursiveProcess(currentState: A, inStream: Stream[B]): Stream[A] =
    currentState #:: recursiveProcess(process(modify(currentState, inStream.head)), inStream.tail) // TODO: ...or use modified state for both pieces of this function? Kindof strange since process isn't really called for first...
}

object StreamingProcessor {

  def apply[A](process: A => A, initState: A) = new StreamingProcessor(process, initState)

  def withModifier[A, B](process: A => A,
                         initState: A,
                         modify: (A, B) => A) = new StreamingProcessorWithModifier(process, initState, modify)
}