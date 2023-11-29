# docker build --no-cache --progress=plain -f .gitpod.Dockerfile .
FROM gitpod/workspace-full

USER root
RUN bash -c "gpg -k"
RUN bash -c "gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69"
RUN bash -c "echo 'deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main' | tee /etc/apt/sources.list.d/k6.list"
RUN bash -c "apt-get update"
RUN bash -c "apt-get install k6"

USER gitpod
RUN bash -c "brew install hurl"
ARG JAVA_SDK="21-amzn"
RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
    && sdk install java $JAVA_SDK \
    && sdk default java $JAVA_SDK \
    && sdk install quarkus"

