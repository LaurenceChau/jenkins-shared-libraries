def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    
    pipeline {
        agent any
        stages {
            stage('1') {
                steps {
                    script {
                        def tests = [:]
                        for (f in findFiles(glob: '**/html/*.html')) {
                            tests["${f}"] = {
                                node {
                                    stage("${f}") {
                                        echo '${f}'
                                    }
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
