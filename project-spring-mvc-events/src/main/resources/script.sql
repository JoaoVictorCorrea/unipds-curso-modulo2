CREATE TABLE tbl_user(
    user_id INT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE tbl_conference(
    id_conference INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE tbl_session(
    session_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL ,
    start_date DATE NOT NULL,
    start_time TIME NOT NULL,
    tbm_conference_id_conference INT,
    FOREIGN KEY (tbm_conference_id_conference) REFERENCES tbl_conference(id_conference)
);

CREATE TABLE tbl_subscription(
    subscribed_user_id INT NOT NULL ,
    session_id INT NOT NULL ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    level int,
    unique_id VARCHAR(45) NOT NULL UNIQUE,
    FOREIGN KEY (subscribed_user_id) REFERENCES tbl_user(user_id),
    FOREIGN KEY (session_id) REFERENCES tbl_session(session_id)
);