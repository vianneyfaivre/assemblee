DROP TABLE IF EXISTS mandats;
DROP TABLE IF EXISTS acteurs;
DROP TABLE IF EXISTS mandats_organes;

CREATE TABLE acteurs (
    acteur_id VARCHAR(50) PRIMARY KEY,
    civilite VARCHAR(5),
    prenom VARCHAR(50),
    nom VARCHAR(50)
);

create table mandats (
    mandat_id VARCHAR(50) PRIMARY KEY,
    acteur_id VARCHAR(50), -- REFERENCES acteurs(acteur_id),
    organe_type VARCHAR(50),
    date_debut DATE,
    date_prise_fonction DATE,
    date_fin DATE,
    num_place_hemicycle SMALLINT,
    cause VARCHAR(100)
);

create table mandats_organes (
    mandat_id VARCHAR(50),
    organe_id VARCHAR(50),

    PRIMARY KEY(mandat_id, organe_id)
)



