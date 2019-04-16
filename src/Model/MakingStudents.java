package Model;

import java.util.ArrayList;
import java.util.List;

public class MakingStudents {
    public void make(){
        List<Student> mylist = new ArrayList<>();
        Make ms = new Make();
        ms.makeStudent("ali", mylist);
        ms.makeStudent("zahra", mylist);
        ms.makeStudent("mohammadreza", mylist);
        ms.makeStudent("parmida", mylist);
        ms.makeStudent("vali", mylist);
        ms.makeStudent("taghi", mylist);
        ms.makeStudent("baran", mylist);
        ms.makeStudent("naghi", mylist);
        ms.makeStudent("melika", mylist);
        ms.makeStudent("danial", mylist);
        ms.makeStudent("fateme", mylist);
        ms.makeStudent("parsa", mylist);
        ms.makeStudent("mohammad", mylist);
        ms.makeStudent("armin", mylist);
        ms.makeStudent("negin", mylist);
        ms.makeStudent("ashkan", mylist);
        ms.makeStudent("pardis", mylist);
        ms.makeStudent("armita", mylist);
        ms.makeStudent("kiana", mylist);
        ms.makeStudent("keivan", mylist);
        ms.makeStudent("hossein", mylist);
        ms.makeStudent("mohammadhossein", mylist);
        ms.makeStudent("reyhane", mylist);
        ms.makeStudent("kimia", mylist);
        ms.makeStudent("alireza", mylist);
        ms.makeStudent("shaghayegh", mylist);
        ms.makeStudent("kourosh", mylist);
        ms.makeStudent("faranak", mylist);
        ms.makeStudent("sara", mylist);
        ms.makeStudent("arshia", mylist);
        ms.makeStudent("kiarash", mylist);
        ms.makeStudent("rozhin", mylist);
        ms.makeStudent("behzad", mylist);
        ms.makeStudent("ramtin", mylist);
        ms.makeStudent("laleh", mylist);
        ms.makeStudent("ghazal", mylist);
        ms.makeStudent("zarreen", mylist);
        ms.makeStudent("aram", mylist);
        ms.makeStudent("matin", mylist);
        ms.makeStudent("avesta", mylist);
        ms.makeStudent("amir", mylist);
        ms.makeStudent("amirhossein", mylist);
        ms.makeStudent("amirali", mylist);
        ms.makeStudent("amirarsalan", mylist);
        ms.makeStudent("sina", mylist);
        ms.makeStudent("shahin", mylist);
        ms.makeStudent("shahbaz", mylist);
        ms.makeStudent("gelareh", mylist);
        ms.makeStudent("dorsa", mylist);
        ms.makeStudent("yas", mylist);
        ms.makeStudent("ferial", mylist);
        ms.makeStudent("mahsa", mylist);
        ms.makeStudent("amirreza", mylist);
        StudentFileStream sfs = new StudentFileStream();
        sfs.write(mylist);
    }
}
