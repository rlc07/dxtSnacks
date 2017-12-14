CREATE TABLE User( 
 user_id              BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 name                 VARCHAR(100) NOT NULL,
 email                VARCHAR(130) NOT NULL UNIQUE,
 password             VARCHAR(280), 
 admin                BOOLEAN DEFAULT FALSE,
 create_date          DATE NOT NULL,
 edit_date            DATE,
 delete_date          DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (name,email,password,admin, create_date) 
VALUES ('Ronaldo','admin@admin.com','$2a$10$Y/hdSxxan79Rws1VqyHWk.L5zpC4lnGkYv0DEoFv/KNVgBVEmJsea', 1,CURRENT_DATE());

CREATE TABLE Permission( 
 permission_id  BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 name                 VARCHAR(100) NOT NULL,
 description           VARCHAR(130)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	user_id BIGINT(20) NOT NULL,
	permission_id BIGINT(20) NOT NULL,
	status        BOOLEAN DEFAULT 1,
	PRIMARY KEY (user_id, permission_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id),
	FOREIGN KEY (permission_id) REFERENCES permission(permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Phone( 
 phone_id             BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 phone_type           VARCHAR(30) NOT NULL,
 number               VARCHAR(20),
 user_id              BIGINT(50),
 create_date          DATE NOT NULL,
 edit_date            DATE,
 delete_date          DATE,
 FOREIGN KEY (user_id) REFERENCES user(user_id) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO phone (phone_type,number,user_id, create_date) 
VALUES ('MOBILE','19988788571',1,CURRENT_DATE());


CREATE TABLE Address( 

 address_id         BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
 street             VARCHAR(100) NOT NULL,
 number             INTEGER,
 district           VARCHAR(130),
 complement         VARCHAR(130),
 state              VARCHAR(60),
 city               VARCHAR(130) NOT NULL,
 zipcode            VARCHAR(10),
 user_id            BIGINT(50),
 create_date       DATE NOT NULL,
 edit_date         DATE,
 delete_date       DATE,
 FOREIGN KEY (user_id) REFERENCES user(user_id)
 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO address (street,number, district,city,state,zipcode,user_id, create_date) 
VALUES ('Rua Um', 200,'Nova Veneza','Sumaré','SP',13177543,1,CURRENT_DATE());

