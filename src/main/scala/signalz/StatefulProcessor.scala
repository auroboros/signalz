package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

case class StatefulProcessor[A, B](process: A => A,
                                   initState: A,
                                   modify: (=>A, B) => A = ((a, b) => identity(a)) : (=>A, B) => A) {

  var state : A = initState

  val coreFunc = modify.curried(state).andThen(process)

  val nextState : (B) => A = (input: B) => {
    state = coreFunc(input)
    state
  }
}