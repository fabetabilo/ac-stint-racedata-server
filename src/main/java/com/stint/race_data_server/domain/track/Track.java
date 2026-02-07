package com.stint.race_data_server.domain.track;

import java.util.List;

/**
 * Representa un circuito junto a sus layouts. Si el circuito no tiene layout especifico, 
 * se considera esa unica opcion como layout.
 */
public class Track {
    
    private final String id;
    private final String name;
    private final String country;
    private final String city;
    private final List<Layout> layouts;

    public Track(String id, String name, String country, String city, List<Layout> layouts) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.layouts = layouts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public List<Layout> getLayouts() {
        return layouts;
    }
}
