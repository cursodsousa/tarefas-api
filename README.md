# Bem vindo a Todo API

Esse é um orientado para utilização da Todo API, este documento contém tudo o que você precisa para rodar este projeto feito com Java.

# O projeto

Esta aplicação é uma API Restful desenvolvida utilizando Spring Boot 2.2. Trata-se de uma API de tarefas (Todos).

## Pré-requisitos
>Para download do projeto:
É necessário ter o GIT instalado na sua máquina.

>Para fazer o Build:
Para realizar o build e rodar a aplicação, é necessário ter o **JDK** e o **Maven** instalados e configurados na sua máquina, além de uma **JRE**.

## Baixar o projeto

Em uma pasta à sua escolha, utilize o comando 

    git clone https://github.com/cursodsousa/tarefas-api.git

feito isso o projeto estará na sua máquina. 

## Rodar o projeto

Para rodar o projeto é bem simples, entre na pasta raíz do projeto ( `cd tarefas-api` )  e rode o comando

    mvn spring-boot:run

dessa forma a aplicação Spring boot vai rodar e vc vai conseguir acessá-la através do browser na url:

http://localhost:8080/swagger-ui.html


## Build do projeto

Para fazer o build também é bem simples, basta utilizar o comando

    mvn clean package

Dessa forma será criada uma pasta na raíz do projeto chamada "target", entre nela (`cd target`) e você terá
um arquivo com a extensão ".jar" com o nome de tarefas-api.0.0.1-SNAPSHOT.jar.


## Rodando o .jar

Para rodar o arquivo ".jar" basta utilizar o comando 

    java -jar tarefas-api.0.0.1-SNAPSHOT.jar



