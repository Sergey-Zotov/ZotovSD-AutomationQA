#language: ru

Функция: Авторизация подтвержденным пользователем

  Предыстория:
    Пусть В системе есть пользователь "ПОЛЬЗОВАТЕЛЬ" с параметрами:
      | Администратор | false          |
      | Статус        | Не подтвержден |
    И Открыт браузер на главной странице

  @test
  @ui
  Сценарий: Авторизоваться пользователем из предусловия
    Если На странице "Заголовок страницы" нажать на элемент "Войти"
    И Авторизоваться как пользователь "ПОЛЬЗОВАТЕЛЬ"
    То На странице "Заголовок страницы" отображается элемент с текстом "Домашняя страница"
    То На странице "Страница авторизации" отображается элемент с текстом "Ваша учётная запись создана и ожидает подтверждения администратора."
    То На странице "Заголовок страницы" не отображается элемент "Моя страница"
    То На странице "Заголовок страницы" отображается элемент с текстом "Войти"
    То На странице "Заголовок страницы" отображается элемент с текстом "Регистрация"