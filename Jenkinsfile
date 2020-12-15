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
                        sh './gradlew :bindingrecycler:lintDebug'
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
                    artifacts: 'app/build/outputs/apk/production/release/*.apk'
            )
        }
    }
}