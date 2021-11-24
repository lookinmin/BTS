package Being;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.Year;
import java.util.Date;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.Icon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.*;


public class trend extends JPanel{
    private JFrame frame;
    private ImageIcon icon;
    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cancel.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\minimize2.png");
    ImageIcon img4 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\ranking.png");
    ImageIcon one = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\number1.png");
    ImageIcon two = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\number2.png");
    ImageIcon three = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\number3.png");
    ImageIcon four = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\number4.png");
    ImageIcon five = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\number5.png");
    ImageIcon musinsa = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\musinsa1.png");
    ImageIcon naver = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\naver2.png");
    ImageIcon Next = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\next.png");
    ImageIcon Prev = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\prev.png");
    ImageIcon cody = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cody1.png");
    ImageIcon trend = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\trend1.png");
    ImageIcon home = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\home1.png");
    ImageIcon MainLogo = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\MainLogo.png");
    int i=0;
    private CardLayout cards = new CardLayout();
    private Font f1;

    String today, beforeWeek;

    ArrayList<String> musinsaArr = new ArrayList<>();          //무신사에서 받은 상위 5개 종목 데이터가 들어올 배열

    ArrayList<Integer> Item1 = new ArrayList<>();
    ArrayList<Integer> Item2 = new ArrayList<>();
    ArrayList<Integer> Item3 = new ArrayList<>();
    ArrayList<Integer> Item4 = new ArrayList<>();
    ArrayList<Integer> Item5 = new ArrayList<>();
    Color G_c[] = {Color.CYAN, Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN};
    String clientId = "pkeFje1lOTUYccJW4XNe";
    String clientSecret = "3AqiuZaCqw";
    Map<String, String> requestHeaders = new HashMap<>();

    String apiUrl = "https://openapi.naver.com/v1/datalab/shopping/category/keywords";
    Draw mainPanel1 ;
    Draw mainPanel2 ;
    Draw mainPanel3 ;
    Draw mainPanel4 ;
    Draw mainPanel5 ;

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
    public trend() {
        List<Integer> score1 = new ArrayList<Integer>();
        List<Integer> score2 = new ArrayList<Integer>();
        List<Integer> score3 = new ArrayList<Integer>();
        List<Integer> score4 = new ArrayList<Integer>();
        List<Integer> score5 = new ArrayList<Integer>();

        try {
            Crawling();
        } catch (IOException ignored) {
        }
        API();
        Item1API();
        Item2API();
        Item3API();
        Item4API();
        Item5API();
        score1.addAll(Item1);
        score2.addAll(Item2);
        score3.addAll(Item3);
        score4.addAll(Item4);
        score5.addAll(Item5);


        mainPanel1 = new Draw(score1);
        mainPanel2 = new Draw(score2);
        mainPanel3 = new Draw(score3);
        mainPanel4 = new Draw(score4);
        mainPanel5 = new Draw(score5);

        initialize();
    }

    private void Crawling() throws IOException {
        String URL = "https://search.musinsa.com/ranking/keyword";

        Document document = Jsoup.connect(URL).get();

        Elements elements= document.select("ol[class=\" sranking_list\"]");
        int i = 0;
        for(Element element:elements.select("p[class=\" p_srank\"]")){
            musinsaArr.add(StrSplit(element));
            i++;
            if(i == 5){
                break;
            }
        }
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

        //endDate는 입력 날짜 하루 전까지 보여줌 -> beforeWeek를 오늘 -8일 하면 7일치 저장
    }

    private void Item1API(){
        System.out.println("Item1");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsaArr.get(0)+"\"]}]}";
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
                System.out.println("tmp : "+tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item2API(){
        System.out.println("Item2");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsaArr.get(1)+"\"]}]}";
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
                System.out.println("tmp : "+tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item3API(){
        System.out.println("Item3");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsaArr.get(2)+"\"]}]}";
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
                System.out.println("tmp : "+tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item4API(){
        System.out.println("Item4");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsaArr.get(3)+"\"]}]}";
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
                System.out.println("tmp : "+tmp);
            }
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    private void Item5API(){
        System.out.println("Item5");
        String requestBody = "{\"startDate\":\""+beforeWeek+"\"," + "\"endDate\":\""+today+"\""+
                ",\"timeUnit\":\"date\","+ "\"category\": \"50000000\","+ "\"keyword\": [{\"name\": \"패션의류\", \"param\": [\""+musinsaArr.get(4)+"\"]}]}";
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
                Item5.add(tmp);
                System.out.println("tmp : "+tmp);
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
    /**
     * Initialize the contents of the frame.
     */

    private void initialize() {
        f1 = new Font("이사만루체 Medium",Font.PLAIN,14);
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 750);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        mainPanel1.setBackground(new Color(247,241,255));
        mainPanel2.setBackground(new Color(247,241,255));
        mainPanel3.setBackground(new Color(247,241,255));
        mainPanel4.setBackground(new Color(247,241,255));
        mainPanel5.setBackground(new Color(247,241,255));

        Date Today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM-dd");
        String today_date = date.format(Today);

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat Month = new SimpleDateFormat("MM");
        SimpleDateFormat Day = new SimpleDateFormat("dd");

        JPanel TREND = new JPanel();    // 트랜드보기 ui 설정
        TREND.setBackground(new Color(247,241,255));
        TREND.setBounds(0, 0, 1200, 750);
        frame.getContentPane().add(TREND);
        TREND.setLayout(null);

        JButton GO_Trend = new JButton(trend);
        GO_Trend.setBounds(605, 10, 50, 50);
        TREND.add(GO_Trend);
        GO_Trend.setBorderPainted(false);
        GO_Trend.setFocusPainted(false);
        GO_Trend.setContentAreaFilled(false);

        JButton Go_Cody = new JButton(cody);
        Go_Cody.setBounds(543, 10, 50, 50);
        TREND.add(Go_Cody);
        Go_Cody.setBorderPainted(false);
        Go_Cody.setFocusPainted(false);
        Go_Cody.setContentAreaFilled(false);

        JLabel Logo = new JLabel(MainLogo);
        Logo.setBounds(12, 0, 269, 67);
        TREND.add(Logo);


        JButton Home = new JButton(home);

        Home.setFocusPainted(false);
        Home.setContentAreaFilled(false);
        Home.setBorderPainted(false);
        Home.setBounds(481, 10, 50, 50);
        TREND.add(Home);



        JButton Minimize = new JButton(img2);
        Minimize.setBounds(1112, 10, 32, 32);
        Minimize.setBorderPainted(false);
        Minimize.setFocusPainted(false);
        Minimize.setContentAreaFilled(false);
        TREND.add(Minimize);

        JButton exit = new JButton(img);
        exit.setBounds(1156, 10, 32, 32);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        TREND.add(exit);

        // 랭킹 패널
        JPanel RankingPanel = new JPanel();
        RankingPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(154, 94, 253)));
        RankingPanel.setBounds(30, 192, 240, 244);
        RankingPanel.setBackground(new Color(244, 238, 255));
        TREND.add(RankingPanel);
        RankingPanel.setLayout(null);


        JLabel rank1 = new JLabel("");
        rank1.setBounds(53, 10, 175, 32);
        rank1.setText(musinsaArr.get(0));
        rank1.setFont(f1);
        RankingPanel.add(rank1);

        JLabel rank2 = new JLabel("");
        rank2.setBounds(53, 57, 175, 32);
        rank2.setText(musinsaArr.get(1));
        rank2.setFont(f1);
        RankingPanel.add(rank2);

        JLabel rank3 = new JLabel("");
        rank3.setBounds(53, 104, 175, 32);
        rank3.setText(musinsaArr.get(2));
        rank3.setFont(f1);
        RankingPanel.add(rank3);

        JLabel rank4 = new JLabel("");
        rank4.setBounds(53, 151, 175, 32);
        rank4.setText(musinsaArr.get(3));
        rank4.setFont(f1);
        RankingPanel.add(rank4);

        JLabel rank5 = new JLabel("");
        rank5.setBounds(53, 198, 175, 32);
        rank5.setText(musinsaArr.get(4));
        rank5.setFont(f1);
        RankingPanel.add(rank5);


        JLabel Rankimg = new JLabel(img4); //랭킹 이미지
        Rankimg.setBounds(95, 96, 110, 80);
        TREND.add(Rankimg);

        JLabel Musinsa = new JLabel(musinsa);
        Musinsa.setBounds(40, 446, 210, 32);
        TREND.add(Musinsa);

        JLabel Naver = new JLabel(naver);
        Naver.setBounds(834, 645, 210, 40);
        TREND.add(Naver);






        JLabel lblNewLabel_5 = new JLabel(one);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(12, 10, 32, 32);
        RankingPanel.add(lblNewLabel_5);

        JLabel lblNewLabel_5_1 = new JLabel(two);
        lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5_1.setBounds(12, 57, 32, 32);
        RankingPanel.add(lblNewLabel_5_1);

        JLabel lblNewLabel_5_2 = new JLabel(three);
        lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5_2.setBounds(12, 104, 32, 32);
        RankingPanel.add(lblNewLabel_5_2);

        JLabel lblNewLabel_5_3 = new JLabel(four);
        lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5_3.setBounds(12, 151, 32, 32);
        RankingPanel.add(lblNewLabel_5_3);

        JLabel lblNewLabel_5_4 = new JLabel(five);
        lblNewLabel_5_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5_4.setBounds(12, 198, 32, 32);
        RankingPanel.add(lblNewLabel_5_4);


        //그래프

        JPanel GraphPanel1 = new JPanel();
        GraphPanel1.setBounds(345, 96, 700, 540);
        TREND.add(GraphPanel1);
        GraphPanel1.setLayout(cards);

        GraphPanel1.add(mainPanel1, "1");

        GraphPanel1.add(mainPanel2, "2");

        GraphPanel1.add(mainPanel3, "3");

        GraphPanel1.add(mainPanel4, "4");

        GraphPanel1.add(mainPanel5, "5");




        JButton next = new JButton(Next);
        next.setBounds(1045, 96, 60, 539);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        TREND.add(next);



        JButton prev = new JButton(Prev);
        prev.setBounds(283, 96, 60, 539);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        TREND.add(prev);

        new Draw(G_c[0]);

        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(i>0) {
                    cards.previous(GraphPanel1);
                    i--;
                    new Draw(G_c[i]);
                }
            }
        });

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(i<4) {
                    cards.next(GraphPanel1);
                    i++;
                    new Draw(G_c[i]);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        Minimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setState(Frame.ICONIFIED);
            }
        });

        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == Home) {
                    menu r = new menu();
                    r.setVisible(true);
                    frame.dispose();
                }

            }
        });

        GO_Trend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == GO_Trend) {
                    trend r = new trend();
                    r.setVisible(true);
                    frame.dispose();
                }
            }
        });

        Go_Cody.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == Go_Cody) {
                    cody r = new cody();
                    r.setVisible(true);
                    frame.dispose();
                }

            }
        });

    }


    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}