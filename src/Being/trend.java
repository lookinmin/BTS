package Being;

import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class trend extends JFrame{

    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JTextField LOGIN_PW_INSERT;

    private ImageIcon icon;

    ArrayList<ArrayList<String>> data = new ArrayList<>(); //크롤링할 때 쓰는 변수

    String clientId = " XB21ieQrpllHq9Hs4dgO";
    String clientSecret = " 7reQtMZRkV";

    String apiUrl = "https://openapi.naver.com/v1/datalab/search";

    Map<String, String> requestHeaders = new HashMap<>();

    String today, beforeWeek;

    ArrayList<ArrayList<ApiData>> trendData1 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData2 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData3 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData4 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData5 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData6 = new ArrayList<>();
    ArrayList<ArrayList<ApiData>> trendData7 = new ArrayList<>();
    //trendData는 상위5개 항목을 7일치 title, period, ratio저장

    /**
     * Launch the application.
     */


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    trend window = new trend();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public trend() throws IOException {
        initialize();
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JPanel TREND = new JPanel();    // 트랜드보기 ui 설정
        TREND.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(TREND);
        TREND.setLayout(null);
        JButton btnTRENDTOMAIN = new JButton("\uBA54\uC778\uBA54\uB274\uB85C \uB3CC\uC544\uAC00\uAE30");
        btnTRENDTOMAIN.setBounds(598, 417, 175, 23);
        TREND.add(btnTRENDTOMAIN);

        Crawling();
        API();

        btnTRENDTOMAIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu r = new menu();
                r.setVisible(true);
                frame.dispose();
            }
        });

    }

    private void Crawling() throws IOException {
        String URL = "https://datalab.naver.com/";
        Document doc = Jsoup.connect(URL).get();

        Elements elem = doc.select("li[class=\" list\"]");

        int cnt = 0;

        ArrayList<String> data6 = new ArrayList<>();
        ArrayList<String> data7 = new ArrayList<>();
        ArrayList<String> data8 = new ArrayList<>();
        ArrayList<String> data9 = new ArrayList<>();
        ArrayList<String> data10 = new ArrayList<>();
        ArrayList<String> data11 = new ArrayList<>();
        ArrayList<String> data12 = new ArrayList<>();

        int n6 = 1; int n7 = 1; int n8 = 1; int n9 = 1; int n10 = 1; int n11 = 1; int n12 = 1;
        for(Element e: elem.select("span")) {
            cnt++;
            if(cnt<=60){
                if (n6 <= 5) {
                    data6.add(e.text());
                }
                n6++;
            }
            else if(cnt<=70){
                if (n7 <= 5) {
                    data7.add(e.text());
                }
                n7++;
            }
            else if(cnt<=80){
                if (n8 <= 5) {
                    data8.add(e.text());
                }
                n8++;
            }
            else if(cnt<=90){
                if (n9<= 5) {
                    data9.add(e.text());
                }
                n9++;
            }
            else if(cnt<=100){
                if (n10 <= 5) {
                    data10.add(e.text());
                }
                n10++;
            }
            else if(cnt<=110){
                if (n11 <= 5) {
                    data11.add(e.text());
                }
                n11++;
            }
            else{
                if (n12 <= 5) {
                    data12.add(e.text());
                }
                n12++;
            }

        }
//        data.add(data1); data.add(data2); data.add(data3); data.add(data4); data.add(data5);
        data.add(data6); data.add(data7); data.add(data8);
        data.add(data9); data.add(data10); data.add(data11); data.add(data12);

        System.out.println(data.get(0));
    }

    private void API(){
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        Date Today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        today = date.format(Today);

        Calendar week = Calendar.getInstance();
        week.add(Calendar.DATE , -8);
        beforeWeek = new java.text.SimpleDateFormat("yyyy-MM-dd").format(week.getTime()); //일주일 전
        System.out.println(beforeWeek);

        //endDate는 입력 날짜 하루 전까지 보여줌 -> beforeWeek를 오늘 -8일 하면 7일치 저장

        Day1API();
        Day2API();
        Day3API();
        Day4API();
        Day5API();
        Day6API();
        Day7API();

        for(int i=0;i<trendData1.size();i++){
            for(int j=0;j<trendData1.get(i).size();j++){
                System.out.println(trendData1.get(i).get(j).getTitle());
            }
            System.out.println("---");
        }
    }

    private void Day1API(){
        System.out.println("Day1");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(0).get(0)+"\"," + "\"keywords\":[\""+data.get(0).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(0).get(1)+"\"," + "\"keywords\":[\""+data.get(0).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(0).get(2)+"\"," + "\"keywords\":[\""+data.get(0).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(0).get(3)+"\"," + "\"keywords\":[\""+data.get(0).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(0).get(4)+"\"," + "\"keywords\":[\""+data.get(0).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }

                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        System.out.println(data1.size());

        trendData1.add(data1);
        trendData1.add(data2);
        trendData1.add(data3);
        trendData1.add(data4);
        trendData1.add(data5);
    }
    private void Day2API(){
        System.out.println("Day2");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(1).get(0)+"\"," + "\"keywords\":[\""+data.get(1).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(1).get(1)+"\"," + "\"keywords\":[\""+data.get(1).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(1).get(2)+"\"," + "\"keywords\":[\""+data.get(1).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(1).get(3)+"\"," + "\"keywords\":[\""+data.get(1).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(1).get(4)+"\"," + "\"keywords\":[\""+data.get(1).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        trendData2.add(data1);
        trendData2.add(data2);
        trendData2.add(data3);
        trendData2.add(data4);
        trendData2.add(data5);
    }
    private void Day3API(){
        System.out.println("Day3");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(2).get(0)+"\"," + "\"keywords\":[\""+data.get(2).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(2).get(1)+"\"," + "\"keywords\":[\""+data.get(2).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(2).get(2)+"\"," + "\"keywords\":[\""+data.get(2).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(2).get(3)+"\"," + "\"keywords\":[\""+data.get(2).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(2).get(4)+"\"," + "\"keywords\":[\""+data.get(2).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);


        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        trendData3.add(data1);
        trendData3.add(data2);
        trendData3.add(data3);
        trendData3.add(data4);
        trendData3.add(data5);
    }
    private void Day4API(){
        System.out.println("Day4");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(3).get(0)+"\"," + "\"keywords\":[\""+data.get(3).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(3).get(1)+"\"," + "\"keywords\":[\""+data.get(3).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(3).get(2)+"\"," + "\"keywords\":[\""+data.get(3).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(3).get(3)+"\"," + "\"keywords\":[\""+data.get(3).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(3).get(4)+"\"," + "\"keywords\":[\""+data.get(3).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {

                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        trendData4.add(data1);
        trendData4.add(data2);
        trendData4.add(data3);
        trendData4.add(data4);
        trendData4.add(data5);
    }
    private void Day5API(){
        System.out.println("Day5");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(4).get(0)+"\"," + "\"keywords\":[\""+data.get(4).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(4).get(1)+"\"," + "\"keywords\":[\""+data.get(4).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(4).get(2)+"\"," + "\"keywords\":[\""+data.get(4).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(4).get(3)+"\"," + "\"keywords\":[\""+data.get(4).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(4).get(4)+"\"," + "\"keywords\":[\""+data.get(4).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        trendData5.add(data1);
        trendData5.add(data2);
        trendData5.add(data3);
        trendData5.add(data4);
        trendData5.add(data5);
    }
    private void Day6API(){
        System.out.println("Day6");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(5).get(0)+"\"," + "\"keywords\":[\""+data.get(5).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(5).get(1)+"\"," + "\"keywords\":[\""+data.get(5).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(5).get(2)+"\"," + "\"keywords\":[\""+data.get(5).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(5).get(3)+"\"," + "\"keywords\":[\""+data.get(5).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(5).get(4)+"\"," + "\"keywords\":[\""+data.get(5).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }


        trendData6.add(data1);
        trendData6.add(data2);
        trendData6.add(data3);
        trendData6.add(data4);
        trendData6.add(data5);
    }
    private void Day7API(){
        System.out.println("Day7");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"keywordGroups\":[{\"groupName\":\""+data.get(6).get(0)+"\"," + "\"keywords\":[\""+data.get(6).get(0)+"\"]}," +
                "{\"groupName\":\""+data.get(6).get(1)+"\"," + "\"keywords\":[\""+data.get(6).get(1)+"\"]}," +
                "{\"groupName\":\""+data.get(6).get(2)+"\"," + "\"keywords\":[\""+data.get(6).get(2)+"\"]}," +
                "{\"groupName\":\""+data.get(6).get(3)+"\"," + "\"keywords\":[\""+data.get(6).get(3)+"\"]}," +
                "{\"groupName\":\""+data.get(6).get(4)+"\"," + "\"keywords\":[\""+data.get(6).get(4)+"\"]}],"+
                "\"timeUnit\":\"date\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        ArrayList<ApiData> data1 = new ArrayList<>();
        ArrayList<ApiData> data2 = new ArrayList<>();
        ArrayList<ApiData> data3 = new ArrayList<>();
        ArrayList<ApiData> data4 = new ArrayList<>();
        ArrayList<ApiData> data5 = new ArrayList<>();

        int cnt = 0;

        try {
            for(int i=0;i<5;i++) {
                JSONParser jsonParse = new JSONParser(); //JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
                JSONObject jsonObj = (JSONObject) jsonParse.parse(responseBody); //JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.
                JSONArray personArray = (JSONArray) jsonObj.get("results"); //result를 먼저 파싱
                JSONObject personObject = (JSONObject) personArray.get(i);

                JSONArray tmpArr = (JSONArray) personObject.get("data");

                for (Object o : tmpArr) {
                    JSONObject object = (JSONObject) o;
                    String title = (String) personObject.get("title");
                    String period = (String) object.get("period");
                    double dRatio;
                    Long lRatio;

                    if (object.get("ratio").getClass().getName().equals("java.lang.Double")){
                        dRatio = (double) object.get("ratio");
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, dRatio));
                            case 1 -> data2.add(new ApiData(title, period, dRatio));
                            case 2 -> data3.add(new ApiData(title, period, dRatio));
                            case 3 -> data4.add(new ApiData(title, period, dRatio));
                            case 4 -> data5.add(new ApiData(title, period, dRatio));
                            default -> {
                            }
                        }
                    }
                    else{
                        lRatio = (Long) object.get("ratio");
                        double newRatio = (double) lRatio;
                        switch (cnt / 7) {
                            case 0 -> data1.add(new ApiData(title, period, newRatio));
                            case 1 -> data2.add(new ApiData(title, period, newRatio));
                            case 2 -> data3.add(new ApiData(title, period, newRatio));
                            case 3 -> data4.add(new ApiData(title, period, newRatio));
                            case 4 -> data5.add(new ApiData(title, period, newRatio));
                            default -> {
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        trendData7.add(data1);
        trendData7.add(data2);
        trendData7.add(data3);
        trendData7.add(data4);
        trendData7.add(data5);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}