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
                        def tests = [:]
                        for (f in files) {
                            tests["${f}"] = {

                                stage("${f}") {
                                    echo '${f}'
                                }
                            }
                        }
                        parallel tests
                    }
                }
            }       
        }
    }

}
