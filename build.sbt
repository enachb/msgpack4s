name := "msgpack4s"

// Remove -SNAPSHOT from the version before publishing a release. Don't forget to change the version to
// $(NEXT_VERSION)-SNAPSHOT afterwards!
version := "0.4-SNAPSHOT"

organization := "org.velvia"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.10.0")

unmanagedSourceDirectories in Compile <++= Seq(baseDirectory(_ / "src" )).join

unmanagedSourceDirectories in Test <++= Seq(baseDirectory(_ / "test" )).join

// Testing deps
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "1.9.1" % "test",
                            "org.mockito" % "mockito-all" % "1.9.0" % "test")

publishMavenStyle := true

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo <<= (version) { version: String =>
    val nexus = "http://m0.quantifind.com:8082/nexus/content/repositories/"
    if (version.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "ext-snapshots/")
    else                                   Some("releases" at nexus + "ext-releases/")
}
