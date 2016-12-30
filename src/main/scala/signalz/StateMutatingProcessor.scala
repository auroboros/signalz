package signalz

/**
  * Created by johnmcgill on 12/9/16.
  */
class StateMutatingProcessor[S, I, O](process: (I, S) => (O, S),
                                           initState: S) {
  val state: S = initState

  val next: I => (O, S) = (i: I) => {
    process(i, state)
  }
}

class StateMutatingProcessorWithModifier[S, I, O, M](process: (I, S) => (O, S),
                                                       initState: S,
                                                       modify: (M, I, S) => Unit) {
  val state: S = initState

  val next: (M, I) => (O, S) = (modInput:M, input: I) => {
    modify(modInput, input, state)
    process(input, state)
  }
}

object StateMutatingProcessor {
  def apply[S, I, O](process: (I, S) => (O, S),
                     initState: S) = new StateMutatingProcessor(process, initState)

  def withModifier[S, I, O, M](process: (I, S) => (O, S),
                            initState: S,
                            modify: (M, I, S) => Unit) = new StateMutatingProcessorWithModifier(process, initState, modify)
}