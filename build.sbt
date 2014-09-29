lazy val root = (project in file(".")).
	aggregate(JavaPatterns , ScalaPatterns)

lazy val JavaPatterns = project

lazy val ScalaPatterns = project

name := "Patterns Project"

version := "1.1"