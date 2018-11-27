package re.vianneyfaiv.assemblee.model;

/**
 * From http://www.assemblee-nationale.fr/opendata/Schemas_Entites/AMO/Schemas_Organes.html
 */
public enum PoliticalBodyType {

    ASSEMBLEE_NATIONALE("ASSEMBLEE"),
    MISSION_INFORMATION("MISINFO"),
    ASSEMBLEE_PARLEMENTAIRE_INTERNATIONALE("API"),
    OFFICE_PARLEMENTAIRE_OU_DELEGATION_MIXTE("OFFPAR"),
    AUTRES_COMMISSIONS_PERMANENTES("COMNL"),
    ORGANISME_EXTRA_PARLEMENTAIRE("ORGEXTPARL"),
    COMMISSIONS_ENQUETES("CNPE"),
    ORGANISME_INTERNATIONAL("ORGAINT"),
    COMMISSIONS_MIXTES_PARITAIRES("CMP"),
    AUTRES_CONSEILS("ASSEXT"),
    COMMISSIONS_PERMANENTES_LEGISLATIVES("COMPER"),
    CONSEIL_CONSTITUTIONNEL("CONSTITU"),
    COMMISSIONS_SPECIALES("CNPS"),
    CONSEIL_ECONOMIQUE_SOCIAL_ENVIRONNEMENTAL("CES"),
    COUR_JUSTICE_REPUBLIQUE("CJR"),
    SENAT("SENAT"),
    DELEGATION_BUREAU("DELEGBUREAU"),
    MANDAT_EUROPEEN("EUROPE"),
    DELEGATION_PARLEMENTAIRE("DELEG"),
    MINISTERE("MINISTERE"),
    GROUPE_AMITIE("GA"),
    PRESIDENCE_REPUBLIQUE("PRESREP"),
    GROUPE_ETUDES("GE"),
    ASSEMMBLEE_TOM("ASSTOM"),
    GROUPE_ETUDES_VOCATION_INTERNATIONALE("GEVI"),
    CONSEIL_GENERAL_OU_DEPARTEMENTAL("DEPARTEMENT"),
    GROUPE_POLITIQUE("GP"),
    CONSEIL_REGIONAL("REGION"),
    HAUTE_COUR_JUSTICE("HCJ"),
    INTERCOMMUNALITE("INTCO"),
    MISSIONS_INFO_COMMUNES("MISINFOCOM"),
    CONSEIL_MUNICIPALE("COMMUNE"),
    MISSION_INFO_CONFERENCE_PRESIDENTS("MISINFOPRE"),
    /* The next ones are not found in the documentation*/
    COMMISSION_SENAT("COMSENAT"),
    COMMISSION_SPECIALE_SENAT("COMSPSENAT"),
    CONFERENCE_PRESIDENTS("CONFPT"),
    DELEGATION_SENATORIALE("DELEGSENAT"),
    GOUVERNEMENT("GOUVERNEMENT"),
    GROUPE_SENAT("GROUPESENAT"),
    PARTI_POLITIQUE("PARPOL");

    private static final PoliticalBodyType[] ALL_VALUES = PoliticalBodyType.values();
    private String code;

    PoliticalBodyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PoliticalBodyType fromCode(String value) {
        for(PoliticalBodyType pbt : ALL_VALUES) {
            if(pbt.getCode().equalsIgnoreCase(value)) {
                return pbt;
            }
        }

        throw new UnsupportedOperationException(
                "The code " + value + " is not supported!"
        );
    }
}
