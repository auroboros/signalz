package signalz

/**
  * Created by johnmcgill on 11/26/16.
  */
// TODO: Technically there is no need for OO style here... these 2 classes can basically just be factory methods for producing a stream
// (but then process/modify functions would need to passed around? Then maybe an anonymous object, so may as well use a class...?
class StreamingProcessor[A](val process: A => A,
                            val initState: A) {

  // Could remove this & simply make this stream a default param to recursive process, but would require empty-parens access so probably won't bother...
  def outStream: Stream[A] = recursiveProcess(initState)

  private def recursiveProcess(state: A): Stream[A] = state #:: recursiveProcess(process(state))
}

class StreamingProcessorWithModifier[A, B](val process: A => A,
                                           val initState: A,
                                           val modify: (A, B) => A) {

  def outStream(inStream: => Stream[B]): Stream[A] = recursiveProcess(initState, inStream.tail) // TODO: Use tail only since init state is fixed?

  private def recursiveProcess(currentState: A, inStream: => Stream[B]): Stream[A] =
    currentState #:: recursiveProcess(process(modify(currentState, inStream.head)), inStream.tail) // TODO: ...or use modified state for both pieces of this function? Kindof strange since process isn't really called for first...
}

object StreamingProcessor {

  def apply[A](process: A => A, initState: A) = new StreamingProcessor(process, initState)

  def withModifier[A, B](process: A => A,
                         initState: A,
                         modify: (A, B) => A) = new StreamingProcessorWithModifier(process, initState, modify)
}