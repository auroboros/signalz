# signalz
Pronounced "signal-zed" a la scalaz. Generic signal processing types for Scala.

signalz is intended to be a slim library of higher-kinded types facilitating generic signal processing techniques. The goal is to keep these types flexible so that they may be applied to any signal processing medium (audio, visual, data). It will also provide a core for scalaudio to depend on (as some of these were originally developed as companion types for scalaudio). With these algebraic types abstracted, medium-specific libraries such as scalaudio will be free to focus solely on the math and IO associated with the given task rather than the details of function composition mechanics commonly associated with signal processing chains.
