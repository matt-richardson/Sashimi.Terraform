package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell

object Build : BuildType({
    name = "Build"
    description = "Build the open source Sashimi package"

    artifactRules = "artifacts"

    params {
        param("env.HOMEDRIVE", "C:")
        param("env.HOMEPATH", """\users\administrator""")
        param("system.OctopusPackageVersion", "%build.number%")
        param("env.CALAMARI_GITHUB_AUTHUSERNAME", "OctopusGithubTester")
    }

    vcs {
        root(AbsoluteId("SharedGitHubVcsRoot"))

        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    steps {
        powerShell {
            name = "Build"
            scriptMode = script {
                content = """
                    ./build.ps1
                    
                    exit ${'$'}LASTEXITCODE
                """.trimIndent()
            }
        }
    }

    features {
        freeDiskSpace {
            requiredSpace = "1gb"
            failBuild = false
        }
        commitStatusPublisher {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:3ff28a52-158e-4221-ad49-1075f1762644"
                }
            }
        }
        swabra {
            filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
            lockingProcesses = Swabra.LockingProcessPolicy.KILL
        }
    }

    requirements {
        equals("system.Octopus.OS", "Windows")
        exists("DotNetFrameworkTargetingPack4.7.2_Path")
        exists("DotNetCoreSDK3.1_Path")
    }
    
    disableSettings("RUNNER_168")
})
