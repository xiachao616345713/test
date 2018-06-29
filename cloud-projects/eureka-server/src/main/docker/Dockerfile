FROM java:8
VOLUME /tmp
COPY eureka-server-1.0.0-assembly.tar.gz app.tar.gz
RUN tar -zxvf app.tar.gz
WORKDIR eureka-server-1.0.0
ENTRYPOINT ["bin/start.sh","com.manager.EurekaServerApplication"]