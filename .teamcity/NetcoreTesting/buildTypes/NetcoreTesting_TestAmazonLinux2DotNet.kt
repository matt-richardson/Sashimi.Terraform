package NetcoreTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher

object NetcoreTesting_TestAmazonLinux2DotNet : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Amazon Linux 2 - DotNet"

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
        equals("system.Octopus.OS", "AmazonLinux", "RQ_186")
        equals("system.Octopus.OSVersion", "2", "RQ_190")
        exists("system.Octopus.DotnetSdk3.1", "RQ_337")
        equals("system.Octopus.Purpose", "Test", "RQ_338")
        equals("system.Octopus.AgentType", "AmazonLinux2", "RQ_339")
    }
    
    disableSettings("RQ_186", "RQ_189", "RQ_190", "RQ_193")
})
