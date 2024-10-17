--liquibase formatted sql logicalFilePath:db/changelog/database-changelog.sql

--changeset akvine:WILD-EMU-1-1
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'DB_LOCK'
CREATE TABLE DB_LOCK
(
    LOCK_ID      VARCHAR(200)                        NOT NULL,
    PROCESS_ID   VARCHAR(36)                         NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX DB_LOCK_ID_INDX ON DB_LOCK (LOCK_ID);
--rollback not required

--changeset akvine:WILD-EMU-1-2
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'DB_LOCK_KEEPALIVE'
CREATE TABLE DB_LOCK_KEEPALIVE
(
    PROCESS_ID   VARCHAR(36)                         NOT NULL,
    EXPIRY_DATE  TIMESTAMP                           NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX DB_LOCK_KEEP_INDX ON DB_LOCK_KEEPALIVE (PROCESS_ID);
--rollback not required

--changeset akvine:WILD-EMU-1-3
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'ASYNC_DB_LOCKS'
CREATE TABLE ASYNC_DB_LOCKS
(
    LOCK_ID    VARCHAR(200) NOT NULL,
    EXPIRE     DECIMAL(22)  NOT NULL,
    CREATOR_ID VARCHAR(36)  NOT NULL,
    LOCK_STATE VARCHAR(50)  NOT NULL
);
CREATE UNIQUE INDEX ASYNC_DB_LOCKS_INDX ON ASYNC_DB_LOCKS (LOCK_ID);
--rollback not required

--changeset akvine:WILD-EMU-1-4
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'XDUAL'
CREATE TABLE XDUAL
(
    DUMMY VARCHAR(1)
);
INSERT INTO XDUAL (DUMMY)
VALUES ('X');
--rollback not required

--changeset akvine:WILD-EMU-1-5
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'CLIENT_ENTITY' and table_schema = 'public';
CREATE TABLE CLIENT_ENTITY
(
    ID           BIGINT       NOT NULL,
    UUID         VARCHAR(255) NOT NULL,
    CREATED_DATE TIMESTAMP    NOT NULL,
    UPDATED_DATE TIMESTAMP,
    IS_DELETED   BOOLEAN      NOT NULL,
    DELETED_DATE TIMESTAMP,
    EMAIL        VARCHAR(255) NOT NULL,
    HASH         VARCHAR(255) NOT NULL,
    CONSTRAINT CLIENT_PKEY PRIMARY KEY (id)
);
CREATE SEQUENCE SEQ_CLIENT_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX CLIENT_ID_INDEX ON CLIENT_ENTITY (ID);
CREATE UNIQUE INDEX CLIENT_UUID_INDEX ON CLIENT_ENTITY (UUID);
--rollback not required

--changeset akvine:WILD-EMU-1-6
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'SPRING_SESSION' and table_schema = 'public';
CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            VARCHAR(36)    NOT NULL,
    SESSION_ID            VARCHAR(36),
    CREATION_TIME         NUMERIC(19, 0) NOT NULL,
    LAST_ACCESS_TIME      NUMERIC(19, 0) NOT NULL,
    MAX_INACTIVE_INTERVAL NUMERIC(10, 0) NOT NULL,
    EXPIRY_TIME           NUMERIC(19, 0) NOT NULL,
    PRINCIPAL_NAME        VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);
CREATE INDEX SPRING_SESSION_INDX ON SPRING_SESSION(LAST_ACCESS_TIME);
--rollback not required

--changeset akvine:WILD-EMU-1-7
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'SPRING_SESSION_ATTRIBUTES' and table_schema = 'public';
CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID VARCHAR(36),
    ATTRIBUTE_NAME     VARCHAR(200),
    ATTRIBUTE_BYTES    BYTEA,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
);
CREATE INDEX SPRING_SESSION_ATTRIBUTES_INDX on SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID);
--rollback not required

--changeset akvine:WILD-EMU-1-8
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'ACCESS_TOKEN_ENTITY' and table_schema = 'public';
CREATE TABLE ACCESS_TOKEN_ENTITY
(
    ID              BIGINT                             NOT NULL,
    CREATED_DATE    TIMESTAMP                          NOT NULL,
    UPDATED_DATE    TIMESTAMP,
    TOKEN           VARCHAR(40)                        NOT NULL,
    EXPIRED_AT      TIMESTAMP                          NOT NULL,
    client_id        bigint                          NOT NULL,
    CONSTRAINT ACCESS_TOKEN_CLIENT_FKEY FOREIGN KEY (client_id) REFERENCES CLIENT_ENTITY (ID),
    CONSTRAINT ACCESS_TOKEN_PK PRIMARY KEY (ID)
);
CREATE SEQUENCE SEQ_ACCESS_TOKEN_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX ACCESS_TOKEN_TOKEN_INDX ON ACCESS_TOKEN_ENTITY (TOKEN);
CREATE INDEX ACCESS_TOKEN_CLIENT_ID ON ACCESS_TOKEN_ENTITY (client_id);

--changeset akvine:WILD-EMU-1-9
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'CARD_TYPE_ENTITY' and table_schema = 'public';
CREATE TABLE CARD_TYPE_ENTITY
(
    ID              BIGINT                             NOT NULL,
    CREATED_DATE    TIMESTAMP                          NOT NULL,
    UPDATED_DATE    TIMESTAMP,
    TYPE            VARCHAR(255)                       NOT NULL,
    CONSTRAINT CARD_TYPE_PK PRIMARY KEY (ID)
);
CREATE SEQUENCE SEQ_CARD_TYPE_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX CARD_TYPE_ID_INDX ON CARD_TYPE_ENTITY (ID);
CREATE UNIQUE INDEX CARD_TYPE_TYPE_INDX ON CARD_TYPE_ENTITY (TYPE);

--changeset akvine:WILD-EMU-1-10
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
--precondition-sql-check expectedResult:0 select count(*) from CARD_TYPE_ENTITY;
INSERT INTO CARD_TYPE_ENTITY (ID, TYPE, CREATED_DATE) VALUES (1, 'Мужской', '2024-09-16 00:00');
INSERT INTO CARD_TYPE_ENTITY (ID, TYPE, CREATED_DATE) VALUES (2, 'Женский', '2024-09-16 00:00');
INSERT INTO CARD_TYPE_ENTITY (ID, TYPE, CREATED_DATE) VALUES (3, 'Детский', '2024-09-16 00:00');
INSERT INTO CARD_TYPE_ENTITY (ID, TYPE, CREATED_DATE) VALUES (4, 'Девочки', '2024-09-16 00:00');
INSERT INTO CARD_TYPE_ENTITY (ID, TYPE, CREATED_DATE) VALUES (5, 'Мальчики', '2024-09-16 00:00');

--changeset akvine:WILD-EMU-1-11
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'CARD_CATEGORY_ENTITY' and table_schema = 'public';
CREATE TABLE CARD_CATEGORY_ENTITY
(
    ID                      BIGINT       NOT NULL,
    UUID                    INTEGER      NOT NULL,
    NAME                    VARCHAR(255) NOT NULL,
    CREATED_DATE            TIMESTAMP    NOT NULL,
    UPDATED_DATE            TIMESTAMP,
    CONSTRAINT CARD_CATEGORY_PKEY PRIMARY KEY (id)
);
CREATE SEQUENCE SEQ_CARD_CATEGORY_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX CARD_CATEGORY_ID_INDEX ON CARD_CATEGORY_ENTITY (ID);
CREATE UNIQUE INDEX CARD_CATEGORY_UUID_INDEX ON CARD_CATEGORY_ENTITY (UUID);

--changeset akvine:WILD-EMU-1-12
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from CARD_CATEGORY_ENTITY;
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (1, 2560, '3D очки', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (2, 1152, '3D-принтеры', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (3, 1151, '3D-ручки', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (4, 7771, 'AKF системы', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (5, 4034, 'AV-ресиверы', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (6, 1928, 'BB-кремы', '2024-09-17 00:00');
INSERT INTO CARD_CATEGORY_ENTITY (ID, UUID, NAME, CREATED_DATE) VALUES (7, 4035, 'Blu-Ray проигрыватели', '2024-09-17 00:00');

--changeset akvine:WILD-EMU-1-13
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'CARD_ENTITY' and table_schema = 'public';
CREATE TABLE CARD_ENTITY
(
    ID                      BIGINT       NOT NULL,
    UUID                    INTEGER      NOT NULL,
    NAME                    VARCHAR(255) NOT NULL,
    CARD_CATEGORY_ID        BIGINT       NOT NULL,
    BARCODE                 VARCHAR(255) NOT NULL,
    CARD_TYPE_ID            BIGINT       NOT NULL,
    CLIENT_ID               BIGINT       NOT NULL,
    PRICE                   INTEGER      NOT NULL,
    DISCOUNT                INTEGER      NOT NULL,
    CREATED_DATE            TIMESTAMP    NOT NULL,
    UPDATED_DATE            TIMESTAMP,
    IS_DELETED              BOOLEAN      NOT NULL,
    DELETED_DATE            TIMESTAMP,
    CONSTRAINT CARD_PKEY PRIMARY KEY (id),
    CONSTRAINT CARD_TYPE_FKEY FOREIGN KEY (CARD_TYPE_ID) REFERENCES CARD_TYPE_ENTITY (ID),
    CONSTRAINT CARD_CATEGORY_FKEY FOREIGN KEY (CARD_CATEGORY_ID) REFERENCES CARD_CATEGORY_ENTITY (ID),
    CONSTRAINT CARD_CLIENT_FKEY FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT_ENTITY (ID)
);
CREATE SEQUENCE SEQ_CARD_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX CARD_ID_INDEX ON CARD_ENTITY (ID);
CREATE UNIQUE INDEX CARD_UUID_INDEX ON CARD_ENTITY (UUID);
CREATE UNIQUE INDEX CARD_CLIENT_INDEX ON CARD_ENTITY (CLIENT_ID);
CREATE INDEX CARD_TYPE_ENTITY_ID_INDEX ON CARD_ENTITY (CARD_TYPE_ID);
CREATE INDEX CARD_CATEGORY_ENTITY_ID_INDEX ON CARD_ENTITY (CARD_CATEGORY_ID);

--changeset akvine:WILD-EMU-1-14
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'ADVERT_BUDGET_SPENDING_ENTITY' and table_schema = 'public';
CREATE TABLE ADVERT_BUDGET_SPENDING_ENTITY
(
    ID                          BIGINT        NOT NULL,
    VALUE                       INTEGER       NOT NULL,
    DELAY_SECONDS               BIGINT        NOT NULL,
    TYPE                        VARCHAR(64)   NOT NULL,
    CREATED_DATE                TIMESTAMP    NOT NULL,
    UPDATED_DATE                TIMESTAMP,
    CONSTRAINT ADVERT_BUDGET_SPENDING_PKEY PRIMARY KEY (ID)
);
CREATE SEQUENCE SEQ_ADVERT_BUDGET_SPENDING_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX ADVERT_BUDGET_SPENDING_ENTITY_ID_INDEX ON ADVERT_BUDGET_SPENDING_ENTITY (ID);

--changeset akvine:WILD-EMU-1-15
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'ADVERT_ENTITY' and table_schema = 'public';
CREATE TABLE ADVERT_ENTITY
(
    ID                          BIGINT        NOT NULL,
    UUID                        INTEGER       NOT NULL,
    NAME                        VARCHAR(255)  NOT NULL,
    CHANGE_TIME                 TIMESTAMP     NOT NULL,
    STATUS                      INTEGER       NOT NULL,
    TYPE                        INTEGER       NOT NULL,
    CPM                         INTEGER       NOT NULL,
    BUDGET_SUM                  INTEGER       NOT NULL,
    ADVERT_BUDGET_SPENDING_ID   BIGINT        NOT NULL,
    CARD_ID                     BIGINT        NOT NULL,
    CREATED_DATE                TIMESTAMP    NOT NULL,
    UPDATED_DATE                TIMESTAMP,
    IS_DELETED                  BOOLEAN      NOT NULL,
    DELETED_DATE                TIMESTAMP,
    CONSTRAINT ADVERT_PKEY PRIMARY KEY (ID),
    CONSTRAINT ADVERT_BUDGET_SPENDING_FKEY FOREIGN KEY (ADVERT_BUDGET_SPENDING_ID) REFERENCES ADVERT_BUDGET_SPENDING_ENTITY (ID),
    CONSTRAINT CARD_ENTITY_FKEY FOREIGN KEY (CARD_ID) REFERENCES CARD_ENTITY (ID)
);
CREATE SEQUENCE SEQ_ADVERT_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX ADVERT_ID_INDEX ON ADVERT_ENTITY (ID);
CREATE UNIQUE INDEX ADVERT_UUID_INDEX ON ADVERT_ENTITY (UUID);
CREATE UNIQUE INDEX ADVERT_BUDGET_SPENDING_INDEX ON ADVERT_ENTITY (ADVERT_BUDGET_SPENDING_ID);
CREATE UNIQUE INDEX ADVERT_CARD_INDEX ON ADVERT_ENTITY (CARD_ID);

--changeset akvine:WILD-EMU-1-16
--preconditions onFail:MARK_RAN onError:HALT onUpdateSQL:FAIL
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where upper(table_name) = 'ADVERT_STATISTIC_ENTITY' and table_schema = 'public';
CREATE TABLE ADVERT_STATISTIC_ENTITY
(
    ID                      BIGINT       NOT NULL,
    VIEWS                   INTEGER,
    CLICKS                  INTEGER,
    CTR                     FLOAT,
    CPC                     FLOAT,
    SUM                     INTEGER,
    ATBS                    INTEGER,
    ORDERS                  INTEGER,
    CR                      INTEGER,
    SHKS                    INTEGER,
    SUM_PRICE               INTEGER,
    ADVERT_ID               BIGINT       NOT NULL,
    CREATED_DATE            TIMESTAMP    NOT NULL,
    UPDATED_DATE            TIMESTAMP,
    IS_DELETED              BOOLEAN      NOT NULL,
    DELETED_DATE            TIMESTAMP,
    CONSTRAINT ADVERT_STATISTIC_PKEY PRIMARY KEY (id),
    CONSTRAINT ADVERT_STATISTIC_ADVERT_FKEY FOREIGN KEY (ADVERT_ID) REFERENCES ADVERT_ENTITY (ID)
);
CREATE SEQUENCE SEQ_ADVERT_STATISTIC_ENTITY START WITH 1 INCREMENT BY 1000;
CREATE UNIQUE INDEX ADVERT_STATISTIC_ID_INDEX ON ADVERT_STATISTIC_ENTITY (ID);
CREATE INDEX ADVERT_STATISTIC_ADVERT_INDEX ON ADVERT_STATISTIC_ENTITY (ADVERT_ID);