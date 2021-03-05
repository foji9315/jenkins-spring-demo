pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Jacoco Report'){
        steps{
                jacoco exclusionPattern: '**/*Test*,**/model/**,**/security/SecurityConfiguration**,**/SpringFoxConfig**,**/*JwtRequestFilter*.class,**/*DBinit*.class,**/*UserPrincipalDetailsService*.class', maximumInstructionCoverage: '0'
            }
        }
        when {
            branch 'develop'
        }
        stage('Deploy DEV') {
            when {
                branch 'develop'
            }
            steps{
            input 'Deploy to Dev?'
                sh label: '', script: 'git remote -v'
                withCredentials([string(credentialsId: 'API_KEY', variable: 'API_KEY')]) {
                sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -Dheroku.appName=at-sso-ex')
                }
            }

        }

        stage('Prepare to QA') {
            when {
                branch 'develop'
            }
            steps {
                sh "mvn clean install"
                input 'Deploy to QA?'
            }
        }

        stage('Deploy QA') {
            when {
                branch 'develop'
            }
            steps {
                sh label: '', script: 'git remote -v'
                withCredentials([string(credentialsId: 'API_KEY', variable: 'API_KEY')]) {
                    sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -P qa')
                }
            }
        }
    }
}
