package cz.muni.fi.pv243.lesson02.students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Country {

    SLOVAKIA("Slovakia", "Bratislava", "Kosice", "Banska Bystrica", "Zilina", "Martin", "Prievidza", "Other"), CZECH_REPUBLIC(
            "Czech republic", "Praha", "Brno", "Ostrava", "Jihlava", "Plzen", "Other");

    private final String label;
    private final List<String> cities;

    private Country(String label, String... cities) {
        this.label = label;
        List<String> tempCities = new ArrayList<String>(cities.length);
        for (String city : cities) {
            tempCities.add(city);
        }
        this.cities = Collections.unmodifiableList(tempCities);
    }

    public List<String> getCities() {
        return cities;
    }

    public String getLabel() {
        return label;
    }

}
