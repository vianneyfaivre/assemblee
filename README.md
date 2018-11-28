I'm using French Assemblée Nationale Open Data in order to play with Spring Boot 2

# TODO 

* Enable FK
* remove Scrutin#objet because it's the same as Scrutin#titre
* standard error handling in rest
* hateoas in rest
* pagination on mandates
* try with latest data (15e legislature)
* rest layer (list of députés, get député details, search for a député, http://www2.assemblee-nationale.fr/qui, etc)
* rest layer documentation with Spring REST Docs
* enum for some fields
* import details of scrutins when "modePublicationDesVotes": "DecompteNominatif"
* front app that consumes rest layer
* basic info in README.md
* create DB indexes on columns that are searched with
* global search (député, organe, mandat, etc.)
* person details : contact info, birth info
* copy documentation from official doc into model classes

# Data integrity issues

* missing political body types in doc

# Useful links

* https://github.com/regardscitoyens/nosdeputes.fr/blob/master/doc/opendata.md
* http://www.assemblee-nationale.fr/opendata/Index_pub.html
* http://www.assemblee-nationale.fr/encyclopedie/loi.asp
* https://github.com/regardscitoyens/nosdeputes.fr/blob/master/doc/data_model.md