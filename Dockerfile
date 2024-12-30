FROM ubuntu:22.04

RUN apt-get update  \
    && apt-get upgrade -y \
    && apt-get install build-essential -y \
    && apt-get install -y curl gnupg  \
    && apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9 \
    && curl -O https://cdn.azul.com/zulu/bin/zulu-repo_1.0.0-2_all.deb \
    && apt-get install ./zulu-repo_1.0.0-2_all.deb -y \
    && apt-get update \
    && apt-get install zulu21-jdk -y

RUN export MAVEN_WS="$(mktemp -d)" \
    && export MAVEN_VERSION=3.8.8 \
    && export MAVEN_NAME=apache-maven-"$MAVEN_VERSION" \
    && cd "$MAVEN_WS" \
    && curl --request GET -sL \
        --url https://downloads.apache.org/maven/maven-3/"$MAVEN_VERSION"/binaries/"$MAVEN_NAME"-bin.tar.gz \
        --output "$MAVEN_NAME"-bin.tar.gz \
    && tar -xvzf "$MAVEN_NAME"-bin.tar.gz -C /opt \
    && ln -s /opt/"$MAVEN_NAME" /opt/maven

ENV JAVA_HOME=/usr/lib/jvm/zulu21
ENV M2_HOME=/opt/maven
ENV PATH=$JAVA_HOME/bin:$PATH
ENV PATH=$M2_HOME/bin:$PATH


CMD ["mvn", "-version"]
