# Тестовое задание: «Сервис обработки заказов с Apache Kafka» 

Сервис демонстрирует базовую интеграцию **Spring Boot** и **Apache Kafka** на примере обработки заказов.

Проект состоит из двух логических компонентов:

- **order-service** — принимает HTTP-запрос на создание заказа и публикует событие в Kafka.
- **notification-service** — читает события из Kafka и имитирует отправку уведомления через логирование.

---

## Стек технологий

- Java 17
- Spring Boot 3.x / 2.7.x
- Spring Kafka
- Apache Kafka
- Docker Compose
- Maven / Gradle
- Testcontainers, опционально
- JSON serialization/deserialization

---

## Архитектура

```text
Client
  |
  | POST /api/orders
  v
Order Service
  |
  | OrderCreatedEvent
  v
Kafka topic: orders
  |
  v
Notification Service
  |
  | success -> log notification
  | error   -> retry -> orders-dlt
  v
Dead Letter Topic: orders-dlt
