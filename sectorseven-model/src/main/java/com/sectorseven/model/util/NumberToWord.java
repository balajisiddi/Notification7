package com.sectorseven.model.util;

/**
 * @author Real
 *
 */
public class NumberToWord {

    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int SEVEN = 7;
    private static final int NINE = 10;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final String EMPTY = " ";

    public String convert(String number) {
        String word = "";
        boolean addzero = true;
        String twodigitword = "";
        int len1 = number.length();
        int[] split = {0, 2, THREE, FIVE, SEVEN, NINE };
        String[] temp = new String[split.length];

        String[] htlc = {"", "Hundred", "Thousand", "Lakh", "Crore" };

        if (len1 > split[split.length - 1]) {
            System.exit(0);
        }

        for (int l = 1; l < split.length; l++) {
            if (number.length() == split[l]) {
                addzero = false;
            }

        }

        if (addzero) {
            String tempValue = null;
            tempValue = "0" + number;
            number = tempValue;
        }

        int j = 0;
        int len = number.length();
        while (split[j] < len) {
            int beg = len - split[j + 1];
            int end = beg + split[j + 1] - split[j];
            temp[j] = number.substring(beg, end);
            j = j + 1;
        }
        for (int k = 0; k < j; k++) {
            twodigitword = convertOnesTwos(temp[k]);
            if (k >= 1) {
                if (twodigitword.trim().length() != 0) {
                    word = twodigitword + EMPTY + htlc[k] + EMPTY + word;
                }

            } else {
                word = twodigitword;
            }

        }
        return word;
    }

    private static String convertOnesTwos(String t) {

        final String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

        final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

        String word = "";
        int num = Integer.parseInt(t);
        if (num % TEN == 0) {
            word = tens[num / TEN] + EMPTY + word;
        } else if (num < TWENTY) {
            word = ones[num] + EMPTY + word;
        } else {
            word = tens[(num - (num % TEN)) / TEN] + word;
            word = word + EMPTY + ones[num % TEN];
        }
        return word;
    }
}
