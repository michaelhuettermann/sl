#!/usr/bin/groovy
import groovy.json.JsonSlurper;

def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    retry(1){

        println ( config.url )
        println ( config.bearer )

        def command = "curl -sk -X \"GET\" -H \"Authorization: Bearer ${config.bearer}\" \"${config.url}\""
        println ( command )

        def proc = command.execute()
        proc.waitFor()

        println "Process exit code: ${proc.exitValue()}"
        println "Std Err: ${proc.err.text}"
        println "Std Out: ${proc.in.text}"

        return ${proc.in.text}
    }
}