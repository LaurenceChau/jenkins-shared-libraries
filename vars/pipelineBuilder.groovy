def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    
    files = ["file-1", "file-2", "file-3"]
    pipeline {
        agent any
        stages {
            stage('1') {
                steps {
                    script {
                        stage("stage-1") {
                            echo 'stage-1'
                        }
                        stage ("stage-2") {
                            echo "stage-2"
                        }
                        
                    }
                }
            }       
        }
    }

}
