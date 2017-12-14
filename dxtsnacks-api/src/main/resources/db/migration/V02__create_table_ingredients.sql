CREATE TABLE Ingredients( 
 ingredient_id        BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 name                 VARCHAR(130) NOT NULL,
 amount               DECIMAL(10,2) NOT NULL,
 create_date          DATE NOT NULL,
 edit_date            DATE,
 delete_date          DATE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ingredients (name,amount, create_date) VALUES ('Alface',0.40,CURRENT_DATE());
INSERT INTO ingredients (name,amount, create_date) VALUES ('Bacon',2.00,CURRENT_DATE());
INSERT INTO ingredients (name,amount, create_date) VALUES ('Hambúrguer de Carne',3.00,CURRENT_DATE());
INSERT INTO ingredients (name,amount, create_date) VALUES ('Ovo',0.80,CURRENT_DATE());
INSERT INTO ingredients (name,amount, create_date) VALUES ('Queijo',1.50,CURRENT_DATE());

CREATE TABLE Snacks( 
 snack_id             BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 name                 VARCHAR(100) UNIQUE NOT NULL,
 description          VARCHAR(130),
 amount               DECIMAL(10,2) NOT NULL,
 create_date          DATE NOT NULL,
 edit_date            DATE,
 delete_date          DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO snacks (name,description, amount, create_date) VALUES ('X-Bacon','Muito Saboroso',6.50,CURRENT_DATE());
INSERT INTO snacks (name,description, amount, create_date) VALUES ('X-Burger','O melhor da casa',4.50,CURRENT_DATE());
INSERT INTO snacks (name,description, amount, create_date) VALUES ('X-Egg','Esse é para os fortes',5.30,CURRENT_DATE());
INSERT INTO snacks (name,description, amount, create_date) VALUES ('X-Egg Bacon','Super Saboroso',7.30,CURRENT_DATE());


CREATE TABLE Snack_Ingredients( 
 snack_ingredient_id   BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 snack_id             BIGINT(50) NOT NULL,
 ingredient_id        BIGINT(50) NOT NULL,
 quantity             INTEGER NOT NULL,
 create_date          DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP,
 edit_date            DATE,
 delete_date          DATE,
 FOREIGN KEY (snack_id) REFERENCES snacks(snack_id),
 FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) 

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (1, 2,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (1, 3,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (1, 5,1);

INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (2, 3,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (2, 5,1);

INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (3, 4,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (3, 3,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (3, 5,1);

INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (4, 4,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (4, 2,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (4, 3,1);
INSERT INTO snack_ingredients (snack_id,ingredient_id,quantity) VALUES (4, 5,1);
