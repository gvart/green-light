# Green Light

![.github/workflows/push-on-[develop].yml](https://github.com/gvart/green-light/workflows/.github/workflows/push-on-%5Bdevelop%5D.yml/badge.svg?branch=develop&event=push)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=gvart_green-light&metric=coverage)](https://sonarcloud.io/dashboard?id=gvart_green-light)
[![Gitter](https://badges.gitter.im/green_light/community.svg)](https://gitter.im/green_light/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
This is an open source project based on Spring Cloud which implies Micro Services 
architecture. The main idea of that project is providing a good solution to organize 
volunteers in public activity upon our environment.
## Features

###### - Full project is written in kotlin
###### - Inter services communication is build on top of RSocket
###### - OAuth2 Authorization server
###### - Auto configurable resource servers
###### - JWT tokens signed with self-signed certificate

## Installation
In order to startup Green Light run the following commands:

Application is working over HTTPS, before start please add following line in your hosts file:

`127.0.0.1 greenlight.local`

```bash
git clone git@github.com:gvart/green-light.git
cd green-light
./gradlew build
cd docker
docket stack deploy green-light
docket stack ps green-light
```
## Usage
* WebApp url: https://greenlight.local:8080/
* Keycloack url: https://greenlight.local:8443/

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change 
and do not forget to read [CONTRIBUTION RULES](./contributing.md)

## Thanks to
I would like to say 'Thank you' to each of the people which contribute to the following resources: [USEFUL_RESOURCES](./thanks_to.md)
## License
[MIT](https://choosealicense.com/licenses/mit/)