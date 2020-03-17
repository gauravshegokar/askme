
name := "server"

version := "1.0"

lazy val server = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(javaJdbc, cache, javaWs)

libraryDependencies += "com.google.code.gson" % "gson" % "2.8.6"

libraryDependencies += "io.ebean" % "ebean" % "11.22.10"

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.11"
libraryDependencies += jdbc


unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")


