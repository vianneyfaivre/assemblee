# Init of the Assemblée Database

Steps to follow:

1. Database configuration in src/main/resources/application.properties

2. Paths to files that will be read during batch import in src/main/resources/application.properties
* File format to use for mandats, acteurs, organes: AMO50 (ex: http://data-prod.assemblee-nationale.fr/static/openData/repository/15/amo/acteurs_mandats_organes_divises/AMO50_acteurs_mandats_organes_divises_XV.json.zip)
* File format to use for scrutins (ex: http://data.assemblee-nationale.fr/static/openData/repository/15/loi/scrutins/Scrutins_XV.json.zip)

3. Run 'mvn spring-boot:run' and wait for batch completion