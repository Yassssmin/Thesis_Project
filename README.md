# Описание запуска проекта.
## 1 шаг, Запуск базы данных и симулятора:
```shell script
docker-compose up -d
```
## 2 шаг, Запуск приложения:
```shell script
java -jar aqa-shop.jar
```
## 3 шаг, Запуск тестов:
 - PostgreSQL
```shell script
./gradlew clean test \
 -Ddatasource.url="jdbc:postgresql://localhost:5432/app" \
 -Ddatasource.user="app" \
 -Ddatasource.password="hd7181BKa"
```
 - MySQL
```shell script
./gradlew clean test \
 -Ddatasource.url="jdbc:mysql://localhost:3306/app" \
 -Ddatasource.user="app" \
 -Ddatasource.password="hd7181BKa"
```

## 4 шаг, Генерация отчета:
```shell script
./gradlew allureServe
```

