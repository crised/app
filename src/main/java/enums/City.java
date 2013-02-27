package enums;

import util.Constants;

import java.util.*;

public enum City {


    ALL(Region.ALL, "", true), CITYREGIONS15(Region.ARICA, Constants.Fixed.citiesIn, true),

    ARICA(Region.ARICA,"Arica"),

    CITYREGIONS1(Region.TARAPACA, Constants.Fixed.citiesIn, true), IQUIQUE(Region.TARAPACA, "Gran Iquique"),

    POZO(Region.TARAPACA, "Pozo Almonte"), CITYREGIONS2(Region.ANTOFAGASTA, Constants.Fixed.citiesIn, true),

    ANTOFAGASTA(Region.ANTOFAGASTA, "Antofagasta"), CALAMA(Region.ANTOFAGASTA, "Calama"),

    TOCOPILLA(Region.ANTOFAGASTA, "Tocopilla"), TALTAL(Region.ANTOFAGASTA, "Taltal"),

    MEJILLONES(Region.ANTOFAGASTA, "Mejillones"), MARIAELENA(Region.ANTOFAGASTA, "María Elena"),

    CITYREGIONS3(Region.ATACAMA, Constants.Fixed.citiesIn, true), COPIAPO(Region.ATACAMA, "Copiapó"),

    CALDERA(Region.ATACAMA, "Caldera"), TIERRAAMARILLA(Region.ATACAMA, "Tierra Amarilla"),

    CHANARAL(Region.ATACAMA, "Chañaral"), DIEGOALMAGRO(Region.ATACAMA, "Diego de Almagro"),

    ELSALVADOR(Region.ATACAMA, "El Salvador"), VALLENAR(Region.ATACAMA, "Vallenar"), HUASCO(Region.ATACAMA, "Huasco"),

    CITYREGIONS4(Region.COQUIMBO, Constants.Fixed.citiesIn, true), SERENA(Region.COQUIMBO, "Gran La Serena"),

    ANDACOLLO(Region.COQUIMBO, "Andacollo"), VICUNA(Region.COQUIMBO, "Vicuña"), ILLAPEL(Region.COQUIMBO, "Illapel"),

    VILOS(Region.COQUIMBO, "Los Vilos"), SALAMANCA(Region.COQUIMBO, "Salamanca"), OVALLE(Region.COQUIMBO, "Ovalle"),

    COMBARBALA(Region.COQUIMBO, "Combarbalá"), MONTEPATRIA(Region.COQUIMBO, "Monte Patria"),

    PALQUI(Region.COQUIMBO, "El Palqui"), CITYREGIONS5(Region.VALPARAISO, Constants.Fixed.citiesIn, true),

    VALPARAISO(Region.VALPARAISO, "Gran Valparaíso"), SANANTONIO(Region.VALPARAISO, "Gran San Antonio"),

    SANFELIPE(Region.VALPARAISO, "San Felipe"), CASABLANCA(Region.VALPARAISO, "Casablanca"),

    VENTANAS(Region.VALPARAISO, "Las Ventanas"), QUINTERO(Region.VALPARAISO, "Quintero"),

    ANDES(Region.VALPARAISO, "Los Andes"), CALLELARGA(Region.VALPARAISO, "Calle Larga"),

    RINCONADA(Region.VALPARAISO, "Rinconada"), ESTEBAN(Region.VALPARAISO, "San Esteban"),

    LIGUA(Region.VALPARAISO, "La Ligua"), CABILDO(Region.VALPARAISO, "Cabildo"), LIMACHE(Region.VALPARAISO, "Limache"),

    NOGALES(Region.VALPARAISO, "Nogales"), MELON(Region.VALPARAISO, "El Melón"), OLMUE(Region.VALPARAISO, "Olmué"),

    ALGARROBO(Region.VALPARAISO, "Algarrobo"), QUISCO(Region.VALPARAISO, "El Quisco"),

    TABO(Region.VALPARAISO, "El Tabo"), CATEMU(Region.VALPARAISO, "Catemu"), LLAILLAY(Region.VALPARAISO, "Llaillay"),

    PUTAENDO(Region.VALPARAISO, "Putaendo"), ALMENDROS(Region.VALPARAISO, "Villa ALmendros"),

    SANTAMARIA(Region.VALPARAISO, "Santa María"), CITYREGIONS6(Region.OHIGGINS, Constants.Fixed.citiesIn, true),

    RANCAGUA(Region.OHIGGINS, "Gran Rancagua"), CODEGUA(Region.OHIGGINS, "Codegua"), DONIHUE(Region.OHIGGINS, "Doñihue"),

    LOMIRANDA(Region.OHIGGINS, "Lo Miranda"), GRANEROS(Region.OHIGGINS, "Graneros"),

    LASCABRAS(Region.OHIGGINS, "Las Cabras"), MOSTAZAL(Region.OHIGGINS, "Mostazal"), PEUMO(Region.OHIGGINS, "Peumo"),

    PUNTADIAMANTE(Region.OHIGGINS, "Punta Diamante"), QUINTADETILCOCO(Region.OHIGGINS, "Quinta de Tilcoco"),

    RENGO(Region.OHIGGINS, "Rengo"), REQUINOA(Region.OHIGGINS, "Requínoa"), SANVICENTE(Region.OHIGGINS, "San Vicente"),

    PICHILEMU(Region.OHIGGINS, "Pichilemu"), SANFERNANDO(Region.OHIGGINS, "San Fernando"),

    CHIMBARONGO(Region.OHIGGINS, "Chimbarongo"), NANCAGUA(Region.OHIGGINS, "Nancagua"),

    PALMILLA(Region.OHIGGINS, "Palmilla"), SANTACRUZ(Region.OHIGGINS, "Santa Cruz"),

    CITYREGIONS7(Region.MAULE, Constants.Fixed.citiesIn, true), TALCA(Region.MAULE, "Talca"),

    CURICO(Region.MAULE, "Curicó"), LINARES(Region.MAULE, "LINARES"), CONSTITUCION(Region.MAULE, "Constitución"),

    SANCLEMENTE(Region.MAULE, "San Clemente"), CAUQUENES(Region.MAULE, "Cauquenes"),

    HUALANE(Region.MAULE, "Hualañé"), MOLINA(Region.MAULE, "Molina"), TENO(Region.MAULE, "Teno"),

    LONGAVI(Region.MAULE, "Longaví"), PARRAL(Region.MAULE, "Parral"),

    SANJAVIER(Region.MAULE, "San Javier"), VILLAALEGRE(Region.MAULE, "Villa Alegre"),

    CITYREGIONS8(Region.BIOBIO, Constants.Fixed.citiesIn, true), CONCEPCION(Region.BIOBIO, "Gran Concepción"),

    CHILLAN(Region.BIOBIO, "Gran Chillán"), LOSANGELES(Region.BIOBIO, "Los Ángeles"),

    SANTAJUANA(Region.BIOBIO, "Santa Juana"), LEBU(Region.BIOBIO, "Lebu"), ARAUCO(Region.BIOBIO, "Arauco"),

    CANETE(Region.BIOBIO, "Cañete"), CURANILAHUE(Region.BIOBIO, "Curanilahue"), LOSALAMOS(Region.BIOBIO, "Los Álamos"),

    CABRERO(Region.BIOBIO, "Cabrero"), MONTEAGUILA(Region.BIOBIO, "Monte Águila"), LAJA(Region.BIOBIO, "LAJA"),

    MULCHEN(Region.BIOBIO, "Mulchén"), NACIMIENTO(Region.BIOBIO, "Nacimiento"),

    SANTABARBARA(Region.BIOBIO, "Santa Barbará"), HUEPIL(Region.BIOBIO, "Huépil"), YUMBEL(Region.BIOBIO, "Yumbel"),

    BULNES(Region.BIOBIO, "Bulnes"), COELEMU(Region.BIOBIO, "Coelemu"), COIHUECO(Region.BIOBIO, "Coihueco"),

    QUILLON(Region.BIOBIO, "Quillón"), QUIRIHUE(Region.BIOBIO, "Quirihue"), SANCARLOS(Region.BIOBIO, "San Carlos"),

    YUNGAY(Region.BIOBIO, "Yungai"), CITYREGIONS9(Region.ARAUCANIA, Constants.Fixed.citiesIn, true),

    TEMUCO(Region.ARAUCANIA, "Gran Temuco"), LABRANZA(Region.ARAUCANIA, "Labranza"), CARAHUE(Region.ARAUCANIA, "Carahue"),

    CUNCO(Region.ARAUCANIA, "Cunco"), FREIRE(Region.ARAUCANIA, "Freire"), GORBEA(Region.ARAUCANIA, "Gorbea"),

    LAUTARO(Region.ARAUCANIA, "Lautaro"), LONCOCHE(Region.ARAUCANIA, "Loncoche"),

    NUEVAIMPERIAL(Region.ARAUCANIA, "Nueva Imperial"), PITRUFQUEN(Region.ARAUCANIA, "Pitrufquén"),

    PUCON(Region.ARAUCANIA, "Pucón"), VILLARICA(Region.ARAUCANIA, "Villarrica"), ANGOL(Region.ARAUCANIA, "Angol"),

    COLLIPULLU(Region.ARAUCANIA, "Collipulli"), CURACAUTIN(Region.ARAUCANIA, "Curacautín"),

    PUREN(Region.ARAUCANIA, "Purén"), RENAICO(Region.ARAUCANIA, "Renaico"), TRAIGUEN(Region.ARAUCANIA, "Traiguén"),

    VICTORIA(Region.ARAUCANIA, "Victoria"), CITYREGIONS14(Region.RIOS, Constants.Fixed.citiesIn, true),

    VALDIVIA(Region.RIOS, "Valdivia"), FUTRONO(Region.RIOS, "Futrono"), LAUNION(Region.RIOS, "La Unión"),

    LANCO(Region.RIOS, "Lanco"), LOSLAGOS(Region.RIOS, "Los Lagos"), MARIQUINA(Region.RIOS, "Mariquina"),

    PAILLACO(Region.RIOS, "Paillaco"), PANGUIPULLI(Region.RIOS, "Panguipulli"), RIOBUENO(Region.RIOS, "Río Bueno"),

    CITYREGIONS10(Region.LAGOS, Constants.Fixed.citiesIn, true), PUERTOMONTT(Region.LAGOS, "Gran Puerto Montt"),

    CALBUCO(Region.LAGOS, "Calbuco"), FRESIA(Region.LAGOS, "Fresia"), FRUTILLAR(Region.LAGOS, "Frutillar"),

    LOSMUERMOS(Region.LAGOS, "Los Muermos"), LLANQUIHUE(Region.LAGOS, "Llanquihue"), CASTRO(Region.LAGOS, "Castro"),

    ANCUD(Region.LAGOS, "Ancud"), QUELLON(Region.LAGOS, "Quellón"), OSORNO(Region.LAGOS, "Osorno"),

    PURRANQUE(Region.LAGOS, "Purranque"), RIONEGRO(Region.LAGOS, "Río Negro"),

    CITYREGIONS11(Region.AYSEN, Constants.Fixed.citiesIn, true), COYHAIQUE(Region.AYSEN, "Coyhaique"),

    PUERTOAYSEN(Region.AYSEN, "Puerto Aysén"), CITYREGIONS12(Region.MAGALLANES, Constants.Fixed.citiesIn, true),

    PUNTAARENAS(Region.MAGALLANES, "Punta Arenas"), PUERTONATALES(Region.MAGALLANES, "Puerto Natales"),

    CITYREGIONSRM(Region.METROPOLITANA, Constants.Fixed.citiesIn, true), SANTIAGO(Region.METROPOLITANA, "Gran Santiago"),

    MAIPO(Region.METROPOLITANA, "Maipo"), COLINA(Region.METROPOLITANA, "Colina"), LAMPA(Region.METROPOLITANA, "Lampa"),

    BATUCO(Region.METROPOLITANA, "Batuco"), TILTIL(Region.METROPOLITANA, "Tiltil"), BUIN(Region.METROPOLITANA, "Buin"),

    ALTOJAHUEL(Region.METROPOLITANA, "Alto Jahuel"), SANAGUSTIN(Region.METROPOLITANA, "San Agustín"),

    PAINE(Region.METROPOLITANA, "Paine"), HOSPITAL(Region.METROPOLITANA, "Hospital"),

    MELIPILLA(Region.METROPOLITANA, "Melipilla"), CURACAVI(Region.METROPOLITANA, "Curacaví"),

    TALAGANTE(Region.METROPOLITANA, "Talagante"), ELMONTE(Region.METROPOLITANA, "El Monte"),

    ISLAMAIPO(Region.METROPOLITANA, "Isla de Maipo"), LAISLITA(Region.METROPOLITANA, "La Islita"),

    PENAFLOR(Region.METROPOLITANA, "Peñaflor");


    private String label;
    private Region region;
    private Boolean isGroup;


    private City(Region region, String label) {
        this.region = region;
        this.label = label;
    }

    private City(Region region, String label, Boolean isGroup) {
        this.region = region;
        this.label = label + region.getLabel();
        this.isGroup = isGroup;
    }


    public static City[] getCityArrayByRegion(Region region) {

        if (region == Region.ALL) {
            return City.values();
        }

        List<City> citiesList = new ArrayList<City>();

        for (City city : City.values()) {
            if (city.getRegion() == region) {
                citiesList.add(city);
            }
        }

        City[] cities = citiesList.toArray(new City[0]);
        return cities;

    }

    public static List<City> getAllCities() {

        List<City> cityList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        cityList.add(ALL);
        for (City c : City.values()) {
            if (c.isGroup == null) cityList.add(c);
        }
        return cityList;
    }

    public static List<City> getAllRealCities() {

        List<City> cityList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        for (City c : City.values()) {
            if (c.isGroup == null) cityList.add(c);
        }
        return cityList;
    }

    public static List<City> getAllGroups() {

        List<City> groupList = new LinkedList<City>(); //LinkedHashSet los deja en el orden del ENUM, HashSet -> aleatorio
        //Collections.addAll(cityList, City.values());
        for (City c : City.values()) {
            if (c.isGroup) groupList.add(c);
        }
        return groupList;
    }

    public static List<String> getAllCitiesByString() {

        List<City> cityList = getAllCities();
        List<String> cityListByString = new LinkedList<String>();

        for (City c : cityList) {
            cityListByString.add(c.toString());
        }

        Collections.sort(cityListByString); //optional alphabet
        return cityListByString;

    }

    public static List<City> getCitiesByRegion(Region region) {

        List<City> cityListByRegion = new LinkedList<City>();

        if (region == Region.ALL) {

            cityListByRegion = getAllCities();


        } else {
            for (City c : City.values()) {
                if (c.region == region) cityListByRegion.add(c);

            }
        }


        return cityListByRegion;
    }

    public static List<City> getRealCitiesByRegion(Region region) {

        List<City> cityListByRegion = new LinkedList<City>();

        if (region == Region.ALL) {

            cityListByRegion = getAllRealCities();


        } else {
            for (City c : City.values()) {
                if (c.region == region && c.isGroup == null) {
                    cityListByRegion.add(c);
                }

            }
        }


        return cityListByRegion;
    }

    public static List<String> getCitiesByRegionByString(Region region) {

        List<City> cityListByRegion = getCitiesByRegion(region);
        List<String> cityListByRegionByString = new LinkedList<String>();

        for (City c : cityListByRegion) {

            cityListByRegionByString.add(c.toString());

        }

        return cityListByRegionByString;
    }

    public static Map<City, Region> getMapRegionCity() {

        Map<City, Region> regionCityMap = new HashMap<City, Region>();

        for (City c : City.values()) {

            regionCityMap.put(c, c.getRegion());
        }

        return regionCityMap;
    }

    public static Map<String, Integer> getMapRegionCityIntString() {

        Map<City, Region> rCMap = getMapRegionCity();
        Map<String, Integer> rcMapCityIntString = new HashMap<String, Integer>();

        for (Map.Entry<City, Region> entry : rCMap.entrySet()) {

            String key = entry.getKey().toString();
            Integer value = entry.getValue().ordinal();
            rcMapCityIntString.put(key, value);
        }

        return rcMapCityIntString;
    }

    public String getLabel() {
        return label;
    }

    public Region getRegion() {
        return region;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }

    @Override
    public String toString() {
        return label;
    }
}
