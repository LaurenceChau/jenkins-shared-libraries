def call(){


    def files = ["file-1", "file-2", "file-3"]
    def tests
    for (f in files) {
        tests["${f}"] = {

            stage("${f}") {
                echo '${f}'
            }
        }
    }
    retuurn tests
    
}