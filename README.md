## Вычислитель отличий
***
### Hexlet tests and linter status:
[![Actions Status](https://github.com/thygh0st/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/thygh0st/java-project-71/actions/workflows/hexlet-check.yml)
[![app_tests Action Status](https://github.com/thygh0st/java-project-71/actions/workflows/app_tests.yml/badge.svg)](https://github.com/thygh0st/java-project-71/actions/workflows/app_tests.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=thygh0st_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=thygh0st_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=thygh0st_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=thygh0st_java-project-71)
***

Вычислитель отличий – программа, определяющая разницу между двумя структурами данных.

Возможности утилиты:
- Поддержка разных входных форматов: yaml и json
- Генерация отчета в виде plain text, stylish и json

Описание доступных опций и формата вызова можно получить по `--help`

### Сборка и запуск
```shell
cd ./app
make build-dist
./build/install/app/bin/app --version
```

### Демонстрация
#### [Сравнение JSON](https://asciinema.org/a/JUC4NIxytz8kP8h1zG9YjJET2 "Open asciinema demo generate()")
#### [Сравнение YAML](https://asciinema.org/a/zCXOoxEw2OxxNul2RelHhGUxQ "Open asciinema demo generate()")
