package Model;

import java.util.ArrayList;
import java.util.List;

public class MakingFood {
    public void make(){
        List<Food> foods = new ArrayList<>();
        Make mf = new Make();
        mf.makeFood("قورمه سبزی", 1500, foods);
        mf.makeFood("شنینسل", 500, foods);
        mf.makeFood("قیمه", 1500, foods);
        mf.makeFood("قیمه بادمجان", 1600, foods);
        mf.makeFood("ماکارونی", 1000, foods);
        mf.makeFood("ناگت", 400, foods);
        mf.makeFood("کباب کوبیده", 1600, foods);
        mf.makeFood("جوجه کباب", 1500, foods);
        mf.makeFood("زرشک پلو مرغ", 1400, foods);
        mf.makeFood("عدس پلو", 600, foods);
        mf.makeFood("ماهی تن", 800, foods);
        mf.makeFood("ماهی قزل آلا", 1000, foods);
        FoodFileStream ffs = new FoodFileStream();
        ffs.write(foods,"Resources/Files/foods");
    }
}
