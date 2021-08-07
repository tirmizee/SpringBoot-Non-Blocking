# SpringBoot-Non-Blocking

### Prerequisites

    docker run --name mysql-standalone -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=user -e MYSQL_PASSWORD=pass -e MYSQL_DATABASE=schema -d mysql:8

    CREATE TABLE users (
      id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
      firstname VARCHAR(30) NOT NULL,
      lastname VARCHAR(30) NOT NULL,
      email VARCHAR(50)
    );
    
    INSERT INTO users (firstname, lastname, email) VALUES ('Pratya', 'Mkaa', 'ksjd@gmail.com');
