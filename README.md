# service-jobad-reader

to work with:

- [service-eureka-server](https://github.com/dotterbear/service-eureka-server)
- [service-rss-reader](https://github.com/dotterbear/service-rss-reader)
- [service-eureka-scheduler](https://github.com/dotterbear/service-eureka-scheduler)

## Development

### Prerequisites

*JAVA 8 Runtime
*Maven >= 3.3
*mongodb >= 4.0.5

## Get Started

```bash
mongod --port 37017
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## TODO List

- [x] total number of items
- [ ] define reusable enum for error msg, request parameter
- [ ] add code mapping to response
