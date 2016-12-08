# signalz
"signal-zed": Generic signal processing types for Scala.

## Build info
This project is currently compiled with Scala 2.11 only (cross-compilation support coming soon).

For use within an SBT project, add the following dependency in build.sbt:
```scala
libraryDependencies += "org.auroboros" %% "signalz" % "0.1.0-SNAPSHOT"
```

If referencing a snapshot version, the Sonatype snapshot repository must be added as well:
```scala
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
```

Or for other build tools, see:

https://mvnrepository.com/artifact/org.auroboros/signalz_2.11

## what is signal-z?
signal-z is a slim library of higher-kinded types representing generic signal processing paradigms. They are kept minimal and flexible so that they may be applied to any signal medium (audio, video, data). signal-z currently is used as the type core for scalaudio (as some of these were originally developed as companion types for scalaudio). With these algebraic types abstracted, medium-specific libraries such as scalaudio are free to focus solely on the math and IO specific to the medium rather than common signal processing mechanics.
