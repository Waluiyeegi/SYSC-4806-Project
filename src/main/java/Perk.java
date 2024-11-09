import java.util.ArrayList;
import java.util.List;

public class Perk {
    private String name;
    private List<Restriction> restrictions;
    private int upvotes;
    private int downvotes;
    //expiry date
    //private User postedBy;

    public Perk (){
        restrictions = new ArrayList<>();
        upvotes = 0;
        downvotes = 0;
    }

    public Perk(String name, List<Restriction> restrictions){
        this.name = name;
        restrictions = new ArrayList<>(restrictions);
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

    public List<Restriction> getRestrictinos(){
        return restrictions;
    }

    public int getUpvotes (){
        return upvotes;
    }

    public int getDownvotes (){
        return downvotes;
    }
}
