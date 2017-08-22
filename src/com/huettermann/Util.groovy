#!/usr/bin/groovy
package com.huettermann

class Util {
    def setVersion(String pom) {
        echo "Calling shared library, a Util, with ${pom}."
        sh "mvn com.huettermann:versionfetcher:1.0.0:release versions:set -DgenerateBackupPoms=false -f ${pom}"
    }
    static void main(String []args){
    }
}



