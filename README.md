## Desafio de conhecimento
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/clevsampaio/knowledge-challenge/maven?style=for-the-badge)

Este projeto é exclusivamente para desafios de conhecimento para abordar estrutura, estilo de código, boas práticas e 
soluções para automação de testes na linguagem Java.

### O que foi utilizado?

O projeto foi construido com a estrutura [Maven 3](http://maven.apache.org/download.cgi), [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) e algumas dependências como:
[TestNG](https://mvnrepository.com/artifact/org.testng/testng/7.3.0), 
[Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.141.59), 
[WebDriverManager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/4.3.1), 
[ExtentReports](https://mvnrepository.com/artifact/com.aventstack/extentreports/4.0.9), 
[ExtentReports TestNG Adapter](https://mvnrepository.com/artifact/com.aventstack/extentreports-testng-adapter/1.0.3), 
[Java Faker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker/1.0.2).

### Como executar?
Para executar em sua máquina local, basta utilizar o comando `mvn test`, por padrão é utilizado o chrome.
Para executar no firefox, basta acrescentar seguinte o comando `-Dproperty.browser=firefox`.

Observações:
- Lembrando que é necessário haver o chrome instalado em sua máquina!

- Caso queira executar em sua máquina local utilizando [zalenium](https://opensource.zalando.com/zalenium/), basta utilizar o seguinte comando 
`mvn test -Dproperty.type=remote`.
  
Para executar em uma máquina remota utilizando [zalenium](https://opensource.zalando.com/zalenium/), 
basta utilizar o comando `mvn test -Dproperty.type=remote -Dproperty.hub=http://localhost:4444/wd/hub (ou a url definida pelo serviço)`.