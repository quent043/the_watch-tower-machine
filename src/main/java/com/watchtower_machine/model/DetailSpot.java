package com.watchtower_machine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Surf_Spots")
public class DetailSpot {

    @Id
    @Column(name = "id", unique = true)
    @NonNull
    private int id;
    @NonNull
    @Column(name ="msw_id", unique = true)
    private int mswId;
    @Column(name = "nom")
    private String nom;
    @Column(name = "houle")
    private String houle;
    @Column(name = "orientation_houle")
    private String orientationHoule;
    @Column(name = "hauteur_saturation")
    private double hauteurSaturation;
    @Column(name = "type")
    private String type;
    @Column(name = "region")
    private String region;
    @Column(name = "maree")
    private String maree;
    @Column(name = "vent")
    private String vent;
    @Column(name = "direction_vent")
    private String directionVent;
    @Column(name = "coordonnees")
    private String coordonnees;
    @Column(name = "selected")
    private Boolean isSelected;

    public DetailSpot(@JsonProperty("id") int id,
                      @JsonProperty("mswId") int mswId,
                      @JsonProperty("nom") String nom,
                      @JsonProperty("houle") String houle,
                      @JsonProperty("orientationHoule") String orientationHoule,
                      @JsonProperty("hauteurSaturation") double hauteurSaturation,
                      @JsonProperty("type") String type,
                      @JsonProperty("region") String region,
                      @JsonProperty("maree") String maree,
                      @JsonProperty("vent") String vent,
                      @JsonProperty("directionVent") String directionVent,
                      @JsonProperty("coordonnees") String coordonnees,
                      @JsonProperty("isSelected") Boolean isSelected) {
        this.id = id;
        this.mswId = mswId;
        this.nom = nom;
        this.houle = houle;
        this.orientationHoule = orientationHoule;
        this.hauteurSaturation = hauteurSaturation;
        this.type = type;
        this.region = region;
        this.maree = maree;
        this.vent = vent;
        this.directionVent = directionVent;
        this.coordonnees = coordonnees;
        this.isSelected = isSelected;
    }

    public DetailSpot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMswId() {
        return mswId;
    }

    public void setMswId(int mswId) {
        this.mswId = mswId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHoule() {
        return houle;
    }

    public void setHoule(String houle) {
        this.houle = houle;
    }

    public String getOrientationHoule() {
        return orientationHoule;
    }

    public void setOrientationHoule(String orientationHoule) {
        this.orientationHoule = orientationHoule;
    }

    public double getHauteurSaturation() {
        return hauteurSaturation;
    }

    public void setHauteurSaturation(double hauteurSaturation) {
        this.hauteurSaturation = hauteurSaturation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMaree() {
        return maree;
    }

    public void setMaree(String maree) {
        this.maree = maree;
    }

    public String getVent() {
        return vent;
    }

    public void setVent(String vent) {
        this.vent = vent;
    }

    public String getDirectionVent() {
        return directionVent;
    }

    public void setDirectionVent(String directionVent) {
        this.directionVent = directionVent;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
