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

// For type parameters to work, would likely have to have several flavors of each processing style. How is this better than S => S (pre-curried/closure) functions
// being given straight to the processor?
case class MonoStatePreTransformProcessor[I,S]

case class MonoStatePostTransformProcessor[S,O]

case class MonoStatePrePostTransformProcessor[I,S,O]