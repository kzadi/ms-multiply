pipeline{
    agent any

    stages{
        stage('permission'){
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