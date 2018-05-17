FROM openjdk:8u171-jre-alpine

ENV JAR_FILE=@project.build.finalName@.@project.packaging@

WORKDIR /var/app
COPY $JAR_FILE .

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "$JAR_FILE"]