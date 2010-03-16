import sbt._

class Config(info: ProjectInfo) extends DefaultProject(info) {
  //val newReleaseToolsRepository = "Scala Tools Repository" at "http://www.scala-tools.org/repo-snapshots"
  val scalatest = "org.scalatest" % "scalatest" %
      "1.0.1-for-scala-2.8.0.Beta1-with-test-interfaces-0.3-SNAPSHOT" % "test"
//  val scalatest = "org.scalatest" %% "scalatest" % "1.0" 
}