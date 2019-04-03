package Model;

public class SignedInPerson {
    private static Object person = null;

    public void setPerson(Object p){
        person = p;
    }

    public Object getPerson(){
        return person;
    }
}
