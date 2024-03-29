# Софтверски квалитет и тестирање - Проектна Задача
## Виктор Мегленовски - 191001, ФИНКИ 2021/22
### Selenium и мутациско тестирање на апликација
#### Репозиториум од апликацијата која што е тестирана: https://github.com/viktor-meglenovski/dnick-hifi-prototype

## 1. Вовед

Целта на овој проект е да се тестира интерфејсот на веб апликација со помош на Selenium, како и да се тестира сервисната логика на апликацијата со помош на мутациско тестирање. Апликацијата која што се тестира е „ПрваПомош++“ која што самостојно ја изработив во рамки на предметот Дизајн на интеракцијата човек-компјутер.

## 2. Користени технологии

Во рамки на овој проект искористени се неколку различни библиотеки и модули за тестирање, меѓу кои: JUnit, Mockito, Pittest и Selenium.

## 3. Тестирање на корисничкиот интерфејс со Selenium

Корисничкиот интерфејс на апликацијата, којшто е креиран со Thymeleaf template engine е тестиран со помош на Selenium. Напишани се голем број на тестови во кои се проверува:
- Присуство/отсуство на одредени компоненти при одредени случаи
- Точност на патеки и линкови
- Однесување на апликацијата при симулирање на цело корисничко сценарио

## 4. Тестирање на сервисната логика со Pittest
За тестирање на сервисната логика на апликацијата, напишани се голем број Unit тестови. Користени се мутанти како критериум за покриеност на тестовите. Во рамки на тестовите се искористени Mock објекти до потребните зависности (Repository и други Service имплементации). Користена е Pittest библиотеката за приказ на покриеноста на мутантите. На крајот добиена е 100% покриеност на сите функции, сите редови код и сите мутанти од сервисната логика.
