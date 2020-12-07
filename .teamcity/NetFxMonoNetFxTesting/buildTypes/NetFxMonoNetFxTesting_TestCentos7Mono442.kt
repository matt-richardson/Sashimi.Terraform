package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestCentos7Mono442 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: Centos 7 - Mono 4.4.2"

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_23"
            buildType = "Infrastructure_TestAgents_CentOS7"
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
        equals("system.Octopus.OS", "CentOS", "RQ_105")
        equals("system.Octopus.Purpose", "Test", "RQ_122")
    }
})
