package Model;

public class SignedInPerson {
    private static User person = null;

    public void setPerson(User p){
        person = p;
    }

    public User getPerson(){
        return person;
    }
}
