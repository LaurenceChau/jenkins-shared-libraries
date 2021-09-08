def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    
    def files = ["file-1", "file-2", "file-3"]
    def pipeline_name = "p1"
    pipeline {
        agent any
        stages {
            stage('1') {
                steps {
                    script {
                        if ( pipeline_name == "p1") parallel stage_deploy()
                        else if ( pipeline_name == "p2" ) stage_p2()
                    }
                    
                }
            }       
        }
    }

}
