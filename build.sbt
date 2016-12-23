organization := "org.auroboros"
name := "signal-z"
version := "0.1.0-SNAPSHOT"

homepage := Some(url("https://github.com/auroboros/signal-z"))
licenses := Seq("copyright" -> url("https://github.com/auroboros/signal-z/blob/master/license.txt"))

pomExtra := <scm>
      <url>git@github.com:auroboros/signal-z.git</url>
      <connection>scm:git:git@github.com:auroboros/signal-z.git</connection>
    </scm>
    <developers>
      <developer>
        <id>fat0wl</id>
        <name>John McGill</name>
        <url>https://github.com/auroboros</url>
      </developer>
    </developers>

//isSnapshot := true // use to force overwrite of local

scalaVersion := "2.11.8"

publishMavenStyle := true

publishArtifact in Test := false // Not req because is default?

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.8" % "test",
  "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"
)