#!/bin/sh

# 환경 변수 설정
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH="$JAVA_HOME/bin:$PATH"

# JAR 파일 위치
JAR_FILE=/purchase.jar

# 애플리케이션 시작
exec java -jar $JAR_FILE