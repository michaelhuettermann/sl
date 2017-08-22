#!/usr/bin/groovy
def call(args) {
    echo "Calling shared library, from vars, with ${args}."
    sh "mvn com.huettermann:versionfetcher:1.0.0:release versions:set -DgenerateBackupPoms=false -f ${args}"
}
