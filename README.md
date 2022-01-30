# SoPra WS21/22

> This repository contains example projects for a RESTful API with Spring Boot (`api/`) and for a single page application with Vue.js (`frontend/`).
> Please refer to the `README.md` files in the respective folders for concrete instructions.

## Installation und Start

Assuming all prerequisites are fulfilled, you can follow these instructions to get to know the application:
- Start the API by navigating into its folder and executing `./mvnw spring-boot:run`.
- Open http://localhost:8080/api/v1/swagger-ui in your browser. It will enable you to interact with the API.

- Now start the frontend by navigating into its folder and executing `npm install` and then `npm run serve`.
- It will host the UI at http://localhost:8000, which you can now also open in your browser.

Better instructions can be seen in the given subfolders "frondend" and "api".

## Usage of the Application

The application should provide detailed information about selected crypto assets. With the application the user can see the current index of certain cryptocurrenices. The user can also calculate the correlation coefficient between different assets. All functions, which are visulized, can be inspected with time differences.

## Known Bugs

- Correlation for volumn and marketcap doesn't work yet
- Threshold for correlation doesn't work yet
- Detailpage for crypo assets doesn't show information yet
- Homepage cannot load every cryptocurrencies yet
- Smaller bugs in graphs and correlation tables

## Changelog

Development History can be seen in [CHANGELOG.md](CHANGELOG.md).
