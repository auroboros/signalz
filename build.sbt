organization := "org.auroboros"
name := "signalz"
version := "0.0.1-SNAPSHOT"

homepage := Some(url("https://github.com/auroboros/signalz"))
licenses := Seq("copyright" -> url("https://github.com/auroboros/signalz/blob/master/license.txt"))

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

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.6",
  "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"
)