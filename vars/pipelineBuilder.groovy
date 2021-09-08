def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    
    def files = ["file-1", "file-2", "file-3"]
    def pipeline_name = "p3"
    pipeline {
        agent any
        stages {
            stage('1') {
                steps {
                    script {
                        echo "step 1"
                    }
                }
                steps {
                    script {
                        if ( pipeline_name == "p1") parallel stage_deploy()
                        else if ( pipeline_name == "p2" ) stage_p2()
                        else stage_p3()
                    }
                    
                }
            }       
        }
    }

}
