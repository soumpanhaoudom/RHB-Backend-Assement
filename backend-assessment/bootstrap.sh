#!/bin/bash

#FUNCTION
dependency() {
    echo "Installing dependencies..."
}

install() {
    ./mvnw clean install -DskipTests
}

build() {
    ./mvnw clean package -DskipTests -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
}

deploy() {
    ./mvnw clean deploy -s settings.xml
}

start() {
    ./mvnw clean spring-boot:run -Dspring-boot.run.jvmArguments="-DPROFILE=$PROFILE -DCLOUD_SERVER=$CLOUD_SERVER" > $LOG_FILE & tail -f $LOG_FILE
}

case "$1" in
    'dependencies')
        dependency
        ;;
    'install')
        install
        ;;
    'build')
        build
        ;;
    'deploy')
        deploy
        ;;
    'start')
        start
        ;;
    *)
        echo "Usage: $0 { dependencies | install | build | deploy | start}"
        exit 1
        ;;
esac
exit 0