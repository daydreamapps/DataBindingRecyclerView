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
//                        sh './gradlew :bindingrecycler:lintDebug'
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
            // archive release apk
            archiveArtifacts(
                    allowEmptyArchive: true,
                    artifacts: 'bindingrecycler/build/outputs/aar/bindingrecycler-release.aar'
            )

            junit "**/bindingrecycler/build/test-results/testReleaseUnitTest/*.xml"

            // execute & publish coverage report

            jacoco(
                    classPattern: '**/classes/kotlin/main',
                    sourcePattern: '**/src/main/kotlin',
                    sourceInclusionPattern: '**/*.kt'
            )

//            step([
//                    $class                : 'JacocoPublisher',
//                    execPattern           : 'bindingrecycler/build/jacoco/testReleaseUnitTest.exec',
//                    classPattern          : 'tmp/kotlin-classes/',
//                    sourceExclusionPattern: 'generated/**.*',
//                    sourceInclusionPattern: '**/*.kt',
//                    exclusionPattern      : '**/*$*.*' +
//                            '**/*.xml' +
//                            '**/*.json' +
//                            '**/R$*.class' +
//                            '**/BuildConfig.*' +
//                            '**/Manifest*.*' +
//                            '**/*Test*.*' +
//                            '**/com/example/databinding/*' +
//                            '**/com/example/generated/callback/*' +
//                            '**/android/databinding/*' +
//                            '**/androidx/databinding/*' +
//                            '**/di/module/*' +
//                            '**/*MapperImpl*.*' +
//                            '**/*$ViewInjector*.*' +
//                            '**/*$ViewBinder*.*' +
//                            '**/BuildConfig.*' +
//                            '**/*Component*.*' +
//                            '**/*BR*.*' +
//                            '**/Manifest*.*' +
//                            '**/*$Lambda$*.*' +
//                            '**/*Companion*.*' +
//                            '**/*Module.*' +
//                            '**/*Dagger*.*' +
//                            '**/*MembersInjector*.*' +
//                            '**/*_Factory*.*' +
//                            '**/*_Provide*Factory*.*' +
//                            '**/*Extensions*.*' +
//                            '**/*$Result.*' +
//                            '**/*$Result$*.*' +
//                            '**/*Activity.*' +
//                            '**/*Fragment.*' +
//                            '**/*View.*' +
//                            '**/*Args.*' +
//                            '**/*Adapter.*' +
//                            '**/*Dao.*'
//            ])
//                  sourcePattern: 'src/main/java',
//                  exclusionPattern: 'src/test*'
//            jacoco classPattern: 'tmp/kotlin-classes/, app/build/tmp/kotlin-classes/release'
        }
    }
}