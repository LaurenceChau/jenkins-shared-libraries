def call(){



    stage("file-1") {
        input {
            message "Should we continue?"
            ok "Yes, we should."
            submitter "alice,bob"
            parameters {
                string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            }
        }
        steps {
            echo 'file-1'
        }
        
    }
    stage("file--2") {
        echo 'file-2'
    }

    
}