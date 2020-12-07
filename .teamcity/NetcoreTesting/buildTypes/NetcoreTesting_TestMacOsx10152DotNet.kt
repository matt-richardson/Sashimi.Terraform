package NetcoreTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher

object NetcoreTesting_TestMacOsx10152DotNet : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Mac OSX 10.15.2- DotNet"

    params {
        param("dotnet_runtime", "osx-x64")
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
        equals("system.Octopus.OS", "Ubuntu", "RQ_186")
        equals("system.Octopus.OSPlatform", "Linux", "RQ_189")
        equals("system.Octopus.OSVersion", "14", "RQ_190")
        equals("teamcity.agent.jvm.os.name", "Mac OS X", "RQ_201")
    }
    
    disableSettings("RQ_186", "RQ_189", "RQ_190", "RQ_193")
})
