package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestUbuntu1604ltsMono461 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: Ubuntu 16.04 LTS - Mono 4.6.1"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_45"
            buildType = "BuildAgentAutomation_Linux_Ubuntu1604Mono461"
            successfulOnly = true
            branchFilter = ""
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_97"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:3ff28a52-158e-4221-ad49-1075f1762644"
                }
            }
        }
    }

    requirements {
        equals("system.MonoVersion", "4.6.1", "RQ_74")
        equals("system.Octopus.OS", "Ubuntu", "RQ_96")
        equals("system.Octopus.OSVersion", "16", "RQ_106")
    }
})
