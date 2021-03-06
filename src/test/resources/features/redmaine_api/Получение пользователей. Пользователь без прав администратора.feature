#language: ru

Функция: Получение пользователей. Пользователь без прав администратора

  Предыстория:
    Пусть В системе есть пользователь "ПОЛЬЗОВАТЕЛЬ1" с параметрами:
      | Администратор | false   |
      | Статус        | Активен |
    Пусть В системе есть пользователь "ПОЛЬЗОВАТЕЛЬ2" без ключа API
    Пусть Выполнено подключение к "API" пользователем "ПОЛЬЗОВАТЕЛЬ1"

  @test
  @api
  Сценарий:
    Если Отправить запрос "GET" пользователя "ПОЛЬЗОВАТЕЛЬ1" и сохранить ответ "ОТВЕТ" от "API"
    То Получен статус код ответа "200" из ответа "ОТВЕТ"
    То В теле ответа "ОТВЕТ" содержится информация о пользователе "ПОЛЬЗОВАТЕЛЬ1"
    Если Отправить запрос "GET" пользователя "ПОЛЬЗОВАТЕЛЬ2" и сохранить ответ "ОТВЕТ" от "API"
    То Получен статус код ответа "200" из ответа "ОТВЕТ"
    То В теле ответа "ОТВЕТ" содержится информация о пользователе "ПОЛЬЗОВАТЕЛЬ2"