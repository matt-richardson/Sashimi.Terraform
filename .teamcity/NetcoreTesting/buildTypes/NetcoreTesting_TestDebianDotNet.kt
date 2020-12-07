package NetcoreTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher

object NetcoreTesting_TestDebianDotNet : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Debian - DotNet"

    params {
        param("dotnet_runtime", "linux-x64")
    }

    features {
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
        equals("system.Octopus.OSPlatform", "Linux", "RQ_189")
        equals("system.Octopus.OS", "Debian", "RQ_186")
    }
})
