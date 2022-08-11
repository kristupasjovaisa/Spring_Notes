CREATE TABLE notes(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   DESCRIPTION VARCHAR(20) NOT NULL,
   CREATED_AT TIMESTAMP NOT NULL,
   OWNER_ID              BIGINT,
   PRIMARY KEY(ID)
);
CREATE TABLE persons(
   ID BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   LAST_NAME VARCHAR(20) NOT NULL,
   EMAIL VARCHAR(20) NOT NULL,
   PRIMARY KEY(ID)
);