# java_test
Java Backend Developer test
Необходимо сделать API для сервиса хранения видеофайлов с возможностью менять разрешение. В качестве фреймворка использовать Spring (Java). Для сборки использовать Gradle. Конвертация должна происходить с помощью ffmpeg (В качестве обертки нужно использовать https://github.com/bramp/ffmpeg-cli-wrapper). Для многопоточности и асинхронности использовать для этого лучше использовать RxJava 3.

## HTTP API

### Методы

#### `POST /file/`
Тело запроса: видеофайл в формате MP4.

В теле ответа возвращается 
```
{
  id: UUID — идентификатор , например: `876b8335-3279-4f59-9718-570f37f076ea`.
}
``` 

Код ответа: `200 Ok`.

#### `PATCH /file/{id}/`
Начать изменение разрешения видео используя ffmpeg. Ответ должен возвращаться независимо от завершения процесса обработки (обработка не должна блокировать запрос) 

Тело запроса:

```
{
  width: int,  - Чётное число больше 20
  height: int,  - Чётное число больше 20
}
```

В теле ответа возвращается 
```
{
  success: Boolean
}
``` 
Код ответа: `200 Ok`.

#### `GET /file/{id}/`
Получить информацию о файле и статусе его обработки

Тело ответа:

```
{
  id: uid,
  filename: string,
  processing: Boolean - идёт ли процесс обработки
  processingSuccess: null | true | false  - отображает успешность последней операции над видео. Дефолтное значение null.
}
```
Код ответа: `200 Ok`.ы


#### `DELETE /file/{id}/`
Удалить файл

В теле ответа возвращается 
```
{
  success: Boolean
}
``` 
Код ответа: `200 Ok`.


### Обработка ошибок
При возникновении ошибок необходимо возвращать ошибку в json формате
```
{
  error: string
}
```

## Дополнительно
Будет плюсом если:
 - есть автодокументация API 
 - есть система логирования
 - сервис завёрнут в docker контейнер
 - наличие юнит тестов
