/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class Test {
    public static void main(String[] args){
        HatebaseSearch hs = new HatebaseSearch();
        RequestModel rm = new RequestModel();
        rm.setVocabulary("nigga");
        System.out.println(hs.getHatebaseSearchResultJSON(rm,"C:/Users/ionut.tirlea/Desktop/Master/Dizertatie/examplenew.json"));
    }
}
