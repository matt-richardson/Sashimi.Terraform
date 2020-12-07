package NetFxWindowsTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxWindowsTesting_TestWindows2012sql2014 : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Windows 2012 - SQL 2014"

    params {
        param("NUnitExcludes", "Windows,macOS")
    }

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_48"
            buildType = "BuildAgentAutomation_Windows_WindowsServer2012sql2014"
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
        equals("system.Octopus.SqlVersion", "2014", "RQ_88")
        equals("system.Octopus.OSVersion", "2012", "RQ_93")
        equals("system.Octopus.OS", "Windows", "RQ_85")
    }
})
