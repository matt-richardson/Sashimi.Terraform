package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestOpenSUSE151Mono5101 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: openSUSE 15.1 - Mono 5.10.1"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_38"
            buildType = "Infrastructure_TestAgents_OpenSUSE151"
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
        equals("system.Octopus.OS", "openSUSE", "RQ_101")
        equals("system.Octopus.OSVersion", "15.1", "RQ_111")
        equals("system.MonoVersion", "5.10.1.47", "RQ_69")
    }
})
