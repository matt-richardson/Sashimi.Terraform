package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

object NetFxMonoNetFxTesting_TestSuseLes12sp2Mono460 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: SUSE  LES 12 SP2 - Mono 4.6.0"
    paused = true

    triggers {
        finishBuildTrigger {
            id = "TRIGGER_47"
            buildType = "Infrastructure_TestAgents_SuseLinuxEnterpriseServer12"
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
        equals("system.MonoVersion", "4.6.0", "RQ_78")
        equals("system.Octopus.OS", "SLES", "RQ_99")
        equals("system.Octopus.OSVersion", "12", "RQ_109")
    }
})
