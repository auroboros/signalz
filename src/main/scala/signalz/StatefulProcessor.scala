package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

case class StatefulProcessor[A, B](process: A => A,
                                   initState: A,
                                   modify: Option[(=> A, B) => A] = None) {

  var state: A = initState

  val coreFunc = modify.map(_.curried(state).andThen(process))
    .getOrElse((b: B) => process(state))

  val nextState: (B) => A = (input: B) => {
    state = coreFunc(input)
    state
  }
}

object StatefulProcessor {
  def apply[A](process: A => A,
               initState: A) = new StatefulProcessor[A, Unit](process, initState)

  def withModifier[A, B](process: A => A,
                         initState: A,
                         modify: (=> A, B) => A) = new StatefulProcessor(process, initState, Some(modify))
}