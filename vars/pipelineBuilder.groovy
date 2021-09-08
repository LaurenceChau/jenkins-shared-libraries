def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    
    pipeline {
        agent {
            kubernetes {
                inheritFrom 'build-tools'
                defaultContainer 'maven'
            }
        }
        stages {
            stage('PreCheck') {
                steps {
                    echo 'PreCheck...'
                }
            }
            stage('Scan Code') {
                when {
                    branch 'main'
                }
                failFast true 
                parallel {
                    stage('SonarIQ Scan') {
                        steps {
                            echo 'SonarIQ Scan...'
                        }
                    }
                    stage('Checkmarx Scan') {
                        steps {
                            echo 'Checkmarx Scan'
                        }
                    }
                    stage('SonarQube Scan') {
                        steps {
                            echo 'SonarQube Scan'
                        }
                    }
                    stage('Branch C') {
                        stages {
                            stage('Nested 1') {
                                steps {
                                    echo "In stage Nested 1 within Branch C"
                                }
                            }
                            stage('Nested 2') {
                                steps {
                                    echo "In stage Nested 2 within Branch C"
                                }
                            }
                        }
                    }
                }
            }
            stage('Check Scan Result') {
                steps {
                    echo 'Check Scan Result..'
                }
            }
            stage('Build') {
                steps {
                    echo 'Building..'
                }
            }
            stage('Unit Test') {
                steps {
                    echo 'Testing..'
                    sh 'echo hello world'
                }
            }
            stage('Deploy Input') {
                input {
                    message "Deploy To DIT?"
                    ok "Yes, Let's go"
                    submitter "admin"
                    parameters {
                        string(name: 'GCP Site\'s IP', defaultValue: '10.91.40.76', description: 'GCP Site\'s IP')
                    }
                }
                stages {
                    stage('Deploy') {
                        failFast true
                        parallel {
                            stage('Deploy Config') {
                                steps {
                                    echo 'Deploy Config..'
                                }
                            }
                            stage('Deploy IHS') {
                                steps {
                                    echo 'Deploy IHS..'
                                }
                            }
                            stage('Deploy CXP') {
                                steps {
                                    echo 'Deploy CXP..'
                                }
                            }
                        }
                    }
                }
            }
            stage('Integration Test') {
                steps {
                    echo 'Integration Test..'
                }
            }
        }
    }
}
