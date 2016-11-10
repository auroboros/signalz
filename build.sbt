organization := "org.auroboros"
name := "signalz"
version := "0.0.1-SNAPSHOT"

//isSnapshot := true // use to force overwrite of local

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.6",
  "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"
)