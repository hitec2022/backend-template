 pipeline {
     agent any
     stages {
         stage('BUILD') {
             steps {
                 sh 'mvn package -DskipTests'
             }
         }
         stage('STOP App') {
             steps {
                 script {
                     try {
                         sh 'docker stop blogboard'
                         sh 'docker rm blogboard' 
                     } catch (err) {
                         echo err.getMessage()
                         echo 'Stop App Failed'
                     }
                 }
             }
         }
         stage('Dockernizer') {
             steps {
                 sh 'docker build --build-arg APP_FILE=target/board*.jar -t blogboard:0.0.1 .'
             }
         }
         stage('Deployment') {
             steps {
                 sh 'docker run -d --name blogboard blogboard:0.0.1'
             }
         }
     }
 }