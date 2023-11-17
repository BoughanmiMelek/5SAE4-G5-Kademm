pipeline {
    agent any
    environment {
        PROJECT_DIR = '5SAE4-G5-Kademm'
    }
    stages {
        stage("GIT") {
            steps {
                script {
                    if (!fileExists(env.PROJECT_DIR)) {
                        sh "git clone -b MelekBoughanmi-5SAE4-G5 https://BoughanmiMelek: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@github.com/BoughanmiMelek/5SAE4-G5-Kademm.git ${env.PROJECT_DIR}"
                    } else {
                        echo "Le répertoire '${env.PROJECT_DIR}' existe déjà. Mise à jour en cours..."
                        dir(env.PROJECT_DIR) {
                            sh "git checkout MelekBoughanmi-5SAE4-G5"
                            sh "git pull origin MelekBoughanmi-5SAE4-G5"
                    sh 'mvn clean compile'
                }
            }
        }
            }
        }


        stage('Maven Build') {
            steps {
                dir(env.PROJECT_DIR) {
                    sh 'mvn clean install'
                    echo 'Maven build is done'
                }
            }
        }
           stage('JUnit/Mockito Tests') {
            steps {
                dir(env.PROJECT_DIR) {
                    sh 'mvn test -Dtest=tn.esprit.spring.kaddem.EtudiantServiceImplTest'
                }
            }
        }

         stage('SonarQube Analysis') {
                    steps {
                        dir(env.PROJECT_DIR) {
                            sh 'mvn clean package sonar:sonar -Dsonar.login=admin -Dsonar.password=password -Dmaven.test.skip=true'
                        }
                    }
                }

           stage('Nexus Deployment') {
            steps {
                dir(env.PROJECT_DIR) {
                    script{
                    artifactPath = "target/kaddem-1.0.jar";

                    nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: '192.168.33.3:8081',
                            groupId: 'tn.esprit',
                            version: '1.0',
                            repository: 'maven-releases',
                            credentialsId: 'nexus',
                            artifacts: [
                                    [artifactId: 'kaddem',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );
                    }
                }
            }
        }



        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker image...'
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker build -t melekboug/kaddem:1.0 ."
                        }
            }
        }

        stage('Upload to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                                        sh "echo $PWD | docker login -u $USER --password-stdin"
                                        sh "docker push melekboug/kaddem:1.0"
                 }
            }
        }

        stage('Run Docker Compose') {
            steps {
                echo 'Running Docker Compose...'
                sh 'docker compose -f /var/lib/jenkins/workspace/pipelineDevo/5SAE4-G5-Kademm/docker-compose.yml up -d'
            }
        }

    }
}

