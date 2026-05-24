
package fansapp;


public class Fan {
    private int ID;
    private String firstname;
    private String lastname;
    private String favoriteteam;
    
    public Fan(int ID, String firstname, String lastname, String favoriteteam) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.favoriteteam = favoriteteam;
    }
    
    public int getID() { return ID; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getFavoriteteam() { return favoriteteam; }
    
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setFavoriteTeam(String favoriteteam) { this.favoriteteam = favoriteteam; }
}
