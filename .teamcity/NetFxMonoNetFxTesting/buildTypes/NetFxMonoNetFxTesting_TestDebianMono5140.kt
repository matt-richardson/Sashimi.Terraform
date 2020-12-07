package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestDebianMono5140 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: Debian - Mono 5.14.0"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_32"
            buildType = "Infrastructure_TestAgents_Debian9"
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
        equals("system.Octopus.OS", "Debian", "RQ_104")
        equals("system.Octopus.AgentType", "Debian911", "RQ_341")
        equals("system.Octopus.Purpose", "Test", "RQ_342")
    }
    
    disableSettings("RQ_104")
})
