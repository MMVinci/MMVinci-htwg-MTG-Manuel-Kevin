import scoverage.ScoverageKeys._

lazy val root = (project in file("."))
  .settings(
    name := "MTG-Simulator",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.8",

    coverageEnabled := true,

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1"
    )
  )
