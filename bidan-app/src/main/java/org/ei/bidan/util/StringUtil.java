package org.ei.bidan.util;

import com.google.common.base.Strings;

import org.apache.commons.lang3.text.WordUtils;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.upperCase;

public class StringUtil {
    public static String humanize(String value) {
        return capitalize(replace(value, "_", " "));
    }

    public static String replaceAndHumanize(String value, String oldCharacter, String newCharacter) {
        return humanize(replace(value, oldCharacter, newCharacter));
    }

    public static String replaceAndHumanizeWithInitCapText(String value, String oldCharacter, String newCharacter) {
        return humanize(WordUtils.capitalize(replace(value, oldCharacter, newCharacter)));
    }

    public static String humanizeAndDoUPPERCASE(String value) {
        return upperCase(humanize(value));
    }

    public static String getValueFromNumber(String value) { return Strings.isNullOrEmpty(value) ? "0" : value; }

    public static String splitCamelCase(String s) {
        return humanize(s).replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

}