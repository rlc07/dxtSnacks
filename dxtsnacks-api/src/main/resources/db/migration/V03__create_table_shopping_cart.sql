CREATE TABLE ShoppingCart( 
 shopping_cart_id     BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 purchase_date        DATE NOT NULL,
 amount               DECIMAL(10,2)  NOT NULL,
 discount             DECIMAL(10,2),
 payday               DATE,
 user_id              BIGINT(50),
 create_date          DATE NOT NULL,
 edit_date            DATE,
 delete_date          DATE,
 FOREIGN KEY (user_id) REFERENCES user(user_id) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

