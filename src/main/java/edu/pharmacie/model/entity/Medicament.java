package edu.pharmacie.model.entity;

public class Medicament {
    private Long id;
    private String nom;
    private Type type;
    private double prix;


    public Medicament() {
    }

    public Medicament(Long id, String nom, Type type, double prix) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }


    @Override
    public String toString() {
         return "Medicament{" +
                "ID:" + id +
                ", NOM:" + nom +
                ", TYPE:" + type +
                ", PRIX:" + prix +
                '}';
    }
}
