ДЗ 29. Spring Core

1. Реалізувати клас Product які складається з: id, назва, ціна
2. Товари зберігаються у класі-біні ProductRepository, у вигляді списку. Цей список ініціалізується даними при старті програми.
3. ProductRepository дозволяє отримати весь список або один товар за ID.
4. Реалізувати клас-бін Cart для, в який можна додавати та видаляти товари за id.
5. Реалізувати консольну програму для керування кошиком.
   5.1 Додавання товару до кошика
   5.2 Видалення товару з кошика
6. Щоразу при запиті екземпляра-біна кошика з контексту додатка повинен повертатися новий екземпляр кошика.
   ВАЖЛИВО! БД підключати не треба.


Приклад роботи створеної програми
Products are available for purchase :
------------------------------------
id= 1, title= Bread, price= 22.0
id= 2, title= Water, price= 15.3
id= 3, title= Cola, price= 25.5
id= 4, title= Milk, price= 32.0
id= 5, title= Salt, price= 10.2
id= 6, title= Sugar, price= 35.0
------------------------------------
Want to start shopping select Yes to exit the store No: yes
Say your name: Alex
Alex your cart: Alex-1
Want to create additional carts? Yes, No: Yes
An additional carts Alex-2 has been created for you.
Want to create additional carts? Yes, No: no
Alex the following carts are available for shopping:
-------------------------
# 1 - Alex-1
# 2 - Alex-2
-------------------------
Use commands to manage purchases:
-------------------------------------------------------------------
commands example notation
-add   | -add 1: 1,4,6  | - adding products 1,4,6 to the cart 1
-del   | -del 2: 3,2    | - removing products 3,2 from the cart 2
-show  | -show 2        | - show the contents of the cart 2
-exit  |                | - completing purchases
-------------------------------------------------------------------
-add 1: 1,4,5
Products have been added to the cart
-add 2: 3,2
Products have been added to the cart
-del 1: 4
Products have been removed from the shopping cart
-exit

Alex your purchases:
- cart # Alex-1
-----------------------------------------
id= 1, title= Bread, price= 22.0
id= 5, title= Salt, price= 10.2
-----------------------------------------
                    Total price: 44.0
- cart # Alex-2
-----------------------------------------
id= 3, title= Cola, price= 25.5
id= 2, title= Water, price= 15.3
-----------------------------------------
                    Total price: 30.6
GOOD DAY