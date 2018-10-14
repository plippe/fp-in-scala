
lazy val chapterSettings = Seq(
    scalaVersion := "2.12.7",
    scalafmtOnCompile := true,
)

lazy val chapter02 = (project in file("chapter-02-getting-started-with-functional-programming-in-scala"))
    .settings(chapterSettings)

lazy val chapter03 = (project in file("chapter-03-functional-data-structures"))
    .settings(chapterSettings)

lazy val chapter04 = (project in file("chapter-04-handling-errors-without-exceptions"))
    .settings(chapterSettings)
    .settings(
        scalacOptions -= "-Ywarn-dead-code"
    )

lazy val chapter05 = (project in file("chapter-05-strictness-and-laziness"))
    .settings(chapterSettings)

lazy val chapter06 = (project in file("chapter-06-purely-functional-state"))
    .settings(chapterSettings)

lazy val chapter07 = (project in file("chapter-07-purely-functional-parallelism"))
    .settings(chapterSettings)

lazy val chapter08 = (project in file("chapter-08-property-based testing"))
    .settings(chapterSettings)

lazy val chapter09 = (project in file("chapter-09-parser-combinators"))
    .settings(chapterSettings)
