package com.laundryguy.account.session.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ganesh
 */
@Component
public class SessionConfig {

/*
    */
/* The given value represents total hours */

    private static final String PROP_desktopDefaultExpiryHours = "userprofile.desktop.default.expiry.hours";

    @Value("${" + PROP_desktopDefaultExpiryHours + ":6}")
    private int desktopDefaultExpiryHours;

    public int getDesktopDefaultExpiryHours() {
        return desktopDefaultExpiryHours;
    }


    private static final String PROP_desktopRemeberMeExpiryHours = "userprofile.desktop.remeberMe.expiry.hours";

    @Value("${" + PROP_desktopRemeberMeExpiryHours + ":7}")
    private int desktopRemeberMeExpiryHours;

    public int getDesktopRemeberMeExpiryHours() {
        return desktopRemeberMeExpiryHours;
    }


    private final String PROP_desktopDefaultLapseHours = "userprofile.desktop.default.lapse.hours";
    @Value("${" + PROP_desktopDefaultLapseHours + ":5}")
    private int desktopDefaultLapseHours;

    public int getDesktopDefaultLapseHours() {
        return desktopDefaultLapseHours;
    }


    private final String PROP_desktopRemeberMeLapseHours = "userprofile.desktop.remeberMe.lapse.hours";

    @Value("${" + PROP_desktopRemeberMeLapseHours + ":6}")
    private int desktopRemeberMeLapseHours;

    public int getDesktopRemeberMeLapseHours() {
        return desktopRemeberMeLapseHours;
    }


    private final String PROP_refreshTokenWindowOpeningTime = "userprofile.session.refreshToken.window.openingPercentage";
    @Value("${" + PROP_refreshTokenWindowOpeningTime + ":90}")
    public int refreshTokenWindowOpeningTime;

    public int getRefreshTokenWindowOpeningTime() {
        return refreshTokenWindowOpeningTime;
    }



/*
    private static final String PROP_mobileWebsiteDefaultExpiryHours =
            "userprofile.mobile.default.expiry.hours";
    @Value("${" + PROP_mobileWebsiteDefaultExpiryHours + "}")
    private int mobileWebsiteDefaultExpiryHours;


    private static final String PROP_mobileWebsiteRememberMeExpiryHours = "userprofile.mobile.remeberMe.expiry.hours";
    @Value("${" + PROP_mobileWebsiteRememberMeExpiryHours + "}")
    private int mobileWebsiteRemeberMeExpiryHours;

    private static final String PROP_androidExpiryDays = "userprofile.android.expiry.days";
    @Value("${" + PROP_androidExpiryDays + "}")
    private int androidExpiryDays;

    private static final String PROP_blackberryExpiryDays = "userprofile.blackberry.expiry.days";
    @Value("${" + PROP_blackberryExpiryDays + "}")
    private int blackberryExpiryDays;

    private static final String PROP_windowsExpiryDays = "userprofile.windows.expiry.days";
    @Value("${" + PROP_windowsExpiryDays + "}")
    private int windowsExpiryDays;

    private static final String PROP_iPhoneExpiryDays = "userprofile.iphone.expiry.days";
    @Value("${" + PROP_iPhoneExpiryDays + "}")
    private int iPhoneExpiryDays;

    private static final String PROP_iPadExpiryDays = "userprofile.ipad.expiry.days";
    @Value("${" + PROP_iPadExpiryDays + "}")
    private int iPadExpiryDays;

    private static final String PROP_androidReMeExpiryDays = "userprofile.android.remeberMe.expiry.days";
    @Value("${" + PROP_androidReMeExpiryDays + "}")
    private int androidReMeExpiryDays;

    private static final String PROP_blackberryReMeExpiryDays = "userprofile.blackberry.remeberMe.expiry.days";
    @Value("${" + PROP_blackberryReMeExpiryDays + "}")
    private int blackberryReMeExpiryDays;

    private static final String PROP_windowsReMeExpiryDays = "userprofile.windows.remeberMe.expiry.days";
    @Value("${" + PROP_windowsReMeExpiryDays + "}")
    private int windowsReMeExpiryDays;

    private static final String PROP_iPhoneReMeExpiryDays = "userprofile.iphone.remeberMe.expiry.days";
    @Value("${" + PROP_iPhoneReMeExpiryDays + "}")
    private int iPhoneReMeExpiryDays;

    private static final String PROP_iPadReMeExpiryDays = "userprofile.ipad.remeberMe.expiry.days";
    @Value("${" + PROP_iPadReMeExpiryDays + "}")
    private int iPadReMeExpiryDays;

    */
/* The given value represents total hours *//*


    private final String PROP_mobileWebsiteDefaultLapseHours = "userprofile.mobile.default.lapse.hours";
    @Value("${" + PROP_mobileWebsiteDefaultLapseHours + "}")
    private int mobileWebsiteLapseHours;

    private final String PROP_mobileWebsiteRememberMeLapseHours = "userprofile.mobile.remeberMe.lapse.hours";
    @Value("${" + PROP_mobileWebsiteRememberMeLapseHours + "}")
    private int mobileWebsiteRemeberMeLapseHours;

    private final String PROP_androidLapseDays = "userprofile.android.lapse.days";
    @Value("${" + PROP_androidLapseDays + "}")
    private int androidLapseDays;

    private final String PROP_blackberryLapseDays = "userprofile.blackberry.lapse.days";
    @Value("${" + PROP_blackberryLapseDays + "}")
    private int blackberryLapseDays;

    private final String PROP_windowsLapseDays = "userprofile.windows.lapse.days";
    @Value("${" + PROP_windowsLapseDays + "}")
    private int windowsLapseDays;

    private final String PROP_iPhoneLapseDays = "userprofile.iphone.lapse.days";
    @Value("${" + PROP_iPhoneLapseDays + "}")
    private int iPhoneLapseDays;

    private final String PROP_iPadLapseDays = "userprofile.ipad.lapse.days";
    @Value("${" + PROP_iPadLapseDays + "}")
    private int iPadLapseDays;


    private final String PROP_androidReMeLapseDays = "userprofile.android.remeberMe.lapse.days";
    @Value("${" + PROP_androidReMeLapseDays + "}")
    private int androidRemeberMeLapseDays;

    private final String PROP_blackberryReMeLapseDays = "userprofile.blackberry.remeberMe.lapse.days";
    @Value("${" + PROP_blackberryReMeLapseDays + "}")
    private int blackberryRemeberMeLapseDays;

    private final String PROP_windowsReMeLapseDays = "userprofile.windows.remeberMe.lapse.days";
    @Value("${" + PROP_windowsReMeLapseDays + "}")
    private int windowsRemeberMeLapseDays;

    private final String PROP_iPhoneReMeLapseDays = "userprofile.iphone.remeberMe.lapse.days";
    @Value("${" + PROP_iPhoneReMeLapseDays + "}")
    private int iPhoneRemeberMeLapseDays;

    private final String PROP_iPadReMeLapseDays = "userprofile.ipad.remeberMe.lapse.days";
    @Value("${" + PROP_iPadReMeLapseDays + "}")
    private int iPadRemeberMeLapseDays;

    public int getWindowsReMeExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_windowsReMeExpiryDays, windowsReMeExpiryDays);
    }

    public int getWindowsRemeberMeLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_windowsReMeLapseDays,windowsRemeberMeLapseDays );
    }

    public int getAndroidRemeberMeLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_androidReMeLapseDays, androidRemeberMeLapseDays);
    }

    public int getAndroidReMeExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_androidReMeExpiryDays , androidReMeExpiryDays);
    }

    public int getBlackberryRemeberMeLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_blackberryReMeLapseDays , blackberryRemeberMeLapseDays);
    }

    public int getBlackberryReMeExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_blackberryReMeExpiryDays,blackberryReMeExpiryDays) ;
    }

    public int getiPadRemeberMeLapseDays() {

        return DynamicPropertiesReader.readInt(PROP_iPadReMeLapseDays,iPadRemeberMeLapseDays) ;
    }

    public int getiPadReMeExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_iPadReMeExpiryDays,iPadReMeExpiryDays);
    }

    public int getiPhoneRemeberMeLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_iPhoneReMeLapseDays,iPhoneRemeberMeLapseDays);
    }

    public int getiPhoneReMeExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_iPhoneReMeExpiryDays , iPhoneReMeExpiryDays);
    }

    public int getMobileWebsiteDefaultExpiryHours() {
        return DynamicPropertiesReader.readInt(PROP_mobileWebsiteDefaultExpiryHours,
                mobileWebsiteDefaultExpiryHours);
    }
    public int getMobileWebsiteRemeberMeExpiryHours() {
        return DynamicPropertiesReader.readInt(PROP_mobileWebsiteRememberMeExpiryHours, mobileWebsiteRemeberMeExpiryHours);
    }

    public int getAndroidExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_androidExpiryDays, androidExpiryDays);
    }
    public int getBlackberryExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_blackberryExpiryDays, blackberryExpiryDays);
    }
    public int getWindowsExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_windowsExpiryDays, windowsExpiryDays);
    }
    public int getIPhoneExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_iPhoneExpiryDays, iPhoneExpiryDays);
    }
    public int getIPadExpiryDays() {
        return DynamicPropertiesReader.readInt(PROP_iPadExpiryDays, iPadExpiryDays);
    }



    public int getMobileWebsiteLapseHours() {
        return DynamicPropertiesReader.readInt(PROP_mobileWebsiteDefaultLapseHours,
                mobileWebsiteLapseHours);
    }
    public int getAndroidLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_androidLapseDays, androidLapseDays);
    }
    public int getBlackberryLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_blackberryLapseDays, blackberryLapseDays);
    }
    public int getWindowsLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_windowsLapseDays, windowsLapseDays);
    }
    public int getIPhoneLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_iPhoneLapseDays, iPhoneLapseDays);
    }
    public int getIPadLapseDays() {
        return DynamicPropertiesReader.readInt(PROP_iPadLapseDays, iPadLapseDays);
    }




    public int getMobileWebsiteRemeberMeLapseHours() {
        return DynamicPropertiesReader.readInt(PROP_mobileWebsiteRememberMeLapseHours, mobileWebsiteRemeberMeLapseHours);
    }

    private static class DynamicPropertiesReader {
         static int readInt(String propKey, int defaultValue) {
            String value = DynamicProperties.getGlobalConfiguration(propKey);
            if (StringUtils.isBlank(value))
                return defaultValue;
            else {
                return Integer.parseInt(value);
            }
        }

        String readString(String propKey, String defaultValue) {
            String value = DynamicProperties.getGlobalConfiguration(propKey);
            return StringUtils.isBlank(value) ? defaultValue : value;
        }
    }
*/

}
