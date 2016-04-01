package bit.gardits1.allaboutdunedinlist;

import android.graphics.drawable.Drawable;

/**
 * Created by Tim on 01/04/2016.
 */
public class FunThings {

    Drawable funImage;
    String  funPlace;

    public FunThings(String funPlace, Drawable funImage)
    {
        this.funImage = funImage;
        this.funPlace = funPlace;
    }

    @Override
    public String toString()
    {
        return funPlace;
    }
}
