def call(){

    stage("input") {
        input {
            message "Should we continue?"
            ok "Yes, we should."
            parameters {
                string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            }
        }
        echo "Hello, ${PERSON}"
    }
    stage("deploy") {
        echo "deploy"
    }

    
}