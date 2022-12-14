# README #

## Тестовый проект "My Warehouse"

### Описание ###

Приложение позволяет вести учетов товаров на складе.

Пользователь может оформить поступление на склад, перемещение товаров между складами,
списание товара со склада или его продажу.

Все операции осуществляются путем отправки пользователем документа со списком товаров с указанными характеристиками.

**Виды документов:**
- **Receipt** (поступление товаро на склад). Указаывается тип документа, номер, дата, имя склада, 
на который поступают товары, список товаров, и в каждом товаре указывается его артикул, цена покупки, 
его описание и количетство. Документ увеличивает количество товара на складе и обновляет его цену покупки.
- **Selling** (продажа товара). Указаывается тип документа, номер, дата, имя склада,
  на который поступают товары, список товаров, и в каждом товаре указывается его артикул, цена продажи,
  его описание и количетство. Документ уменьшает количество товара на складе и обновляет его цену продажи.
- **Write-off** (списание товара). Указаывается тип документа, номер, дата, имя склада, 
  на который поступают товары, список товаров, и в каждом товаре указывается его артикул, описание и количетство. 
  Документ уменьшает количество товара на складе, но не обновляет никакую из цен.
- **Movement** (перемещение товаров между складами) Указаывается тип документа, номер, дата, 
  имя склада, с которого перемещаются товары, имя склада, на который поступают товары, 
  список товаров, и в каждом товаре указывается его артикул, описание и количетство.
  Документ также уменьшает количество товара на одном складе и увеличивает на другом складе, но не обновляет никакую из цен.

Все документы приложение принимает в формате JSON. После операции с товарами документ сохранется в базе. 
Документ одного типа имеет уникальный номер.

**Информация, которую можно получить пользователю:**
- Пользователь может получить информацию о конкретном товаре по его артикулу, увидеть его остаток на каждом складе, 
а также актуальные цены продажи и покупки. 
- Пользователь может получить отчет обо всех существующих товарах на всех склада, увидеть его актуальные цены продажи и покупки.
Отчет автоматически скачивается на устройство пользователя после отправки запроса. Загруженный файл в формате JSON.
- Пользовтель может получить об остатках всех существующих товаров на всех складах.
  Отчет автоматически скачивается на устройство пользователя после отправки запроса. Загруженный файл в формате JSON.
- Пользовтель может получить об остатках всех существующих товаров на конкретном складе. Для этого нужно указать имя склада.
Отчет автоматически скачивается на устройство пользователя после отправки запроса. Загруженный файл в формате JSON.
- Пользовтель также может получить любой тип документа, который был сохранен в базе. 
Для этого нужно указать тип документа и его номер. Документ автоматически скачивается на устройство пользователя после отправки запроса. 
Загруженный файл в формате JSON.

### Stack ###
* Java 11
* Spring Boot
* Spring data
* Hibernate
* PostgreSQL
* Junit5, Mockito, Spring Boot Test
* SpringFox (Swagger 3.0)

### Установка ###

* Клонировать репозиторий.
* Настроить конфигурацию запуска Spring Boot через класс Application.
* В файле application.properties в поле spring.datasource.username и password поменять на личные данные своего сервера Posgres
* Запустить приложение

### Как можно опробовать ###

* Для удобства создана коллекция API запросов в Postman с заготовленными телами зпросов в формате JSON. 
По ним и проводилось API тестирование REST контроллера. 
Ссылка на импорт коллекции: https://www.getpostman.com/collections/d5719531759763e2a21b
* Документация API будет находиться после заруска приложения по URL: http://localhost:8080/swagger-ui/
