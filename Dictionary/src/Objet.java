import java.io.Serializable;

public class Objet implements Serializable {
    private String Mot_E;
    private String Mot_F;
    private String Type_E;
    private String Exemple_E;
    private String Exemple_F;



    public Objet() {
    }

    public Objet(String mot_E, String mot_F, String type_E, String exemple_E, String exemple_F) {
        Mot_E = mot_E;
        Mot_F = mot_F;
        Type_E = type_E;
        Exemple_E = exemple_E;
        Exemple_F = exemple_F;
    }

    public String getMot_E() {
        return Mot_E;
    }
    public void setMot_E(String mot_E) {
        Mot_E = mot_E;
    }

    public String getMot_F() {
        return Mot_F;
    }
    public void setMot_F(String mot_F) {
        Mot_F = mot_F;
    }

    public String getType_E() {
        return Type_E;
    }
    public void setType_E(String type_E) {
        Type_E = type_E;
    }

    public String getExemple_E() {
        return Exemple_E;
    }
    public void setExemple_E(String exemple_E) {
        Exemple_E = exemple_E;
    }
    
    public String getExemple_F() {
        return Exemple_F;
    }
    public void setExemple_F(String exemple_F) {
        Exemple_F = exemple_F;
    }

    @Override
    public String toString() {
        return Mot_E  +  Mot_F + Type_E  + Exemple_E + Exemple_F ;
    }

    public String Affichage() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(Mot_E);
        buffer.append("\t");
        buffer.append(Mot_F);
        buffer.append("\t");
        buffer.append(Type_E);
        buffer.append("\t");
        buffer.append(Exemple_E);
        buffer.append("\t");
        buffer.append(Exemple_F);
        buffer.append("\t");
        buffer.append("\n");

        return buffer.toString();
    }
    public String AffichageL() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(Mot_E);
        buffer.append(",");
        buffer.append(Mot_F);
        buffer.append(",");
        buffer.append(Type_E);
        buffer.append(",");
        buffer.append(Exemple_E);
        buffer.append(",");
        buffer.append(Exemple_F);
        buffer.append(",");

        return buffer.toString();
    }
}