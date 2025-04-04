ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"


val baseImage = "sbtscala/scala-sbt:eclipse-temurin-11.0.17_8_1.8.2_2.13.10"
// val dockerBaseImage = "amazoncorretto:11.0.26-al2023-headless"

lazy val root = (project in file("."))
  .settings(
    name := "example",
    dockerBaseImage := baseImage,
    dockerExposedPorts += 8080,
    dockerUpdateLatest := true,
    Docker / daemonUserUid := None,
    Docker / daemonUser := "daemon"
  ).enablePlugins(DockerPlugin, JavaAppPackaging)

val os = System.getProperty("os.name").toLowerCase
val platformClassifier = {
  (os, System.getProperty("os.arch").toLowerCase) match {
    case (mac, arm) if mac.contains("mac") && (arm.contains("aarch") || arm.contains("arm")) => "osx-aarch_64"
    case (mac, x86) if mac.contains("mac") && (x86.contains("x86") || x86.contains("amd64")) => "osx-x86_64"
    case (linux, arm) if linux.contains("linux") && (arm.contains("aarch") || arm.contains("arm")) => "linux-aarch_64"
    case (linux, x86) if linux.contains("linux") && (x86.contains("x86") || x86.contains("amd64")) => "linux-x86_64"
    case (osName, archName) => throw new RuntimeException(s"Unsupported platform $osName $archName")
  }
  "linux-x86_64"
}

libraryDependencies += "io.valkey" % "valkey-glide" % "1.+" classifier platformClassifier
