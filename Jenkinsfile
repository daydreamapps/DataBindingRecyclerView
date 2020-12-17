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
                        sh './gradlew :bindingrecycler:testDebugUnitTest --stacktrace'
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

            junit "**/app/build/test-results/testDebugUnitTest/*.xml"

            // execute & publish coverage report
            jacoco classPattern: 'tmp/kotlin-classes/, app/build/tmp/kotlin-classes/debug'
        }
    }
}