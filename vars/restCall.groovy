#!/usr/bin/groovy
import groovy.json.JsonSlurper;

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    retry(1){

        println ( config.url )
        println ( config.bearer )



        //def pr = "curl -sk -X GET -H \"Authorization: Bearer ${config.bearer} \"${config.url}\""


        def command = "curl -sk -X GET -H \"Authorization: Bearer ${config.bearer}\" \"${config.url}\""


        println ( command )

        println  " ... "

        def proc = command.execute()
        proc.waitFor()

        println "Process exit code: ${proc.exitValue()}"
        println "Std Err: ${proc.err.text}"
        println "Std Out: ${proc.in.text}"


        /*
        def HttpURLConnection connection = config.url.toURL().openConnection()

        if(config.bearer.length() > 0)
        {
            def conn = config.url.openConnection()
            connection.setRequestProperty("Authorization", "Bearer ${config.bearer}")
        }
        connection.setRequestMethod("GET")
        connection.setDoInput(true)

        def pr = null
        try {
            connection.connect()
            pr = new JsonSlurper().parse(new InputStreamReader(connection.getInputStream(),"UTF-8"))
        } finally {
            connection.disconnect()
        }
        */
        return null
    }
}