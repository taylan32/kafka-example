# Spring boot kafka implementation

---

- Download `https://github.com/taylan32/kafka-example.git`
- docker command : `docker compose up`

## endpoints
for add task
> POST http://localhost:8080/api/task/

example request body
```json
{
  "name" : "Task name 1"
}

```
get all tasks
> GET http://localhost:8080/api/task/

get one task
> GET http://localhost:8080/api/task/{id}

delete task
> DELETE http://localhost:8080/api/task/{id}