-- results must be empty

select * from assemblee.scrutins
where resultat_nombre_votants != (resultat_pour + resultat_contre + resultat_abstention);

select * from assemblee.mandats
where date_debut > date_fin;

select * from assemblee.mandats m, assemblee.acteurs a
where a.acteur_id not in (select distinct acteur_id from assemblee.mandats);