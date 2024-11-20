
import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;
    private String password;
    private List<Perk> perksPosted;

    private List<Membership> memberships;

    public User() {
        this.memberships = new ArrayList<>();
        this.perksPosted = new ArrayList<>();
    }

    public User(String userName, String password, List<Perk> perks){
        this.userName = userName;
        this.password = password;
        this.memberships = new ArrayList<>();
        perksPosted = new ArrayList<>(perks);
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addMembership(Membership membership) {
        memberships.add(membership);
    }

    public void addPerkpost (Perk perk){
        perksPosted.add(perk);
    }

    public void addPerkPosts(List<Perk> perks){
        perksPosted.addAll(perks);
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword (){
        return password;
    }

    public List<Perk> getPerkposts (){
        return perksPosted;
    }

    public String toString(){
        String str = "";
        for (Perk perk : perksPosted){
            str += perk.toString() + "\n";
        }
        return str;
    }
}
