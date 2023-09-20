# bankmanagementsystem
Bank management system

# Spring Boot Application

Это простое Spring Boot приложение, предоставляющее REST API для управления банковскими счетами и транзакциями.

## Описание

Приложение имеет 3 сущности:
- 'User': сущность пользователя.
- 'Account': сущность банковского счета.
- 'Transaction': сущность транзакций.
  3 Сервиса:
- 'UserService'
- 'AccountService'
- 'TransactionService'
  2 контроллера:
- 'AccountController': Отвечает за управление аккаунтами и установку месячных лимитов.
- 'TransactionController': Обрабатывает операции с транзакциями, такие как конвертация валют, создание транзакции и получение транзакций, превышающих установленные лимиты.
  3 Репозитория:
- 'UserRepository'
- 'AccountRepository'
- 'TransactionRepository'

## Технологии

- Java
- Spring Boot
- RESTful API
- JPA (Java Persistence API)
- PostgreSQL
- FlyWay

## Зависимости

- Spring Boot Starter Web
- Spring Data JPA
- Spring Boot DevTools
- Lombok
- PostgreSQl Driver
- FlyWay

## Запуск приложения

1. Убедитесь, что у вас установлен Java и Maven.
2. Клонируйте репозиторий:

   ```shell
   git clone https://github.com/zhanbolatazizov/bankmanagementsystem.git

Перейдите в директорию проекта:
cd bankmanagementsystem

Запустите приложение с помощью Maven:
mvn spring-boot:run

Приложение будет доступно по адресу http://localhost:8080.

##Примеры запросов

Установка месячного лимита для аккаунта: POST /account/set-monthly-limit?accountNumber=123456&newLimit=1000

Конвертация валюты: GET /transactions/convert?amount=100&currencyShortname=USD&limit=500

Создание транзакции: POST /transactions/create-transaction?fromAccountNumber=123456&toAccountNumber=789012&amount=50&category=Purchase

Получение транзакций, превышающих лимит: GET /transactions/exceeded-limit-transactions



