pipeline {
  agent any
  stages {
    stage('checkout') {
      parallel {
        stage('checkout') {
          steps {
            sh 'git clone https://github.com/successanil/RxJavaBasicDemo.git'
          }
        }

        stage('build') {
          steps {
            sh './gradlew build'
          }
        }

      }
    }

  }
}