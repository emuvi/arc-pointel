/*
GNU Lesser General Public License

Translatrix - General Access To Language Resource Bundles
Copyright (C) 2002  Howard A Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package pin.libout;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Translatrix {

    private ResourceBundle langResources;
    private String bundleName;

    public Translatrix(String bundle) {
        bundleName = bundle;
        try {
            langResources = ResourceBundle.getBundle(bundleName);
        } catch (MissingResourceException mre) {
            log("MissingResourceException while loading language file", mre);
        }
    }

    public void setBundleName(String bundle) {
        bundleName = bundle;
        try {
            langResources = ResourceBundle.getBundle(bundleName);
        } catch (MissingResourceException mre) {
            log("MissingResourceException while loading language file", mre);
        }
    }

    public void setLocale(Locale locale) {
        if (bundleName == null) {
            return;
        }
        if (locale != null) {
            try {
                langResources = ResourceBundle.getBundle(bundleName, locale);
            } catch (MissingResourceException mre1) {
                try {
                    langResources = ResourceBundle.getBundle(bundleName);
                } catch (MissingResourceException mre2) {
                    log("MissingResourceException while loading language file", mre2);
                }
            }
        } else {
            try {
                langResources = ResourceBundle.getBundle(bundleName);
            } catch (MissingResourceException mre) {
                log("MissingResourceException while loading language file", mre);
            }
        }
    }

    public void setLocale(String sLanguage, String sCountry) {
        if (sLanguage != null && sCountry != null) {
            setLocale(new Locale(sLanguage, sCountry));
        }
    }

    public String get(String key) {
        if (langResources == null || bundleName == null) {
            return key;
        } else {
            try {
                return langResources.getString(key);
            } catch (Exception e) {
                return key;
            }
        }
    }

    private void log(String internalMessage, Exception e) {
        System.err.println(internalMessage);
        System.err.println(e.getMessage());
        e.printStackTrace(System.err);
    }

}
