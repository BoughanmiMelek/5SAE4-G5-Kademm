pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.3:8081"
        NEXUS_REPOSITORY = "maven-snapshots"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
    }
    stages {
        stage('Building') {
            steps {
                script {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Unit Testing') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST*.xml'
                }
            }
        }
        stage('Sonarqube Analysis') {
            steps {
                script {
                    jacoco()
                    withSonarQubeEnv('sonar') {
                        sh "mvn verify sonar:sonar"
                    }
                }
            }
        }

        stage("Quality Gate") {
            steps {
                script {
                    sleep(10)
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Building jar') {
            steps {
                script {
                    sh "mvn clean package"
                }
            }
        }


        stage("publish to nexus") {
            steps {
                script {
                    artifactPath = "target/kaddem-0.0.1-SNAPSHOT.jar";

                    echo "*** File: ${artifactPath}, group: tn.esprit, packaging: jar, version 0.0.1-SNAPSHOT ***";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: 'tn.esprit',
                            version: '0.0.1-SNAPSHOT',
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: 'kaddem',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );

                }
            }
        }


        stage('Email Notification') {
            steps {
                script {
                    mail bcc: '', body: '''Hi,
                        Welcome to jenkins email alerts.
                        Thanks,
                        Malek''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'melekboughanmi2023@gmail.com'
                }
            }
        }


        stage('Build And Deploy Docker Image') {
            steps {
                script {
                    echo "deploying the application"
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker build -t melekboug/kaddem:1.0 ."
                        sh "docker push melekboug/kaddem:1.0"
                    }
                }
            }
        }

       stage('Docker Compose') {
            steps{
                script{
                    sh "docker compose up -d"
                }
            }
       }
    }
}