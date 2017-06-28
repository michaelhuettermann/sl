#!/usr/bin/groovy
package com.huettermann

dev setVersion(String pom) {
    echo "Calling shared library, with ${args}."
    sh "mvn com.huettermann:versionfetcher:1.0.0:release versions:set -DgenerateBackupPoms=false -f ${pom}"
}

return this;