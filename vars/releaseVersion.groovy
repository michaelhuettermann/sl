dev call(String name = 'pom.xml') {
    echo "Calling shared library, with ${name}."
    sh "mvn com.huettermann:versionfetcher:1.0.0:release versions:set -DgenerateBackupPoms=false -f ${name}"
}
