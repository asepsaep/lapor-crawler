import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._

name := """lapor-crawler"""
version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.9.2",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" % "slick-hikaricp_2.11" % "3.1.1",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc41",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "javax.inject" % "javax.inject" % "1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(DanglingCloseParenthesis, Preserve)
  .setPreference(AlignParameters, false)
  .setPreference(CompactStringConcatenation, false)
  .setPreference(IndentPackageBlocks, true)
  .setPreference(PreserveSpaceBeforeArguments, false)
  .setPreference(RewriteArrowSymbols, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40)
  .setPreference(SpaceBeforeColon, false)
  .setPreference(SpaceInsideBrackets, false)
  .setPreference(SpaceInsideParentheses, false)
  .setPreference(IndentSpaces, 2)
  .setPreference(IndentLocalDefs, false)
  .setPreference(SpacesWithinPatternBinders, true)
  .setPreference(SpacesAroundMultiImports, true)

mainClass in (Compile, run) := Some("io.github.asepsaep.laporcrawler.Main")