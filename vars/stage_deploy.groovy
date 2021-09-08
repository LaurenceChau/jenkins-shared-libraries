def call(){


    def tests = ["file-1", "file-2", "file-3"]

    tests["file-1"] = {

        stage("file-1") {
            echo 'file-1'
        }
    }

    tests["file-2"] = {

        stage("file-2") {
            echo 'file-2'
        }
    }
    
    parallel tests

    
}