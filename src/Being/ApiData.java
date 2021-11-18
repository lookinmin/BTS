package Being;

public class ApiData {
    private String title;
    private String period;
    private double ratio;

    ApiData(String title, String period, double ratio){
        this.title = title;
        this.period = period;
        this.ratio = ratio;
    }

    public String getTitle() {
        return title;
    }

    public String getPeriod() {
        return period;
    }

    public double getRatio() {
        return ratio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}