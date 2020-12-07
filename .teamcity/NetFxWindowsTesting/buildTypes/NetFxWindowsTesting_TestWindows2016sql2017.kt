package NetFxWindowsTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxWindowsTesting_TestWindows2016sql2017 : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Windows 2016 - SQL 2017"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_50"
            buildType = "Infrastructure_TestAgents_WindowsServer2016sql2017"
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
        equals("system.Octopus.OSVersion", "2016", "RQ_91")
        equals("system.Octopus.SqlVersion", "2017", "RQ_86")
        equals("system.Octopus.OS", "Windows", "RQ_85")
    }
})
