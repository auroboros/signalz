package signalz

/**
  * Created by johnmcgill on 12/9/16.
  */
case class StateMutatingProcessor[S, I, O](process: (I, S) => (O, S),
                                           initState: S) {
  val state: S = initState

  val next: I => (O, S) = (i: I) => {
    process(i, state)
  }
}
