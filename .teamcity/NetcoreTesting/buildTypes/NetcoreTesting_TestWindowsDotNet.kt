package NetcoreTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher

object NetcoreTesting_TestWindowsDotNet : BuildType({
    templates(_Self.buildTypes.DotNetTesting)
    name = "Test: Windows - DotNet"

    params {
        param("VSTest_TestCaseFilter", "TestCategory!=macOs & TestCategory!=Nix & TestCategory!=PlatformAgnostic & TestCategory != nixMacOS")
        param("dotnet_runtime", "win-x64")
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
        equals("system.Octopus.OS", "Windows", "RQ_186")
        doesNotEqual("system.Octopus.OSVersion", "2008", "RQ_190")
        doesNotEqual("system.Octopus.OSVersion", "2008R2", "RQ_202")
        equals("system.Octopus.Purpose", "Test", "RQ_234")
        exists("DotNetFrameworkTargetingPack4.0_Path", "RQ_144")
    }
})
