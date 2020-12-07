package NetFxWindowsTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nunit
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxWindowsTesting_TestWindows2008sql2008Net40noSashimiTests : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Windows 2008 - SQL 2008 (Net40, no Sashimi tests)"

    params {
        param("NUnitBin", "/opt/NUnit/NUnit-3.4.1/bin/nunit3-console.exe")
    }

    steps {
        nunit {
            name = "Run Sashimi Tests"
            id = "RUNNER_121"
            enabled = false
            nunitPath = "%teamcity.tool.NUnit.Console.DEFAULT%"
            includeTests = "SashimiTests/Sashimi.%CalamariFlavour%.Tests.dll"
        }
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
            id = "TRIGGER_27"
            buildType = "BuildAgentAutomation_Windows_WindowsServer2008sql2008"
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
        equals("system.Octopus.SqlVersion", "2008", "RQ_90")
        equals("system.Octopus.OSVersion", "2008", "RQ_95")
        equals("system.Octopus.OS", "Windows", "RQ_85")
    }
    
    disableSettings("BUILD_EXT_79", "RQ_193")
})
