
# Weather Service Project (Task 3)

## Описание

Это задание реализует сервис прогноза погоды по городам на Java.

## Функционал

- Получение координат города через Open-Meteo Geocoding API
- Получение прогноза погоды на ближайшие 24 часа через Open-Meteo Forecast API
- Кэширование данных в Redis с TTL 15 минут
- Визуализация температуры по часам через XChart
- Город выбирается через аргумент при запуске проекта


## Зависимости

- Java 19 или 21
- Gradle 8.x
- Redis (localhost:6379)

### Библиотеки (в Gradle)

- `org.json:json:20230227` – для работы с JSON
- `redis.clients:jedis:5.4.0` – клиент Redis
- `org.knowm.xchart:xchart:3.9.2` – графики

Все зависимости подтягиваются автоматически через Gradle.

## Установка и запуск

### Установить Redis и запустить сервер

``
bash
redis-server
``

### Склонировать проект
``
git clone <репозиторий>
cd doczilla-test
``
### Построить все подпроекты






# Doczilla Test Assignment

This repository contains solutions for the Doczilla test assignment.

## Structure

- task1-liquid-sort — Liquid sorting puzzle solver
- task2-file-service — File upload and sharing service
- task3-weather-service — Weather forecast service with caching

Each task is implemented as a separate independent application.
