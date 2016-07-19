package com.laundryguy.booking.model.enums;

/**
 * @Author maninder
 */
public enum UserClientType {

    Invalid("invalid", -1),
    DesktopWebsite("desktopWebsite", 0),
    MobileWebsite("mobileWebsite", 1),
    Android("android", 3),
    Blackberry("blackberry", 5),
    WindowsApp("windows", 8),
    IPhone("iphone", 9),
    IPad("ipad", 10);

    String value;
    int identifier;

    UserClientType(String value, int identifier) {
        this.value = value;
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public int getIdentifier() {
        return identifier;
    }

    public static UserClientType fromIdentifier(int identifier) {
        switch (identifier) {
            case 0:
                return DesktopWebsite;
            case 1:
                return MobileWebsite;
            case 3:
                return Android;
            case 5:
                return Blackberry;
            case 8:
                return WindowsApp;
            case 9:
                return IPhone;
            case 10:
                return IPad;
            default:
                return Invalid;
        }
    }

    public static boolean isWeb(int identifier) {
        return (DesktopWebsite.identifier == identifier || MobileWebsite.identifier == identifier);
    }

    public static boolean isWeb(UserClientType userClientType) {
        return isWeb(userClientType.getIdentifier());
    }

    public static boolean isMobileApp(int identifier) {
        return (Android.identifier == identifier || IPhone.identifier == identifier || WindowsApp.identifier == identifier);
    }

    public static boolean isMobileApp(UserClientType userClientType) {
        return isMobileApp(userClientType.getIdentifier());
    }
}
