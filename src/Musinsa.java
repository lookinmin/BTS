
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Musinsa extends JPanel {
    private static final int MAX_SCORE = 100;
    private static final int WIDTH = 900;              //프레임 가로
    private static final int HEIGHT = 700;              //프레임 세로
    private static final int BORDER_GAP = 50;           //x, y를 그리는 간격 선분이 프레임의 겉면과 얼마나 떨어져 있는 지
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(0, 0, 0, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 10;
    private static final int Y_HATCH_CNT = 10;
    private List<Integer> scores;

    String today, beforeWeek;

    ArrayList<String> musinsa = new ArrayList<>();          //무신사에서 받은 상위 5개 종목 데이터가 들어올 배열

    ArrayList<Integer> Item1 = new ArrayList<>();
    String clientId = "pkeFje1lOTUYccJW4XNe";
    String clientSecret = "3AqiuZaCqw";
    Map<String, String> requestHeaders = new HashMap<>();

    String apiUrl = "https://openapi.naver.com/v1/datalab/shopping/category/keywords";

    public Musinsa(List<Integer> scores) {
        this.scores = scores;
    }
    public Musinsa() throws IOException {
        initialize();
    }
    private void initialize() throws IOException{
        createAndShowGui();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    private void createAndShowGui() throws IOException {
        List<Integer> scores = new ArrayList<Integer>();
        Crawling();
        API();

        Item1API();

        for (int i = 0; i < 7 ; i++) {
            scores.add(Item1.get(i));
        }
        Musinsa mainPanel = new Musinsa(scores);

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Musinsa graph = new Musinsa();
                    graph.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

}