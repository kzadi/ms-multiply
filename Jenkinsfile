pipeline{
    agent any

    stages{
        stage('Permission'){
            steps{
                sh 'chmod +x ./mvnw'
            }
        }
        stage('Build'){
            steps{
                sh './mvnw clean compile'
            }
        }
        stage('Tests'){
            steps{
                sh './mvnw test'
            }
        }
    }
}