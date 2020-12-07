package NetFxWindowsTesting

import NetFxWindowsTesting.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("NetFxWindowsTesting")
    name = "NetFx (Windows) Testing"

    buildType(NetFxWindowsTesting_TestWindows2016sql2017)
    buildType(NetFxWindowsTesting_TestWindows2012sql2014)
    buildType(NetFxWindowsTesting_TestWindows2008r2sql2012noSashimiTests)
    buildType(NetFxWindowsTesting_TestWindows2008sql2008Net40noSashimiTests)
    buildType(NetFxWindowsTesting_TestWindows2012r2sql2016)
    buildType(NetFxWindowsTesting_TestWindows2019sql2019)

    params {
        param("VSTest_TestCaseFilter", "TestCategory!=macOs & TestCategory!=Nix & TestCategory != nixMacOS")
        param("dotnet_runtime", "netfx")
    }
})
