# Описание запуска проекта.
## 1 шаг, Запуск базы данных и симулятора:
```
docker-compose up -d

```
## 2 шаг, Запуск приложения:
```
java -jar aqa-shop.jar

```
## 3 шаг, Запуск тестов:
```
./gradlew clean test allureReport

```

