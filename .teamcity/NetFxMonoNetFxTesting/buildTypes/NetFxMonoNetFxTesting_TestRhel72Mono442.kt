package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestRhel72Mono442 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: RHEL 7.2 - Mono 4.4.2"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_8"
            buildType = "BuildAgentAutomation_Rhel72"
            successfulOnly = true
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
        equals("system.Octopus.OS", "RHEL", "RQ_100")
        equals("system.Octopus.OSVersion", "7.2", "RQ_110")
    }
})
