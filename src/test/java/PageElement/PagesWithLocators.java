package PageElement;

public enum PagesWithLocators {
    Culturalink_Web("https://www.culturalinksync.com", "");
    public final String urlName;
    public final String  Title;

    PagesWithLocators(String urlName, String Title) {
        this.urlName=urlName;
        this.Title = Title;
    }
    public String getPage() {
        return urlName;
    }

    public String getTitle() {
        return Title;
    }
}
