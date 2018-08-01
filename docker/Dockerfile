FROM alpine:3.7

RUN apk --update add openjdk8-jre bash
RUN rm -rf /var/cache/apk/

# Copy artifact to image
ENV ARTIFACT="/presence-control-service.jar"
COPY "spring-boot/target${ARTIFACT}" "/"

CMD ["sh", "-c", "java -jar ${ARTIFACT}"]
