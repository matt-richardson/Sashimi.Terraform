package NetFxMonoNetFxTesting.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher

object NetFxMonoNetFxTesting_TestMacOsx10152Mono5140 : BuildType({
    templates(_Self.buildTypes.MonoTesting)
    name = "Test: Mac OSX 10.15.2 - Mono 5.14.0"

    params {
        param("VSTest_TestCaseFilter", "TestCategory != Windows & TestCategory != Nix")
        param("MonoBin", "/Library/Frameworks/Mono.framework/Versions/Current/Commands/mono")
        param("NUnit_TestCaseFilter", "cat != Windows && cat != Nix")
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
        equals("teamcity.agent.jvm.os.name", "Mac OS X", "RQ_68")
        equals("system.MonoVersion", "5.14.0", "RQ_66")
    }
})
