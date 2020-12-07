package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestFedoraMono442 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: Fedora - Mono 4.4.2"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_33"
            buildType = "Infrastructure_TestAgents_Fedora"
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
        equals("system.Octopus.OS", "Fedora", "RQ_103")
    }
})
