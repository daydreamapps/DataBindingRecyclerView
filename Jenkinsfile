pipeline {

    agent any

    triggers {
        githubPush()
        pollSCM('') // Enabling being build on Push
    }

    stages {
        stage('Setup') {
            steps {
                sh 'echo "Setup"'
                sh 'chmod +x gradlew'
                sh './gradlew :bindingrecycler:clean --stacktrace'
            }
        }

        stage('Assemble') {
            steps {
                sh 'echo "Assemble"'
                sh './gradlew :bindingrecycler:assemble --stacktrace'
            }
        }

        stage('Lint & Unit Test') {
            parallel {

                stage('Check Style') {
                    steps {
                        sh 'echo "Check Style"'
                        sh './gradlew :bindingrecycler:lintRelease'
                        androidLint()
                    }
                }

                stage('Unit Tests') {
                    steps {
                        sh 'echo "Unit Tests"'
                        sh './gradlew :bindingrecycler:testReleaseUnitTest --stacktrace'
                        sh './gradlew :bindingrecycler:testReleaseUnitTestCoverage --stacktrace'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts(
                    allowEmptyArchive: true,
                    artifacts: 'bindingrecycler/build/outputs/aar/bindingrecycler-release.aar'
            )

            junit "**/bindingrecycler/build/test-results/testReleaseUnitTest/*.xml"

            jacoco classPattern: 'tmp/kotlin-classes/, bindingrecycler/build/tmp/kotlin-classes/release',
                    execPattern: 'bindingrecycler/build/jacoco/**.exec',
                    sourceExclusionPattern: 'generated/**.*',
                    sourceInclusionPattern: '**/*.kt',
                    exclusionPattern: '**/*$*.*' +
                            '**/*.xml' +
                            '**/*.json' +
                            '**/R$*.class' +
                            '**/BuildConfig.*' +
                            '**/Manifest*.*' +
                            '**/*Test*.*' +
                            '**/com/example/databinding/*' +
                            '**/com/example/generated/callback/*' +
                            '**/android/databinding/*' +
                            '**/androidx/databinding/*' +
                            '**/di/module/*' +
                            '**/*MapperImpl*.*' +
                            '**/*$ViewInjector*.*' +
                            '**/*$ViewBinder*.*' +
                            '**/BuildConfig.*' +
                            '**/*Component*.*' +
                            '**/*BR*.*' +
                            '**/Manifest*.*' +
                            '**/*$Lambda$*.*' +
                            '**/*Companion*.*' +
                            '**/*Module.*' +
                            '**/*Dagger*.*' +
                            '**/*MembersInjector*.*' +
                            '**/*_Factory*.*' +
                            '**/*_Provide*Factory*.*' +
                            '**/*Extensions*.*' +
                            '**/*$Result.*' +
                            '**/*$Result$*.*' +
                            '**/*Activity.*' +
                            '**/*Fragment.*' +
                            '**/*View.*' +
                            '**/*Args.*' +
                            '**/*Adapter.*' +
                            '**/*Dao.*'

//            sh "curl -s https://codecov.io/bash | bash"

//            new GroovyShell().parse(new File('codecov.groovy')).call(null)

//            sh "curl -s https://codecov.io/bash | bash -f ./bindingrecycler/build/reports/jacoco/testReleaseUnitTestCoverage/testReleaseUnitTestCoverage.xml"
//            sh "curl -s https://codecov.io/bash | bash -f ./bindingrecycler/build/reports/jacoco/testReleaseUnitTestCoverage/testReleaseUnitTestCoverage.xml -s - -K"
//            sh "curl -s https://codecov.io/bash | bash -s -- -c -F aFlag -s - -K -f ./bindingrecycler/build/reports/jacoco/testReleaseUnitTestCoverage/testReleaseUnitTestCoverage.xml"

//            sh "curl -Ls https://coverage.codacy.com/get.sh | bash jacoco*.xml"
            sh "curl -Ls https://coverage.codacy.com/get.sh | bash ./bindingrecycler/build/reports/jacoco/testReleaseUnitTestCoverage/testReleaseUnitTestCoverage.xml"
//            sh "curl -Ls https://coverage.codacy.com/get.sh | bash testReleaseUnitTestCoverage.xml"
//            bash <(curl -Ls https://coverage.codacy.com/get.sh) report
//            script {
//                sh 'curl -s https://codecov.io/bash | bash -f ./bindingrecycler/build/reports/jacoco/testReleaseUnitTestCoverage/testReleaseUnitTestCoverage.xml'
//            }
        }
    }
}
