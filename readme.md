# Green Light

![Build](https://github.com/gvart/green-light/workflows/Pipeline/badge.svg)
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
```bash
git clone git@github.com:gvart/green-light.git
cd green-light
./gradlew build
cd docker
docket stack deploy green-light
docket stack ps green-light
```
## Usage

TBD

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change 
and do not forget to read [CONTRIBUTION RULES](./contributing.md)

## Thanks to
I would like to say 'Thank you' to each of the people which contribute to the following resources: [USEFUL_RESOURCES](./thanks_to.md)
## License
[MIT](https://choosealicense.com/licenses/mit/)