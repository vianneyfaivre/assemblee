CREATE TABLE IF NOT EXISTS acteurs (
    acteur_id VARCHAR(50) PRIMARY KEY,
    civilite VARCHAR(5),
    prenom VARCHAR(50),
    nom VARCHAR(50)
);

create table IF NOT EXISTS organes (
    organe_id VARCHAR(50) PRIMARY KEY,
    type VARCHAR(50),
    libelle VARCHAR,
    date_debut DATE,
    date_fin DATE,
    regime VARCHAR(50),
    legislature SMALLINT
);

create table IF NOT EXISTS mandats (
    mandat_id VARCHAR(50) PRIMARY KEY,
    acteur_id VARCHAR(50) REFERENCES acteurs,
    date_debut DATE,
    date_prise_fonction DATE,
    date_fin DATE,
    num_place_hemicycle SMALLINT,
    qualite VARCHAR,
    cause VARCHAR(100)
);

create table IF NOT EXISTS mandats_organes (
    mandat_id VARCHAR(50) references mandats,
    organe_id VARCHAR(50) references organes,

    PRIMARY KEY(mandat_id, organe_id)
);

create table IF NOT EXISTS scrutins (
    scrutin_id VARCHAR(50) PRIMARY KEY,
    titre VARCHAR,
    numero SMALLINT,
    organe_id VARCHAR(50) references organes,
    legislature SMALLINT,
    session_id VARCHAR(50),
    seance_id VARCHAR(50),
    date_scrutin DATE,
    type_vote VARCHAR(50),
    sort VARCHAR(50),
    demandeur VARCHAR(300),
    mode_publication_votes VARCHAR(50),
    resultat_nombre_votants SMALLINT, -- nombre de votants présents lors de la séance. ex: 90
    resultat_pour SMALLINT, -- nombre de votants POUR ex: 60
    resultat_contre SMALLINT, -- nombre de votants CONTRE ex:30
    resultat_abstention SMALLINT, -- nombre de votants ayant choisi l'abstention ex:5
    resultat_non_votant SMALLINT -- nombre de votants qui n'ont pas voté ex:10
    -- mise au point
    -- ventilation votes
);