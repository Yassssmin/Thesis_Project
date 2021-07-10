# Описание запуска проекта.
## 1 шаг, Запуск базы данных и симулятора:
```shell script
docker-compose up -d
```
## 2 шаг, Запуск приложения:
- PostgreSQL
```shell script
java -jar aqa-shop.jar \
 -spring.datasource.url="jdbc:postgresql://localhost:5432/app" \
 -spring.datasource.username="app" \
 -spring.datasource.password="hd7181BKa"
```
- MySQL
```shell script
java -jar aqa-shop.jar \
 -spring.datasource.url="jdbc:mysql://localhost:3306/app" \
 -spring.datasource.username="app" \
 -spring.datasource.password="hd7181BKa"
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
# Тестовая документация:
- [План](./Plan.md)
- [Отчет](./Summary.md)


