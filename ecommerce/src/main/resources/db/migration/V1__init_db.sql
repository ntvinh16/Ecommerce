CREATE TABLE USER_TBL (
                             ID UUID PRIMARY KEY,
                             USERNAME VARCHAR(255) NOT NULL,
                             EMAIL VARCHAR(255) NOT NULL,
                             PASSWORD VARCHAR(120) NOT NULL,
                             BIRTHDAY DATE,
                             FULLNAME VARCHAR(40)
);

CREATE TABLE ROLE_TBL (
                             ID UUID PRIMARY KEY,
                             NAME VARCHAR(255) NOT NULL UNIQUE,
                             DESCRIPTION TEXT
);

CREATE TABLE PERMISSION_TBL (
                                   ID UUID PRIMARY KEY,
                                   NAME VARCHAR(255) NOT NULL UNIQUE,
                                   DESCRIPTION TEXT
);

CREATE TABLE USER_ROLES_TBL (
                                ID UUID PRIMARY KEY,
                                USER_ID UUID NOT NULL,
                                ROLE_ID UUID NOT NULL,
                                FOREIGN KEY (USER_ID) REFERENCES USER_TBL(ID),
                                FOREIGN KEY (ROLE_ID) REFERENCES ROLE_TBL(ID)
);

CREATE TABLE ROLE_PERMISSIONS_TBL (
                                      ID UUID PRIMARY KEY,
                                      ROLE_ID UUID NOT NULL,
                                      PERMISSION_ID UUID NOT NULL,
                                      FOREIGN KEY (ROLE_ID) REFERENCES ROLE_TBL(ID),
                                      FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSION_TBL(ID)
);