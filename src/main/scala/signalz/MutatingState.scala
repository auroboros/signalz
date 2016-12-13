package signalz

/**
  * Created by johnmcgill on 12/9/16.
  */
trait ReflexiveMutatingState[S,I,O] extends MutatingState[S,I,O] {
  self: S =>

  def asReflexiveFunction = mutableProcessor(this).next
}

trait MutatingState[S, I, O] {

  def process(i: I, s: S): (O, S)

  //private?
  object mutableProcessor {
    def apply(initState: S) = StateMutatingProcessor(process, initState)

    def withModifier(initState: S,
                     modify: (I,S) => Unit) = StateMutatingProcessor.withModifier(process, initState, modify)
  }

  object asFunction {
    def apply(initState: S): (I) => (O, S) = mutableProcessor(initState).next

    def withModifier(initState: S,
                     modify: (I,S) => Unit) = mutableProcessor.withModifier(initState, modify).next
  }

}


