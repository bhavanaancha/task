pipeline {
agent any

environment {
SONAR_TOKEN = credentials('GYM_SONAR_TOKEN')
TOMCAT_CREDENTIALS = credentials('TOMCAT_CREDENTIALS') // Use the ID of the Jenkins credential containing Tomcat credentials

}

stages {
stage('Build, Test, and Generate Coverage') {
steps {
bat "mvn clean verify"
bat "mvn jacoco:prepare-agent"
}
}
stage('SonarQube Analysis') {
steps {
bat "mvn clean verify sonar:sonar -Dsonar.projectKey=basic -Dsonar.projectName='basic' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_df0989bb2f55c48661bc7b423802cc98a0a3fcce"
}
post{
success{
archiveArtifacts artifacts:'**/target/*.war'
}
}

}

stage('Deploy to Tomcat') {
steps {
// Deploy the war file to Tomcat
deploy adapters: [tomcat9(credentialsId: 'TOMCAT_CREDENTIALS', path: '', url: 'http://localhost:9005')], contextPath: 'assessment-jenkins-task', war: '**/*.war'
}
}
}
}

has context menu