package com.github.meandor

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, LocalFileSystem}
import org.scalatest.{FeatureSpec, Matchers}

class MobiusChairTest extends FeatureSpec with Matchers {
  val basePath = "src/test/resources/filesystem"
  val localHDFS: LocalFileSystem = FileSystem.getLocal(new Configuration())

  feature("get latest generation in path") {
    scenario("nothing existing yet") {
      MobiusChair.latestGeneration(localHDFS, s"$basePath/inFoo/0001") shouldBe "0000"
    }

    scenario("n generations existing") {
      MobiusChair.latestGeneration(localHDFS, s"$basePath/inFooBar/0002") shouldBe "0009"
    }
  }
}