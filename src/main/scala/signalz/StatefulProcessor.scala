package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

case class StatefulProcessor[A](process: A => A, initState: A, modify: Option[A => A] = None) {

  var state : A = initState

  val coreFunc = modify.map(f => f.andThen(process)).getOrElse(process)

  val nextState : () => A = () => {
    state = coreFunc(state)
    state
  }
}