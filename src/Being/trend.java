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
import java.text.ParseException;
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

    ArrayList<String> musinsa = new ArrayList<>();          //무신사에서 받은 상위 5개 종목 데이터가 들어올 배열

    ArrayList<Integer> Item1 = new ArrayList<>();
    ArrayList<Integer> Item2 = new ArrayList<>();
    ArrayList<Integer> Item3 = new ArrayList<>();
    ArrayList<Integer> Item4 = new ArrayList<>();
    ArrayList<Integer> Item5 = new ArrayList<>();

    String clientId = "pkeFje1lOTUYccJW4XNe";
    String clientSecret = "3AqiuZaCqw";

    String apiUrl = "https://openapi.naver.com/v1/datalab/shopping/category/keywords";
    Map<String, String> requestHeaders = new HashMap<>();
    String today, beforeWeek;
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
        JButton btnTRENDTOMAIN = new JButton("\uBA54\uC778\uBA54\uB274\uB85C\uB3CC\uC544\uAC00\uAE30");
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
        String URL = "https://search.musinsa.com/ranking/keyword";

        Document document = Jsoup.connect(URL).get();

        Elements elements= document.select("ol[class=\" sranking_list\"]");
        int i = 0;
        for(Element element:elements.select("p[class=\" p_srank\"]")){
            musinsa.add(StrSplit(element));
            i++;
            if(i == 5){
                break;
            }

        }
        System.out.println(musinsa);
    }

    private static String StrSplit(Element element) {
        String str = element.toString();
        String[] str1 = str.split("\\.");
        String[] str2 = str1[1].split("<");
        return str2[0];
    }


    private void API(){
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        Date Today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        today = date.format(Today);

        Calendar week = Calendar.getInstance();
        week.add(Calendar.DATE , -7);
        beforeWeek = new java.text.SimpleDateFormat("yyyy-MM-dd").format(week.getTime()); //일주일 전
        System.out.println(beforeWeek);

        //endDate는 입력 날짜 하루 전까지 보여줌 -> beforeWeek를 오늘 -8일 하면 7일치 저장

        Item1API();
        Item2API();
        Item3API();
        Item4API();
        Item5API();

//        for(int i=0;i<trendData1.size();i++){
//            for(int j=0;j<trendData1.get(i).size();j++){
//                System.out.println(trendData1.get(i).get(j).getTitle());
//            }
//            System.out.println("---");
//        }
    }

    private void Item1API(){
        System.out.println("Item1");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsa.get(0)+"\"]}]}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            System.out.println(jsonArray1);
            for(Object o :jsonArray1) {
                JSONObject object = (JSONObject) o;

                double dRatio;
                Long lRatio;

                if (object.get("ratio").getClass().getName().equals("java.lang.Double")) {
                    dRatio = (double) object.get("ratio");
                }
                else {
                    lRatio = (Long) object.get("ratio");
                    dRatio = (double) lRatio;
                }

                int tmp = (int)dRatio;
                Item1.add(tmp);
                System.out.println(tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item2API(){
        System.out.println("Item2");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsa.get(1)+"\"]}]}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            System.out.println(jsonArray1);
            for(Object o :jsonArray1) {
                JSONObject object = (JSONObject) o;

                double dRatio;
                Long lRatio;

                if (object.get("ratio").getClass().getName().equals("java.lang.Double")) {
                    dRatio = (double) object.get("ratio");
                }
                else {
                    lRatio = (Long) object.get("ratio");
                    dRatio = (double) lRatio;
                }

                int tmp = (int)dRatio;
                Item2.add(tmp);
                System.out.println(tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }

    }

    private void Item3API(){
        System.out.println("Item3");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsa.get(2)+"\"]}]}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            System.out.println(jsonArray1);
            for(Object o :jsonArray1) {
                JSONObject object = (JSONObject) o;

                double dRatio;
                Long lRatio;

                if (object.get("ratio").getClass().getName().equals("java.lang.Double")) {
                    dRatio = (double) object.get("ratio");
                }
                else {
                    lRatio = (Long) object.get("ratio");
                    dRatio = (double) lRatio;
                }

                int tmp = (int)dRatio;
                Item3.add(tmp);
                System.out.println(tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item4API(){
        System.out.println("Item4");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsa.get(3)+"\"]}]}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            System.out.println(jsonArray1);
            for(Object o :jsonArray1) {
                JSONObject object = (JSONObject) o;

                double dRatio;
                Long lRatio;

                if (object.get("ratio").getClass().getName().equals("java.lang.Double")) {
                    dRatio = (double) object.get("ratio");
                }
                else {
                    lRatio = (Long) object.get("ratio");
                    dRatio = (double) lRatio;
                }

                int tmp = (int)dRatio;
                Item4.add(tmp);
                System.out.println(tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item5API(){
        System.out.println("Item5");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsa.get(4)+"\"]}]}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);

        System.out.println(responseBody);

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("data");
            System.out.println(jsonArray1);
            for(Object o :jsonArray1) {
                JSONObject object = (JSONObject) o;

                double dRatio;
                Long lRatio;

                if (object.get("ratio").getClass().getName().equals("java.lang.Double")) {
                    dRatio = (double) object.get("ratio");
                }
                else {
                    lRatio = (Long) object.get("ratio");
                    dRatio = (double) lRatio;
                }

                int tmp = (int)dRatio;
                Item5.add(tmp);
                System.out.println(tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
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