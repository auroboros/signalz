package signalz

/**
  * Created by johnmcgill on 10/25/16.
  */

case class MonoStateProcessor[I,S](processF: S => S, initState: S, preF: Option[(=>S,I) => S] = None) { //, postF: Option[(=>S) => O]) {

  var state : S = initState

  val nextStateF : S => S = (intermediateState: S) => {
    state = processF(intermediateState)
    state
  }

  val tmp: (I) => S = preF.map(_.curried(state)).getOrElse((input: I) => state)
  val coreFunc: (I) => S = tmp andThen nextStateF
//  val coreFunc: (I) => Any = postF.map(pf => tmp2 andThen pf).getOrElse(tmp2)
}