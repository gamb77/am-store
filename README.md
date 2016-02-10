# am-store
AM store demo (http://mepa-store-api.herokuapp.com/marketads)

Requires maven3 for running

Starting up to port 8080: 
 - /am-store/am-build/mvn install
 - /am-store/am-application/mvn spring-boot:run


Used technologies: 

- SpringBoot (1.3.2.RELEASE)
- Spring RestTemplate
- Vaadin UI FW (spring-boot-starter 1.0.0)
- Javax validation
- JUnit
- PowerMock/EasyMock

Modules: 

AM-DOMAIN (Total coverage: 98,7%)
- JAR domain package for rest service calls and DTOs
 
AM-APPLICATION (Total coverage: 97%)
- Spring boot application entrypoint, Vaadin UI. 
