package mahjong_simulate.To;

import lombok.Getter;
import java.util.*;
/**
 * @author:liguozheng
 * @Date:2018/11/15
 * @time:20:59
 * @description:
 */
@SuppressWarnings("all")
@Getter
public class Until {

    private ArrayList<String> mahjong ;            //桌面上未用的牌
    HashMap<Integer, ArrayList<String>> gfeele ;   //玩家列表及其拿到的牌
    ArrayList<String> coun = new ArrayList();      //牌的默认顺序


    public Until(ArrayList<String> mahjong, HashMap<Integer, ArrayList<String>> gfeele) {
        this.mahjong = mahjong;
        this.gfeele =gfeele;
    }

    public void start(){

        //1.存麻将;
        saveM();

        //2.打乱顺序
        Collections.shuffle(mahjong);

        //3.发牌;
        licensing();

    }

    private void saveM(){

        String[] count = {"一","二","三","四","五","六","七","八","九"};
        String[] mold = {"万","筒","条"};
        String[] special = {"东风","南风","西风","北风","红中","白板","发财"};

        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < mold.length ; j++) {
                for (int k = 0; k < count.length ; k++) {
                    mahjong.add(count[k]+mold[j]);
                    if (i ==0){
                        coun.add(count[k]+mold[j]);
                    }
                }
            }
            for (int j = 0; j < special.length; j++) {
                mahjong.add(special[j]);
                if (i ==0){
                    coun.add(special[j]);
                }
            }
        }
    }

    private void licensing(){

        Comparator<String> comparator = (String o1 ,String o2) -> this.coun.indexOf(o1)-this.coun.indexOf(o2);

        //1.每人拿12张牌
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4 ; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == 0 && k == 0) {
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(mahjong.get(0));
                        mahjong.remove(0);
                        gfeele.put(j, arrayList);
                    } else {
                        gfeele.get(j).add(mahjong.get(0));
                        mahjong.remove(0);
                    }
                }
            }
        }

        //2.挑牌
        for (int i = 0; i < 4; i++) {
            if (i==0){
                getGfeele().get(i).add(mahjong.get(0));
                mahjong.remove(0);
                getGfeele().get(i).add(mahjong.get(1));
                mahjong.remove(1);
            }else {
                getGfeele().get(i).add(mahjong.get(0));
                mahjong.remove(0);
            }
        }

        //3.排序
        for (int i = 0; i <4 ; i++) {
            Collections.sort(gfeele.get(i),comparator);
        }
    }
}
