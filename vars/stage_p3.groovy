def call(){

    stage("input") {

        input(
            id: 'coreImageTags', message: 'Enter a comma separated list of additional tags for the image (0.0.1,some-tagname,etc):?', 
            parameters: [
                [$class: 'StringParameterDefinition', defaultValue: 'None', description: 'List of tags', name: 'coreImageTagsList'],
            ]
        )
        echo "Hello, ${PERSON}"
    }
    stage("deploy") {
        echo "deploy"
    }

    
}