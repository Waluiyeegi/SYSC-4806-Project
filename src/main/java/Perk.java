import java.util.ArrayList;
import java.util.List;

public class Perk {
    private String name;
    private List<Restriction> restrictions;
    private int upvotes;
    private int downvotes;
    private int code;
    //expiry date
    //private User postedBy;

    public Perk (){
        restrictions = new ArrayList<>();
        upvotes = 0;
        downvotes = 0;
    }

    public Perk(String name, List<Restriction> restrictions, int code){
        this.name = name;

        if(restrictions == null)
        {
            this.restrictions = new ArrayList<>();
        }
        else
        {
            this.restrictions = new ArrayList<>(restrictions);
        }
        this.code = code;
        upvotes = 0;
        downvotes = 0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addRestriction (Restriction restriction){
        restrictions.add(restriction);
    }

    public void addRestrictions (List<Restriction> restriction){
        restrictions.addAll(restriction);
    }

    public void upvote(){
        upvotes += 1;
    }

    public void downvote(){
        downvotes += 1;
    }

    public String getName (){
        return name;
    }

    public int getCode() {return code;}

    public List<Restriction> getRestrictinos(){
        return restrictions;
    }

    public int getUpvotes (){
        return upvotes;
    }

    public int getDownvotes (){
        return downvotes;
    }

    public String toString(){
        String str = "Name: " + getName() + " ";
        for(Restriction restriction : restrictions){
            str += restriction.toString() + " ";
        }
        str += "Upvotes: " + upvotes + " ";
        str += "Downvotes: " + downvotes + " ";
        str += "Code: " + code;
        return str;
    }
}
