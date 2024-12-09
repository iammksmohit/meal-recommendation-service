pipeline {
    agent any
    stages {
        stage('Build Step') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Dockerizing Application') {
            steps {
                sh 'docker build -t meal-recommendation-service .'
            }
        }
         stage('Deploy') {
                    steps {
                        sh 'docker run -p 8080:8080 meal-recommendation-service'
                    }
            }
    }
}
