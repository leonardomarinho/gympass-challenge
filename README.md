# Gympass Coding Challenge
Java API for processing F1 data developed for Gympass Coding Challenge.

## How it works

Once the application is running it exposes an endpoint ("/") for that receives a file containing preformatted F1 data, processes it and returns a JSON containing several informations extracted from the file.

##### By default the application uses the port 8080.

Please notice that formatting is important.

##### AND THE FILE MUST CONTAIN 4 SPACES AS DATA SEPARATOR

A sample of the expected file is below.

```text
Hora    Piloto  Nº Volta    Tempo Volta Velocidade média da volta
23:49:08.277    038 – F.MASSA    1    1:02.852    44,275
23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243
23:49:11.075    002 – K.RAIKKONEN    1    1:04.108    43,408
23:49:12.667    023 – M.WEBBER    1    1:04.414    43,202
23:49:30.976    015 – F.ALONSO    1    1:18.456    35,47
23:50:11.447    038 – F.MASSA     2    1:03.170    44,053
23:50:14.860    033 – R.BARRICHELLO    2    1:04.002    43,48
23:50:15.057    002 – K.RAIKKONEN    2    1:03.982    43,493
23:50:17.472    023 – M.WEBBER    2    1:04.805    42,941
23:50:37.987    015 – F.ALONSO    2    1:07.011    41,528
23:51:14.216    038 – F.MASSA     3    1:02.769    44,334
23:51:18.576    033 – R.BARRICHELLO    3    1:03.716    43,675
23:51:19.044    002 – K.RAIKKONEN    3    1:03.987    43,49
23:51:21.759    023 – M.WEBBER    3    1:04.287    43,287
23:51:46.691    015 – F.ALONSO    3    1:08.704    40,504
23:52:01.796    011 – S.VETTEL    1    3:31.315    13,169
23:52:17.003    038 – F.MASS      4    1:02.787    44,321
23:52:22.586    033 – R.BARRICHELLO    4    1:04.010    43,474
23:52:22.120    002 – K.RAIKKONEN    4    1:03.076    44,118
23:52:25.975    023 – M.WEBBER    4    1:04.216    43,335
23:53:06.741    015 – F.ALONSO    4    1:20.050    34,763
23:53:39.660    011 – S.VETTEL    2    1:37.864    28,435
23:54:57.757    011 – S.VETTEL    3    1:18.097    35,633
```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
* JDK 8 or later
* Gradle 4 or later
```

### Installing

Clone this repository

```
git clone https://github.com/leonardomarinho/gympass-challenge.git
```

To run it navigate into the project root folder and execute:

```
gradle bootRun
```

### Using

Since it's an API you can submit a POST containing the file using a tool like POSTMAN or via Terminal using curl.

For curl use the following command replacing PATH_TO_FILE with the real path:
```
curl -F 'file=@PATH_TO_FILE' http://localhost:8080/
```

## Running the tests

You can run the tests by navigating into the root folder and using
```
gradle test
```

## Built With

* [Java 8](https://www.java.com/pt_BR/download/) - The language most people love to hate
* [Gradle](https://gradle.org/) - Dependency Management
* [SpringBoot](http://spring.io/projects/spring-boot) - Framework used
* [Spring Initializr](https://start.spring.io/) - Bootstrap for SpringBoot applications

## Authors

* **Leonardo Marinho** - [Github](https://github.com/leonardomarinho) | [Deu Tilt](http://deutilt.com.br) | [Linkedin](http://linkedin.com/in/leonardomarinho)