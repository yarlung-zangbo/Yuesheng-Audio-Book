pipeline {
    agent any

    stages {
        // eureka
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'cd code/eureka && mvn install && mvn spring-boot:run'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'cd code/eureka && mvn test'
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                echo 'Deploying....'
            }
        }
    }
}