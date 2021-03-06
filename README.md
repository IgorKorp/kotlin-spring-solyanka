## Описание 

Прростая авторизация с использованием kotlin, spring-boot, hibernate 
## Сборка и запуск приложения 

```shell
git clone git@github.com:IgorKorp/kotlin-spring-solyanka.git
cd kotlin-spring-solyanka
```
Запуск docker: (Запустится PostgreSQl -- нужно для бд)
```bash
docker-compose build
docker-compose up -d 
```

упаковка в jar архив:
```shell
./gradlew bootJar
```
запуск:
```shell
java  -jar ./build/libs/auth\ -0.0.1-SNAPSHOT.jar 
```
или одной командой:
```shell
./gradlew bootRun
```

## Пример использования:
1. ресурс "/users" для получения всех пользователей.
```
GET http://localhost:8089/users
```
2. ресурс "/user/registration" для создания нового пользователя(Принимает json логин и пароль).
```
POST http://localhost:8089/user/registration
```
```json
{
    "login": "Peck",
    "password": "qwerty123"
}
```

3. ресурс "/user/auth" для авторизации пользователя, возвращает токен(Принимает json логин и пароль).
```
POST http://localhost:8089/user/auth
```
```json
{
    "login": "Peck",
    "password": "qwerty123"
}
```

4. ресурс "/user/{login}" для смены пароля, в(Принимает json старый пароль, два новых и  токен).
```
POST http://localhost:8089/user/Peck
```
```json
{
    "token": "8nPO7FssZhirgJFaqep03o2nb5ilcAdyVpRZiXJlc2smidahnmOiCgU3aLvMp7CePTyWb91PqDBLYEKX",
    "old_password": "qwerty123",
    "new_password": "qwerty",
    "new_confirmed_password": "qwerty"
}
```
