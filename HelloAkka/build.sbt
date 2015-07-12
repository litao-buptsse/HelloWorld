name := "HelloAkka"

version := "1.0"

scalaVersion := "2.10.4"

autoScalaLibrary := false

resolvers ++= Seq(
  "Central Maven Repository" at "http://repo1.maven.org/maven/"
)

libraryDependencies ++= {
  val akkaVersion = "2.3.12"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
  )
}