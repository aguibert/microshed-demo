# OpenLiberty
FROM open-liberty:microProfile3
ADD target/myservice.war /config/dropins
COPY src/main/liberty/config /config/

# Wildfly
#FROM jboss/wildfly
#ADD target/myservice.war /opt/jboss/wildfly/standalone/deployments/

# Payara
#FROM payara/micro:5.193
#CMD ["--deploymentDir", "/opt/payara/deployments", "--noCluster"]
#ADD target/myservice.war /opt/payara/deployments
