
package Models;

public class TypeArticleModel {
   private String libbleTypeArt ,mnimoTypeArt;
   private int idArt;

    public String getLibbleTypeArt() {
        return libbleTypeArt;
    }

    public void setLibbleTypeArt(String libbleTypeArt) {
        this.libbleTypeArt = libbleTypeArt;
    }

    public String getMnimoTypeArt() {
        return mnimoTypeArt;
    }

    public void setMnimoTypeArt(String mnimoTypeArt) {
        this.mnimoTypeArt = mnimoTypeArt;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    public TypeArticleModel(String libbleTypeArt, String mnimoTypeArt, int idArt) {
        this.libbleTypeArt = libbleTypeArt;
        this.mnimoTypeArt = mnimoTypeArt;
        this.idArt = idArt;
    }
   
   
}
