# REST API: Онлайн-магазин електроніки

### API REST документація для онлайн-магазину комп'ютерних аксесуарів

### Загальні відомості

- **Базовий URL**: `https://api.example.com/v1`
- **Формат даних**: JSON
- **Аутентифікація**: JWT токен у заголовку запиту `Authorization: Bearer <token>`

### Ендпоінти

### Управління обліковими записами користувачів

- **Реєстрація користувача**
    - **URL**: `/users/register`
    - **Метод**: `POST`
    - **Тіло запиту**:

        ```json
        {
          "email": "string",
          "password": "string",
          "confirmPassword": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 201 Created, 400 Bad Request
- **Аутентифікація користувача**
    - **URL**: `/users/login`
    - **Метод**: `POST`
    - **Тіло запиту**:

        ```json
        {
          "email": "string",
          "password": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 200 OK, 401 Unauthorized
- **Редагування профілю користувача**
    - **URL**: `/users/{userId}`
    - **Метод**: `PUT`
    - **Тіло запиту**:

        ```json
        {
          "name": "string",
          "phone": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 200 OK, 400 Bad Request, 404 Not Found
- **Видалення облікового запису**
    - **URL**: `/users/{userId}`
    - **Метод**: `DELETE`
    - **Очікувані HTTP статуси**: 200 OK, 404 Not Found

### Управління каталогом товарів (для адміністраторів)

- **Додавання нового товару**
    - **URL**: `/products`
    - **Метод**: `POST`
      - **Тіло запиту**:

          ```json
          {
            "name": "string",
            "description": "string",
            "price": "number",
            "category": "string",
            "images": [
                "string", 
                "string", 
                "string"   
                ]
          }
          ```

    - **Очікувані HTTP статуси**: 201 Created, 400 Bad Request
- **Редагування товару**
    - **URL**: `/products/{productId}`
    - **Метод**: `PUT`
    - **Тіло запиту**:

        ```json
        {
          "name": "string",
          "description": "string",
          "price": "number",
          "category": "string",
          "images": [
                "string", 
                "string", 
                "string"   
                ]
        }
        ```

    - **Очікувані HTTP статуси**: 200 OK, 400 Bad Request, 404 Not Found
- **Видалення товару**
    - **URL**: `/products/{productId}`
    - **Метод**: `DELETE`
    - **Очікувані HTTP статуси**: 200 OK, 404 Not Found

### Управління категоріями товарів (для адміністраторів)

- **Додавання нової категорії товарів**
    - **URL**: `/categories`
    - **Метод**: `POST`
    - **Тіло запиту**:

        ```json
        {
          "categoryId": "string",
          "category": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 201 Created, 400 Bad Request
- **Редагування категорії товарів**
    - **URL**: `/categories/{categoryId}`
    - **Метод**: `PUT`
    - **Тіло запиту**:

        ```json
        {
          "categoryId": "string",
          "category": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 200 OK, 400 Bad Request, 404 Not Found
- **Видалення категорії товарів**
    - **URL**: `/categories/{categoryId}`
    - **Метод**: `DELETE`
    - **Очікувані HTTP статуси**: 200 OK, 404 Not Found

### Отримання та фільтрація даних

- **Отримання списку товарів**
    - **URL**: `/products`
    - **Метод**: `GET`
    - **Параметри запиту**: `category`, `minPrice`, `maxPrice`, `discount`, `sort`
    - **Очікувані HTTP статуси**: 200 OK
- **Отримання детальної інформації про товар**
    - **URL**: `/products/{productId}`
    - **Метод**: `GET`
    - **Очікувані HTTP статуси**: 200 OK, 404 Not Found
- **Отримання списку категорій товарів**
    - **URL**: `/categories`
    - **Метод**: `GET`
    - **Очікувані HTTP статуси**: 200 OK

### Система замовлень

- **Додавання товару до кошика**
    - **URL**: `/cart`
    - **Метод**: `POST`
    - **Тіло запиту**:

        ```json
        {
          "productId": "string",
          "quantity": "number"
        }
        ```

    - **Очікувані HTTP статуси**: 200 OK, 400 Bad Request
- **Оформлення замовлення**
    - **URL**: `/orders`
    - **Метод**: `POST`
    - **Тіло запиту**:

        ```json
        {
          "items": [
            {
              "productId": "string",
              "quantity": "number"
            }
          ],
          "deliveryAddress": "string",
          "deliveryMethod": "string"
        }
        ```

    - **Очікувані HTTP статуси**: 201 Created, 400 Bad Request
- **Перегляд статусу замовлення**
    - **URL**: `/orders/{orderId}`
    - **Метод**: `GET`
    - **Очікувані HTTP статуси**: 200 OK, 404 Not Found

### Історія та Обране

- **Історія покупок користувача**
    - **URL**: `/orders/history`
    - **Метод**: `GET`
    - **Аутентифікація**: Потрібен JWT токен
    - **Очікувані HTTP статуси**: 200 OK, 401 Unauthorized
- **Отримання списку обраних товарів користувача**
    - **URL**: `/favorites`
    - **Метод**: `GET`
    - **Аутентифікація**: Потрібен JWT токен
    - **Очікувані HTTP статуси**: 200 OK, 401 Unauthorized

### Примітки

- Всі ендпоінти, що потребують аутентифікації, повинні перевіряти наявність та дійсність JWT токену.
- Для кожного ендпоінту повинна бути передбачена обробка помилок з відповідними HTTP статусами та повідомленнями про помилки.
- Вся інформація щодо ендпоінтів повинна бути задокументована в Swagger.
