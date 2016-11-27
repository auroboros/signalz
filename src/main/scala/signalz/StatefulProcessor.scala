package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

class StatefulProcessor[A](val process: A => A,
                           val initState: A) {

  var state: A = initState

  val nextState: () => A = () => {
    state = process(state)
    state
  }
}

class StatefulProcessorWithModifier[A, B](val process: A => A,
                                          val initState: A,
                                          val modify: (A, B) => A) {

  var state: A = initState

  val nextState: (B) => A = (input: B) => {
    state = process(modify(state, input))
    state
  }
}

object StatefulProcessor {
  def apply[A](process: A => A,
               initState: A) = new StatefulProcessor[A](process, initState)

  def withModifier[A, B](process: A => A,
                         initState: A,
                         modify: (A, B) => A) = new StatefulProcessorWithModifier(process, initState, modify)
}