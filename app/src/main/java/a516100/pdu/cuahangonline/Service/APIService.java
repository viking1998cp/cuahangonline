package a516100.pdu.cuahangonline.Service;

public class APIService {
    private static String Base_Url = "http:/cungnhauhoctap.ml/Sever/";
    public static DataService getService(){
        return APIRetrofitClient .getClient(Base_Url).create(DataService.class);
    }

}
