# Лабораторна робота 3

## Тема: Робота зі списковими елементами в Android

### Мета лабораторної роботи:

* Розібратися з роботою зі списками в Android.
* Вивчити призначення класу адаптера та реалізувати його.
* Ознайомитися з механізмом повторного використання елементів списку.
* Вивчити роботу віджета `RecyclerView` та розглянути його відмінності від віджета `ListView`.

### Завдання

Необхідно розробити додаток "Список контактів" з таким функціоналом:

1.  Головне вікно програми відображає порожній список контактів та кнопку "+". Якщо список порожній, показується відповідне повідомлення.
2.  Натискання на кнопку "+" відкриває вікно додавання контакту.
3.  Користувач вводить ім'я, електронну пошту, телефон та робить фото контакту за допомогою кнопки "Take photo".
4.  Після заповнення всіх полів та фото, натискання кнопки "Add" додає контакт до списку в головному вікні.
5.  Нові контакти додаються внизу списку.
6.  Кожен елемент списку має кнопку "X" для видалення контакту.
7.  Якщо всі контакти видалені, знову відображається повідомлення про порожній список.
