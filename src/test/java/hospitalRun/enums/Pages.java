package hospitalRun.enums;

import hospitalRun.utils.PropertyReader;

public enum Pages {

    HOME_PAGE(PropertyReader.applicationProperties().getProperty("base.url")),
    LOGIN_PAGE(HOME_PAGE.getUrl() + "/#/login"),
    PATIENT_LISTING_PAGE(HOME_PAGE.getUrl() + "/#/patients"),
    MEDICATION_PAGE(HOME_PAGE.getUrl() + "/#/medication/edit/new");

    private final String url;

    Pages(final String page) {
        this.url = page;
    }

    public String getUrl() {
        return url;
    }
}
