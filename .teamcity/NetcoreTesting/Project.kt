package NetcoreTesting

import NetcoreTesting.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("NetcoreTesting")
    name = "Netcore testing"

    buildType(NetcoreTesting_TestAmazonLinux2DotNet)
    buildType(NetcoreTesting_TestWindowsDotNet)
    buildType(NetcoreTesting_TestUbuntu1604ltsDotNetMono)
    buildType(NetcoreTesting_TestDebianDotNet)
    buildType(NetcoreTesting_TestUbuntu1604ltsDotNetMono_2)
    buildType(NetcoreTesting_TestCentos7DotNet)
    buildType(NetcoreTesting_TestOpenSUSE151DotNet)
    buildType(NetcoreTesting_TestSuse12DotNet)
    buildType(NetcoreTesting_TestMacOsx10152DotNet)
    buildType(NetcoreTesting_TestFedoraDotNet)
    buildType(NetcoreTesting_TestRhel72DotNet)
    buildType(NetcoreTesting_TestUbuntu1804lts)

    params {
        param("VSTest_TestCaseFilter", "TestCategory != Windows")
        param("dotnet.cli.test.reporting", "off")
        param("framework", "netcoreapp2.0")
    }
    buildTypesOrder = arrayListOf(NetcoreTesting_TestUbuntu1604ltsDotNetMono, NetcoreTesting_TestUbuntu1604ltsDotNetMono_2, NetcoreTesting_TestUbuntu1804lts, NetcoreTesting_TestOpenSUSE151DotNet, NetcoreTesting_TestSuse12DotNet, NetcoreTesting_TestCentos7DotNet, NetcoreTesting_TestAmazonLinux2DotNet, NetcoreTesting_TestWindowsDotNet, NetcoreTesting_TestFedoraDotNet, NetcoreTesting_TestDebianDotNet, NetcoreTesting_TestRhel72DotNet, NetcoreTesting_TestMacOsx10152DotNet)
})
