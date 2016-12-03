package signalz

/**
  * Created by johnmcgill on 12/2/16.
  */
trait SequentialState[A, C] {

  def nextState(currentState: A)(implicit context: C): A

  // Necessary intermediate syntax? Or useless....
  object statefulProcessor {
    def apply(initState: A)(implicit context: C): StatefulProcessor[A] = StatefulProcessor(nextState, initState)

    def withModifier[B](initState: A, modify: (A, B) => A)(implicit context: C): StatefulProcessorWithModifier[A, B] =
      StatefulProcessor.withModifier(nextState, initState, modify)
  }

  object streamingProcessor {
    def apply(initState: A)(implicit context: C): StreamingProcessor[A] = StreamingProcessor(nextState, initState)

    def withModifier[B](initState: A, modify: (A, B) => A)(implicit context: C): StreamingProcessorWithModifier[A, B] =
      StreamingProcessor.withModifier(nextState, initState, modify)
  }

  // Convenience syntax (ultraaaa)
  object asFunction {
    def apply(initState: A)(implicit context: C): () => A = statefulProcessor(initState).nextState

    def withModifier[B](initState: A, modify: (A, B) => A)(implicit context: C): B => A = statefulProcessor.withModifier(initState, modify).nextState
  }

  object asStream {
    def apply(initState: A)(implicit context: C): Stream[A] = streamingProcessor(initState).outStream

    // TODO: Can B stream be its own param list & this will be curryable...?
    def withModifier[B](initState: A, modify: (A, B) => A)(implicit context: C): (=> Stream[B]) => Stream[A] = streamingProcessor.withModifier(initState, modify).outStream
  }

}