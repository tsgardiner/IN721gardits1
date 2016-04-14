package bit.gardits1.topartists;

/**
 * Created by Tim on 14/04/2016.
 */
public class TopArtist {

    String name;
    String listenerCount;

    public TopArtist(String name, String listenerCount) {
        this.name = name;
        this.listenerCount = listenerCount;
    }

    public String getName() { return name; }
    public String getListenerCount() { return listenerCount; }

}
