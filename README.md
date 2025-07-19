# Task Manager REST API

## Описание

Простой CRUD-сервис для управления задачами. Реализован на Spring Boot с подключением H2, Redis, Docker, логированием, почтой и авторизацией.

## Возможности

- Создание задач
- Получение всех задач
- Получение задачи по ID
- Обновление задач
- Удаление задач
- Логирование всех HTTP-запросов и ответов
- Кэширование с Redis (`getAll` и `getById`)
- Basic Auth
- Email-уведомление при создании задачи
- HTTP GET-запрос к внешнему API (`https://api.restful-api.dev/objects`)
- Unit-тесты для сервиса
- Dockerfile

## Запуск
```bash
./mvnw clean package \
&& mkdir -p target/dependency \
&& (cd target/dependency && jar -xf ../*.jar) \
&& docker compose up --build
```

По умолчанию:
- **Логин:** `admin`
- **Пароль:** `admin123`