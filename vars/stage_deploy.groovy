def call(){

    script {
        def tests = ["file-1", "file-2", "file-3"]
        for (f in tests) {
            tests["${f}"] = {

                stage("${f}") {
                    echo '${f}'
                }
            }
        }
        parallel tests
    }
    
}