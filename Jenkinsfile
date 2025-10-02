pipeline {
    agent any
    tools {
        maven 'Maven 3.9.11'
    }

    environment {
        PATH = "/Applications/Docker.app/Contents/Resources/bin:${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
        DOCKER_IMAGE = 'ristler/javafx_with_db2'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Setup Maven') {
            steps {
                script {
                    def mvnHome = tool name: 'Maven 3.9.11', type: 'maven'
                    env.PATH = "${mvnHome}/bin:${env.PATH}"
                }
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Ristler/week7otp.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: env.DOCKERHUB_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh """
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }
    }

    post {
        always {
            junit(testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true)
            jacoco(
                execPattern: '**/target/jacoco.exec',
                classPattern: '**/target/classes',
                sourcePattern: '**/src/main/java',
                inclusionPattern: '**/*.class',
                exclusionPattern: ''
            )
        }
    }
}