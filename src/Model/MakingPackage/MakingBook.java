package Model.MakingPackage;

import Model.Book;
import Model.BookFileStream;

import java.util.ArrayList;
import java.util.List;

public class MakingBook {
    public void make() {
        List<Book> books = new ArrayList<>();
        Make mb = new Make();
        mb.makeBook("a", "aa", "aaa", books);
        mb.makeBook("b", "bb", "bbb", books);
        mb.makeBook("c", "cc", "ccc", books);
        mb.makeBook("d", "dd", "ddd", books);
        mb.makeBook("e", "ee", "eee", books);
        mb.makeBook("f", "ff", "fff", books);
        mb.makeBook("g", "gg", "fff", books);
        mb.makeBook("h", "aa", "eee", books);
        mb.makeBook("i", "ii", "aaa", books);
        mb.makeBook("j", "jj", "kkk", books);
        mb.makeBook("k", "bb", "bbb", books);
        mb.makeBook("l", "ll", "lll", books);
        mb.makeBook("m", "dd", "ccc", books);
        new BookFileStream().write(books);
    }
}
