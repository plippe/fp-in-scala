
lazy val chapterSettings = Seq(
    scalaVersion := "2.12.7",
    scalafmtOnCompile := true,
)

lazy val chapter02 = (project in file("chapter-02-getting-started-with-functional-programming-in-scala"))
    .settings(chapterSettings)
