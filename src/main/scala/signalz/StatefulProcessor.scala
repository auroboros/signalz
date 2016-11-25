package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

case class StatefulProcessor[A, B](process: A => A,
                                   initState: A,
                                   modify: (A,B) => A = (a: A, b: Unit) => a) {

  var state : A = initState

//  val coreFunc: (B) => A = modify.curried(state).andThen(process)
    //modify.map(f => f.andThen(process)).getOrElse(process)

  val nextState : (B) => A = (input: B) => {
    state = process(modify(state, input))
    state
  }
}