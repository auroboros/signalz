package signalz

/**
  * Created by johnmcgill on 12/9/16.
  */
trait ReflexiveMutatingState[S,I,O] extends MutatingState[S,I,O] {
  self: S =>

  def asReflexiveFunction = mutableProcessor(process, this).next
}

trait MutatingState[S, I, O] {

  def process(i: I, s: S): (O, S)

  //private?
  object mutableProcessor {
    def apply(process: (I, S) => (O, S), initState: S) = StateMutatingProcessor(process, initState)
  }

  object asFunction {
    def apply(initState: S): (I) => (O, S) = mutableProcessor(process, initState).next
  }

}


