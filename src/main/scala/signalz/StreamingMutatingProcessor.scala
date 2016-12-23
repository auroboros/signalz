package signalz

/**
  * Created by johnmcgill on 12/20/16.
  */
class StreamingMutatingProcessor[S,I,O](process: (I, S) => (O, S),
                                        var state: S) {

  def outStream(inStream: => Stream[I]): Stream[(O,S)] = recursiveProcess(inStream)

  private def recursiveProcess(inStream: => Stream[I]): Stream[(O,S)] = process(inStream.head, state) #:: recursiveProcess(inStream.tail)
}
