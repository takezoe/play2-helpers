name := "play2-helpers"

organization := "jp.sf.amateras.play2"

version := "0.0.1"

scalaVersion := "2.9.1"

//crossScalaVersions := Seq("2.8.1", "2.9.1", "2.9.1-1", "2.9.2")

libraryDependencies ++= Seq(
  "play" %% "play" % "2.0.4" % "provided->default"
)

publishTo <<= (version) { version: String =>
  val repoInfo =
    if (version.trim.endsWith("SNAPSHOT"))
      ("amateras snapshots" -> "/home/groups/a/am/amateras/htdocs/mvn-snapshot/")
    else
      ("amateras releases" -> "/home/groups/a/am/amateras/htdocs/mvn/")
  Some(Resolver.ssh(
    repoInfo._1,
    "shell.sourceforge.jp",
    repoInfo._2) as(System.getProperty("user.name"), (Path.userHome / ".ssh" / "id_rsa").asFile) withPermissions("0664"))
}
