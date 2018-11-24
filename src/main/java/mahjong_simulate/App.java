package mahjong_simulate;


import mahjong_simulate.To.Until;
import java.util.*;

public class App {
    public static void main( String[] args ) {

        Until until = new Until(new ArrayList(), new HashMap());

        until.start();

        Iterator<Map.Entry<Integer, ArrayList<String>>> iterator = until.getGfeele().entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<Integer, ArrayList<String>> next = iterator.next();
            Integer key = next.getKey();
            ArrayList arrayList = next.getValue();
            System.out.println("玩家"+key + " :" + arrayList);
        }
    }
}
