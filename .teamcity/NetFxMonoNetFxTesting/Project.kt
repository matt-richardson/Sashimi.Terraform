package NetFxMonoNetFxTesting

import NetFxMonoNetFxTesting.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("NetFxMonoNetFxTesting")
    name = "NetFx (Mono NetFx) testing"

    buildType(NetFxMonoNetFxTesting_TestAmazonLinux2Mono442)
    buildType(NetFxMonoNetFxTesting_TestSuseLes12sp2Mono460)
    buildType(NetFxMonoNetFxTesting_TestOpenSUSE151Mono5101)
    buildType(NetFxMonoNetFxTesting_TestUbuntu1604ltsMono461)
    buildType(NetFxMonoNetFxTesting_TestRhel72Mono442)
    buildType(NetFxMonoNetFxTesting_TestMacOsx10152Mono5140)
    buildType(NetFxMonoNetFxTesting_TestDebianMono5140)
    buildType(NetFxMonoNetFxTesting_TestFedoraMono442)
    buildType(NetFxMonoNetFxTesting_TestCentos7Mono442)

    params {
        param("VSTest_TestCaseFilter", "TestCategory != Windows & TestCategory != MacOS")
        param("dotnet_runtime", "netfx")
        param("NUnit_TestCaseFilter", "cat != Windows && cat != macOS")
    }
})
