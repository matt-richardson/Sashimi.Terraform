package NetFxWindowsTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nunit
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxWindowsTesting_TestWindows2008r2sql2012noSashimiTests : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Windows 2008 R2 - SQL 2012 (no Sashimi tests)"

    params {
        param("NUnitExcludes", "Windows,macOS")
    }

    steps {
        nunit {
            name = "Run Calamari Tests"
            id = "RUNNER_136"
            nunitPath = "%teamcity.tool.NUnit.Console.DEFAULT%"
            includeTests = "CalamariTests/Calamari.%CalamariFlavour%.Tests.dll"
        }
        stepsOrder = arrayListOf("RUNNER_121", "RUNNER_136")
    }

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_40"
            buildType = "BuildAgentAutomation_Windows_WindowsServer2008r2sql2012"
            successfulOnly = true
        }
    }

    features {
        feature {
            id = "BUILD_EXT_8"
            type = "xml-report-plugin"
            param("xmlReportParsing.reportType", "nunit")
            param("xmlReportParsing.reportDirs", "TestResult.xml")
            param("xmlReportParsing.verboseOutput", "true")
        }
        commitStatusPublisher {
            id = "BUILD_EXT_89"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:3ff28a52-158e-4221-ad49-1075f1762644"
                }
            }
        }
    }

    requirements {
        equals("system.Octopus.SqlVersion", "2012", "RQ_89")
        equals("system.Octopus.OSVersion", "2008R2", "RQ_94")
        equals("system.Octopus.OS", "Windows", "RQ_85")
    }
    
    disableSettings("BUILD_EXT_79", "RUNNER_121")
})
