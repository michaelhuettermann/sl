#!/usr/bin/groovy
import groovy.json.JsonSlurper;

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    retry(2){


        System.Err.Println ( config.url )
        System.Err.Println ( config.bearer )

        def HttpURLConnection connection = config.url.openConnection()
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
        return pr
    }
}