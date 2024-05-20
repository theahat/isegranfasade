package theahat.isegranfasade;

import jakarta.persistence.*;

@Entity
@Table(name = "produkt")
public class Produkt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String navn;
    private String bio;
    private Double pris;
    private String levering;
    private String bilde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }

    public String getLevering() {
        return levering;
    }

    public void setLevering(String levering) {
        this.levering = levering;
    }

    public String getBilde() {
        return bilde;
    }

    public void setBilde(String bilde) {
        this.bilde = bilde;
    }
}
