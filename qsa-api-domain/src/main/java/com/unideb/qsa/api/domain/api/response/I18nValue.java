package com.unideb.qsa.api.domain.api.response;

/**
 * Domain class for an i18n element value. Contains the locale and the corresponding translation.
 */
public class I18nValue {

    private String locale;
    private String i18n;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }
}
