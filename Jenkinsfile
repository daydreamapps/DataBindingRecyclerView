pipeline {

    agent any

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

//            githubNotify description: 'This is a shorted example',  status: 'SUCCESS'
        }
    }
}