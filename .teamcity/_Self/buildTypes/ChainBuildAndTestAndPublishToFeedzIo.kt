package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPublish
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object ChainBuildAndTestAndPublishToFeedzIo : BuildType({
    name = "Chain: Build and test and publish to Feedz.io"

    buildNumberPattern = "${Build.depParamRefs.buildNumber}"

    vcs {
        root(AbsoluteId("SharedGitHubVcsRoot"))

        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        nuGetPublish {
            name = "Nuget Publish"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            packages = "*.nupkg"
            serverUrl = "%InternalNuget.OctopusDependeciesFeedUrl%"
            apiKey = "credentialsJSON:eee0f4e4-8dea-4c0a-b6e4-5cb7f70eca3d"
            args = "-Timeout 1200"
        }
    }

    triggers {
        vcs {
            branchFilter = """
                ## We actually want to publish all builds
                +:refs/tags/*
                +:<default>
                +:refs/heads/*
                -:*enh-calamariplatforms
            """.trimIndent()
        }
    }

    features {
        commitStatusPublisher {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:3ff28a52-158e-4221-ad49-1075f1762644"
                }
            }
        }
    }

    dependencies {
        dependency(Build) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = "*.nupkg"
            }
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestAmazonLinux2Mono442) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestCentos7Mono442) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestDebianMono5140) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestFedoraMono442) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestMacOsx10152Mono5140) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestOpenSUSE151Mono5101) {
            onDependencyCancel = FailureAction.ADD_PROBLEM
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestRhel72Mono442) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxMonoNetFxTesting.buildTypes.NetFxMonoNetFxTesting_TestUbuntu1604ltsMono461) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2008r2sql2012noSashimiTests) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2008sql2008Net40noSashimiTests) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2012r2sql2016) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2012sql2014) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2016sql2017) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetFxWindowsTesting.buildTypes.NetFxWindowsTesting_TestWindows2019sql2019) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestAmazonLinux2DotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestCentos7DotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestDebianDotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestFedoraDotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestMacOsx10152DotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestOpenSUSE151DotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestRhel72DotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestUbuntu1604ltsDotNetMono) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestUbuntu1604ltsDotNetMono_2) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestUbuntu1804lts) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
        snapshot(NetcoreTesting.buildTypes.NetcoreTesting_TestWindowsDotNet) {
            onDependencyFailure = FailureAction.CANCEL
            onDependencyCancel = FailureAction.CANCEL
        }
    }

    requirements {
        equals("system.Octopus.Purpose", "Build")
    }
})
