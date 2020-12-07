package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange

object DotNetTesting : Template({
    name = "DotNet testing"

    artifactRules = "artifacts"
    buildNumberPattern = "${Build.depParamRefs.buildNumber}"

    params {
        param("system.OctopusPackageVersion", "%build.number%")
        param("ExtraPackageSources", "")
    }

    vcs {
        root(AbsoluteId("SharedGitHubVcsRoot"))

        checkoutMode = CheckoutMode.MANUAL
        cleanCheckout = true
    }

    steps {
        script {
            name = "Run Sashimi Tests"
            id = "RUNNER_121"
            workingDir = "SashimiTests"
            scriptContent = """dotnet vstest Sashimi.%CalamariFlavour%.Tests.dll /TestCaseFilter:"%VSTest_TestCaseFilter%" /logger:trx"""
        }
        script {
            name = "Run Calamari Tests"
            id = "RUNNER_136"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            workingDir = "CalamariTests"
            scriptContent = """dotnet vstest Calamari.*.Tests.dll /TestCaseFilter:"%VSTest_TestCaseFilter%" /logger:trx"""
        }
    }

    failureConditions {
        executionTimeoutMin = 60
        failOnMetricChange {
            id = "BUILD_EXT_79"
            metric = BuildFailureOnMetric.MetricType.TEST_COUNT
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.LESS
            compareTo = value()
        }
        failOnMetricChange {
            id = "BUILD_EXT_113"
            metric = BuildFailureOnMetric.MetricType.TEST_COUNT
            threshold = 20
            units = BuildFailureOnMetric.MetricUnit.PERCENTS
            comparison = BuildFailureOnMetric.MetricComparison.LESS
            compareTo = build {
                buildRule = lastSuccessful()
            }
        }
    }

    features {
        feature {
            id = "BUILD_EXT_1"
            type = "xml-report-plugin"
            param("xmlReportParsing.reportType", "trx")
            param("xmlReportParsing.reportDirs", "**/*.trx")
        }
        commitStatusPublisher {
            id = "BUILD_EXT_89"
            vcsRootExtId = "OctopusDeploy_Calamari"
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
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_10"
                cleanDestination = true
                artifactRules = "Sashimi.%CalamariFlavour%.Tests.zip!**=>SashimiTests"
            }
            artifacts {
                id = "ARTIFACT_DEPENDENCY_75"
                cleanDestination = true
                artifactRules = "Calamari.%CalamariFlavour%.Tests.zip!%dotnet_runtime%/**=>CalamariTests"
            }
            artifacts {
                id = "ARTIFACT_DEPENDENCY_76"
                cleanDestination = true
                artifactRules = "Calamari.%CalamariFlavour%.zip!%dotnet_runtime%/**=>CalamariBinaries"
            }
        }
    }

    requirements {
        exists("system.Octopus.DotnetSdk2.2", "RQ_193")
    }
})
