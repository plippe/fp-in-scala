
lazy val chapterSettings = Seq(
    scalaVersion := "2.12.7",
    scalafmtOnCompile := true,

    libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.5" % "test",
        "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
    ),
)

lazy val chapter02 = (project in file("chapter-02-getting-started-with-functional-programming-in-scala"))
    .settings(chapterSettings)

lazy val chapter03 = (project in file("chapter-03-functional-data-structures"))
    .settings(chapterSettings)

lazy val chapter04 = (project in file("chapter-04-handling-errors-without-exceptions"))
    .settings(chapterSettings)

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

lazy val chapter10 = (project in file("chapter-10-monoids"))
    .dependsOn(chapter07)
    .settings(chapterSettings)
    .settings(scalacOptions -= "-Ywarn-dead-code")

lazy val chapter11 = (project in file("chapter-11-monads"))
    .dependsOn(chapter07)
    .dependsOn(chapter08)
    .dependsOn(chapter09)
    .settings(chapterSettings)

lazy val chapter12 = (project in file("chapter-12-applicative-and-traversable-functors"))
    .dependsOn(chapter06)
    .dependsOn(chapter10)
    .dependsOn(chapter11)
    .settings(chapterSettings)

lazy val chapter13 = (project in file("chapter-13-external-effects-and-io"))
    .dependsOn(chapter07)
    .settings(chapterSettings)

lazy val chapter14 = (project in file("chapter-14-local-effects-and-mutable-state"))
    .settings(chapterSettings)

lazy val chapter15 = (project in file("chapter-15-stream-processing-and-incremental-io"))
    .dependsOn(chapter13)
    .settings(chapterSettings)
