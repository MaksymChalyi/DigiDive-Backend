
# Database Schema for Electronics Online Store

## Tables and Relationships

### **Cart**
- **CartID** INT (Primary Key)
- **UserID** INT (Foreign Key referencing Users)

**Relationships**:  
One cart is associated with one user, but a user can have many items in the cart (1 to many relationship with **CartItems**).

---

### **CartItems**
- **CartItemID** INT (Primary Key)
- **CartID** INT (Foreign Key referencing Cart)
- **ProductID** INT (Foreign Key referencing Products)
- **Quantity** INT

**Relationships**:  
One cart can have multiple items (1 to many with **CartItems**). Each cart item references a product from the **Products** table.

---

### **Users**
- **UserID** INT (Primary Key)
- **Name** VARCHAR
- **Email** VARCHAR (Unique)
- **PhoneNumber** VARCHAR
- **PasswordHash** VARCHAR
- **Role** ENUM('Customer', 'Administrator')

**Relationships**:  
Each user can have one cart, multiple orders, and multiple favorite items.

---

### **Favorites**
- **FavoriteID** INT (Primary Key)
- **UserID** INT (Foreign Key referencing Users)
- **ProductID** INT (Foreign Key referencing Products)

**Relationships**:  
One user can have many favorite products (1 to many relationship with **Favorites**). Each favorite references a product.

---

### **Orders**
- **OrderID** INT (Primary Key)
- **UserID** INT (Foreign Key referencing Users)
- **CreatedAt** TIMESTAMP
- **DeliveryAddress** VARCHAR
- **ContactPhone** VARCHAR
- **DeliveryMethod** VARCHAR
- **Status** ENUM
- **UpdatedAt** TIMESTAMP

**Relationships**:  
One user can place many orders, and one order can contain multiple products (1 to many with **OrderItems**).

---

### **OrderItems**
- **OrderItemID** INT (Primary Key)
- **OrderID** INT (Foreign Key referencing Orders)
- **ProductID** INT (Foreign Key referencing Products)
- **Quantity** INT
- **PriceAtPurchase** DECIMAL

**Relationships**:  
One order can contain multiple products (1 to many with **OrderItems**). Each item in an order references a product.

---

### **Categories**
- **CategoryID** INT (Primary Key)
- **Name** VARCHAR

**Relationships**:  
Each category can have many products (1 to many relationship with **Products**).

---

### **Products**
- **ProductID** INT (Primary Key)
- **Name** VARCHAR
- **Description** TEXT
- **Price** DECIMAL
- **CategoryID** INT (Foreign Key referencing Categories)
- **DiscountPrice** DECIMAL (NULL if no discount)
- **CreatedAt** TIMESTAMP
- **UpdatedAt** TIMESTAMP

**Relationships**:  
One product can be in multiple carts, orders, and favorite lists. Each product can have multiple images (1 to many relationship with **ProductImages**).

---

### **ProductImages**
- **ImageID** INT (Primary Key)
- **ProductID** INT (Foreign Key referencing Products)
- **ImageURL** VARCHAR

**Relationships**:  
One product can have multiple images. This is a one-to-many relationship between **Products** and **ProductImages**.

