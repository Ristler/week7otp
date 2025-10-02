pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKER_IMAGE = 'ristler/javafx_with_db2'
        DOCKER_TAG = 'latest'

        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:${env.PATH}"
        DOCKER_PATH = "/Applications/Docker.app/Contents/Resources/bin/docker"
    }

    stages {
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
                sh "${env.DOCKER_PATH} build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        sh "${env.DOCKER_PATH} push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }
    }

    post {
        always {
            junit(testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true)
            jacoco(execPattern: '**/target/jacoco.exec',
                   classPattern: '**/target/classes',
                   sourcePattern: '**/src/main/java',
                   inclusionPattern: '**/*.class',
                   exclusionPattern: '')
        }
    }
}