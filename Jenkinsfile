pipeline{
    agent any

    stages{
        stage('clean'){
            steps{
                sh './mvnw clean'
            }
        }
        stage('package'){
            steps{
                sh './mvnw package'
            }
        }
    }
}