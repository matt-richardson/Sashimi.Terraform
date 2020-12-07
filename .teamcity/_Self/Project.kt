package _Self

import _Self.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({

    buildType(ChainBuildAndTestAndPublishToFeedzIo)
    buildType(Build)

    template(MonoTesting)
    template(DotNetTesting)

    params {
        param("CalamariFlavour", "Terraform")
        param("RepositoryName", "Sashimi.%CalamariFlavour%")
        param("DefaultGitBranch", "main")
    }
    buildTypesOrder = arrayListOf(Build, ChainBuildAndTestAndPublishToFeedzIo)

    subProject(NetFxMonoNetFxTesting.Project)
    subProject(NetFxWindowsTesting.Project)
    subProject(NetcoreTesting.Project)
})
