package PageElements;


public enum PagesWithLocators {

    // URL Directory
    Culturalink_Web("https://www.goculturalink.com", "");


    public final String urlName;
    public final String title;

    PagesWithLocators(String urlName, String title) {
        this.urlName = urlName;
        this.title = title;
    }

    public String getPage() {
        return urlName;
    }

    public String getTitle() {
        return title;
    }

}
