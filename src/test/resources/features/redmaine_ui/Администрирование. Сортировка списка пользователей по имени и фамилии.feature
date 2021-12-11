#language: ru

Функция: Администрирование. Сортировка списка пользователей по имени и фамилии

  Предыстория:
    Пусть В системе есть пользователь "ПОЛЬЗОВАТЕЛЬ" с параметрами:
      | Администратор | true    |
      | Статус        | Активен |
    Пусть В системе есть 5 пользователей
    И Открыт браузер на главной странице

  @test
  @ui
  Сценарий: Авторизоваться пользователем из предусловия
    Если На странице "Заголовок страницы" нажать на элемент "Войти"
    И Авторизоваться как пользователь "ПОЛЬЗОВАТЕЛЬ"
    То На странице "Заголовок страницы" отображается элемент с текстом "Домашняя страница"
    Если На странице "Заголовок страницы" нажать на элемент "Администрирование"
    То На странице "Администрирование" отображается элемент с текстом "Администрирование"
    Если На странице "Администрирование" нажать на элемент "Пользователи"
    То На странице "Пользователи" отображается элемент с текстом "Пользователи"
    То На странице "Пользователи", таблица не отсортирована по "Список Фамилий"
    То На странице "Пользователи", таблица не отсортирована по "Список Имен"
    Если На странице "Пользователи" нажать на элемент "Фамилия"
    То На странице "Пользователи", таблица отсортирована по "Список Фамилий" по возрастанию
    То На странице "Пользователи", таблица не отсортирована по "Список Имен"
    Если На странице "Пользователи" нажать на элемент "Фамилия"
    То На странице "Пользователи", таблица отсортирована по "Список Фамилий" по убыванию
    То На странице "Пользователи", таблица не отсортирована по "Список Имен"
    Если На странице "Пользователи" нажать на элемент "Имя"
    То На странице "Пользователи", таблица отсортирована по "Список Имен" по возрастанию
    То На странице "Пользователи", таблица не отсортирована по "Список Фамилий"
    Если На странице "Пользователи" нажать на элемент "Имя"
    То На странице "Пользователи", таблица отсортирована по "Список Имен" по убыванию
    То На странице "Пользователи", таблица не отсортирована по "Список Фамилий"
