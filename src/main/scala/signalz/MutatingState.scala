package signalz

/**
  * Created by johnmcgill on 12/9/16.
  */
trait ReflexiveMutatingState[S,I,O] extends MutatingState[S,I,O] {
  self: S =>

  val thisAsState: S = this

  object asReflexiveFunction {
    def apply() : (I) => (O, S) = asFunction(thisAsState)

    def withModifier[M](modify: (M,I,S) => Unit) = asFunction.withModifier(thisAsState, modify)
  }
}

trait MutatingState[S, I, O] {

  def process(i: I, s: S): (O, S)

  //private?
  object mutableProcessor {
    def apply(initState: S) = StateMutatingProcessor(process, initState)

    def withModifier[M](initState: S,
                     modify: (M,I,S) => Unit) = StateMutatingProcessor.withModifier(process, initState, modify)
  }

  object asFunction {
    def apply(initState: S): (I) => (O, S) = mutableProcessor(initState).next

    def withModifier[M](initState: S,
                     modify: (M,I,S) => Unit) = mutableProcessor.withModifier(initState, modify).next
  }

}


