CREATE TABLE VOIVODESHIP (
    id                       NUMBER                 NOT NULL,
    name                     VARCHAR(30)            NOT NULL,

    CONSTRAINT voivodeship_pk PRIMARY KEY (id)
--     CONSTRAINT checked_name CHECK (
--         name = 'DOLNOSLASKIE' OR
--         name ='KUJAWSKO_POMORSKIE' OR
--         name = 'LUBELSKIE' OR
--         name = 'LUBUSKIE' OR
--         name = 'LODZKIE'  OR
--         name = 'MALOPOLSKIE' OR
--         name = 'MAZOWIECKIE' OR
--         name = 'OPOLSKIE' OR
--         name = 'PODKARPACKIE' OR
--         name = 'PODLASKIE' OR
--         name = 'POMORSKIE' OR
--         name = 'SLASKIE' OR
--         name = 'SWIETOKRZYSKIE' OR
--         name = 'WARMINSKO_MAZURSKIE' OR
--         name = 'WIELKOPOLSKIE' OR
--         name = 'ZACHODNIOPOMORSKIE'
--     )
);


CREATE TABLE COUNTY (
    id                      NUMBER                 NOT NULL,
    name                    VARCHAR(30)            NOT NULL,
    voivodeship             NUMBER                 NOT NULL,

    CONSTRAINT county_pk PRIMARY KEY (id),
    CONSTRAINT voivodeship_fk FOREIGN KEY (voivodeship)
        REFERENCES VOIVODESHIP(id)
);


CREATE TABLE BOROUGH (
    id                      NUMBER                 NOT NULL,
    name                    VARCHAR(30)            NOT NULL,
    county                  NUMBER                 NOT NULL,

    CONSTRAINT borough_pk PRIMARY KEY (id),
    CONSTRAINT county_fk FOREIGN KEY (county)
        REFERENCES COUNTY(id)
);


CREATE TABLE LOCALITY (
    id                      NUMBER                 NOT NULL,
    name                    VARCHAR(30)            NOT NULL,
    borough                 NUMBER                 NOT NULL,

    CONSTRAINT locality_pk PRIMARY KEY (id),
    CONSTRAINT borough_fk FOREIGN KEY (borough)
        REFERENCES BOROUGH(id)
);


CREATE TABLE LOCALIZATION (
    id                      NUMBER                 NOT NULL,
    address                 TEXT                   NOT NULL,
    latitude                FLOAT                  NOT NULL,
    longitude               FLOAT                  NOT NULL,
    locality                NUMBER                 NOT NULL,

    CONSTRAINT localization_pk PRIMARY KEY (id),
    CONSTRAINT locality_fk FOREIGN KEY (locality)
        REFERENCES LOCALITY(id)
);


CREATE TABLE MONUMENT (
    id                      NUMBER                 NOT NULL,
    name                    TEXT                   NOT NULL,
    additional_description  TEXT,
    creation_date           TEXT                   NOT NULL,
    localization            NUMBER                 NOT NULL,

    CONSTRAINT monument_pk PRIMARY KEY (id),
    CONSTRAINT localization_fk FOREIGN KEY (localization)
        REFERENCES LOCALIZATION(id)
);


CREATE TABLE AUTHOR (
    id                      NUMBER                 NOT NULL,
    name                    TEXT                   NOT NULL,

    CONSTRAINT author_pk PRIMARY KEY (id)
);


CREATE TABLE PHOTO (
    id                      NUMBER                 NOT NULL,
    creation_date           TEXT                   NOT NULL,
    url                     TEXT                   NOT NULL,
    author                  NUMBER                 NOT NULL,
    monument                NUMBER                 NOT NULL,

    CONSTRAINT photo_pk PRIMARY KEY (id),
    CONSTRAINT author_fk FOREIGN KEY (author)
        REFERENCES AUTHOR(id),
    CONSTRAINT monument_fk FOREIGN KEY (monument)
        REFERENCES MONUMENT(id)
);


CREATE TABLE CATEGORY (
    id                      NUMBER                 NOT NULL,
    name                    VARCHAR(60)            NOT NULL,

    CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE CATEGORY_TO_MONUMENT (
    category_id             NUMBER                 NOT NULL,
    monument_id             NUMBER                 NOT NULL,

    CONSTRAINT category_to_monument_pk PRIMARY KEY (category_id, monument_id),
    CONSTRAINT category_fk_1 FOREIGN KEY (category_id)
        REFERENCES CATEGORY(id),
    CONSTRAINT monument_fk_1 FOREIGN KEY (monument_id)
        REFERENCES MONUMENT(id)
);


