lazy val phoneCompany = (project in file(".")).settings(
  Seq(
    name := "disco-test-phone-company",
    version := "1.0",
    scalaVersion := "2.12.3"
  )
)

libraryDependencies += "com.typesafe" % "config" % "1.4.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"