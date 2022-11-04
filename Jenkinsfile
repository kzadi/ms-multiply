pipeline{
    agent any

    stages{
        stage('permission'){
            steps{
                sh 'chmod +x ./mvnw'
            }
        }
        stage('clean'){
            steps{
                sh './mvnw clean'
            }
        }
        stage('install'){
            steps{
                sh './mvnw install'
            }
        }
    }
}