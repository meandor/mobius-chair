package com.github.meandor

import java.io.File
import java.nio.file.{Files, Paths}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, LocalFileSystem}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

class MobiusChairTest extends AnyFeatureSpec with Matchers {
  val basePath = "src/test/resources/filesystem"
  val localHDFS: LocalFileSystem = FileSystem.getLocal(new Configuration())

  Feature("should get latest generation in path") {
    Scenario("should return None when nothing existing yet") {
      MobiusChair.latestGeneration(localHDFS, s"$basePath/inFoo/0001") shouldBe None
    }

    Scenario("should return latest when n generations existing") {
      MobiusChair.latestGeneration(localHDFS, s"$basePath/inFooBar/0002").get shouldBe "0009"
    }
  }

  Feature("should get next generation in path") {
    Scenario("one generation already existing") {
      MobiusChair.nextGeneration(localHDFS, s"$basePath/inFoo") shouldBe "0002"
    }

    Scenario("nothing existing yet") {
      MobiusChair.nextGeneration(localHDFS, s"$basePath/inFoo/0001") shouldBe "0001"
    }
  }

  Feature("should clean up old generations") {
    Scenario("keep only 3 generations and one generation existing") {
      MobiusChair.cleanUpGenerations(localHDFS, s"$basePath/inFoo", 3) shouldBe Seq()
    }

    Scenario("keep only 3 generations and n < 3 generation existing") {
      MobiusChair.cleanUpGenerations(localHDFS, s"$basePath/inFooBar/0002", 3) shouldBe Seq()
    }

    Scenario("keep only 2 generations and 3 generation existing") {
      try {
        Files.createDirectories(Paths.get(s"$basePath/infoofoo/0006"))
        Files.createFile(Paths.get(s"$basePath/0006/_SUCCESS"))
      } catch {
        case _: Exception =>
      }

      val deletedFolders = MobiusChair.cleanUpGenerations(localHDFS, s"$basePath/infoofoo", 2)
      deletedFolders.length shouldBe 1
      deletedFolders.head should endWith(s"$basePath/infoofoo/0006")

      val tmpBasePath = new File(s"$basePath/infoofoo")
      tmpBasePath.list().toSeq should contain theSameElementsAs Seq("0007", "0008")
    }
  }

  Feature("should calculate next output folder") {
    Scenario("output folder not existing yet") {
      Files.deleteIfExists(Paths.get(s"$basePath/nonExistent/0001/0001"))
      Files.deleteIfExists(Paths.get(s"$basePath/nonExistent/0001"))
      Files.deleteIfExists(Paths.get(s"$basePath/nonExistent"))

      val actual = MobiusChair.outputPath(localHDFS, basePath, "nonExistent", "0001")

      actual shouldBe s"$basePath/nonExistent/0001/0001"
      Files.exists(Paths.get(s"$basePath/nonExistent/0001")) shouldBe true
    }

    Scenario("no generation existing yet") {
      val actual = MobiusChair.outputPath(localHDFS, basePath, "inFoo", "0001")

      actual shouldBe s"$basePath/inFoo/0001/0001"
    }

    Scenario("generations already existing") {
      val actual = MobiusChair.outputPath(localHDFS, basePath, "inFooBar", "0002")

      actual shouldBe s"$basePath/inFooBar/0002/0010"
    }
  }
}
