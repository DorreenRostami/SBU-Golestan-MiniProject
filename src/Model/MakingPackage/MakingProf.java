package Model.MakingPackage;

import Model.Professor;
import Model.ProfessorFileStream;

import java.util.ArrayList;
import java.util.List;

public class MakingProf {
    public void make(){
        List<Professor> mylist = new ArrayList<>();
        Make mp = new Make();
        mp.makeProfessor("a", mylist);
        mp.makeProfessor("b", mylist);
        mp.makeProfessor("c", mylist);
        mp.makeProfessor("d", mylist);
        mp.makeProfessor("e", mylist);
        new ProfessorFileStream().write(mylist);
    }
}