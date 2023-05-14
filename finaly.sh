rm -rf ~/lib/glassfish7/glassfish/domains/domain1/applications/*
rm -rf ~/lib/glassfish7/glassfish/domains/domain1/generated/*
rm -rf ~/lib/glassfish7/glassfish/domains/domain1/imq/

~/lib/glassfish7/bin/asadmin start-domain
systemctl start docker
docker start db

~/lib/glassfish7/bin/asadmin deploy --force=true ./target/web.war
~/lib/glassfish7/bin/asadmin deploy --force=true ./../authMicroservice/target/authMicroservice.war