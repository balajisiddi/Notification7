package com.sectorseven.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Real
 *
 */
public class CurrencyInWords {

    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 10000;
    private static final int LAKH = 100000;
    private static final int CRORE = 10000000;

    private static String numberToWords(int number) {
        int intQuotient = (int) (number / TEN);
        StringBuilder word = new StringBuilder();

        if (intQuotient > 0) {
            if (intQuotient == 1 && (number % TEN) > 0) {
                word.append(wordInTens(number % TEN));
                return word.toString();
            }
            word.append(wordInTenMultiples(intQuotient));
        }
        int remainder = number % TEN;
        if (remainder > 0) {
            if (word.length() > 0) {
                word.append(" ");
            }
            word.append(numberInWords(remainder));
        }

        return word.toString();

    }

    private static String wordInTenMultiples(int number) {
        String[] words = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        return words[number - 1];
    }

    private static String wordInTens(int number) {
        String[] words = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen" };
        return words[number - 1];

    }

    private static String numberInWords(int number) {
        String[] words = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
        return words[number - 1];
    }

    public static String convertNumberToWords(BigDecimal number, boolean prefix, boolean suffix) {
        String numberInWords = convertNumberToWords(number);
        if (prefix) {
            if (number.compareTo(BigDecimal.ONE) == 0) {
                numberInWords = "Rupee " + numberInWords;
            } else {
                numberInWords = "Rupees " + numberInWords;
            }
        }
        if (suffix) {
            numberInWords += " Only";
        }
        return numberInWords;
    }

    public static String convertNumberToWords(BigDecimal parameter) {

        boolean negativeAmount = false;

        if (parameter.signum() == -1) {
            BigDecimal tempValue = new BigDecimal("0");
            negativeAmount = true;
            tempValue = parameter.abs();
            parameter = tempValue;
        }

        StringBuilder word = new StringBuilder();

        String numberString = parameter.setScale(2, RoundingMode.HALF_UP).toPlainString();

        double number = Double.parseDouble(numberString);

        int quotient = (int) (number / CRORE);
        if (quotient > 0) {
            word.append(convertNumberToWords(new BigDecimal(quotient)) + " Crore ");
        }
        number = number % CRORE;

        quotient = (int) (number / LAKH);
        if (quotient > 0) {
            word.append(numberToWords(quotient) + " Lakh ");
        }
        number = number % LAKH;

        quotient = (int) (number / THOUSAND);
        if (quotient > 0) {
            word.append(numberToWords(quotient) + " Thousand ");
        }
        number = number % THOUSAND;

        quotient = (int) (number / HUNDRED);
        if (quotient > 0) {
            word.append(numberToWords(quotient) + " Hundred ");
        }
        number = number % HUNDRED;

        if (number != 0) {
            word.append(numberToWords((int) number) + " ");
        }

        int decimal = 0;
        String val;
        if (number % 1 != 0) {
            String decimalInWords = Double.toString(number);
            int index = decimalInWords.indexOf(".");
            decimalInWords = decimalInWords.substring(index + 1);
            if (decimalInWords.length() > 2) {
                val = decimalInWords.substring(0, 2);
                decimal = Integer.parseInt(val);
                if (Integer.parseInt(decimalInWords.substring(2, THREE)) > FIVE) {
                    decimal++;
                }
            } else {
                decimal = Integer.parseInt(decimalInWords);
            }
            if (decimalInWords.length() == 1) {
                decimal *= TEN;
            }
            if (word.toString().trim().length() > 0) {
                word.append("& ");
            }

            word.append(numberToWords(decimal));
            if (decimal > 1) {
                word.append(" Paise");
            } else {
                word.append(" Paisa");
            }
        }

        if (word.toString().trim().length() == 0) {
            return "Zero";
        }

        String result = word.toString().trim();
        if (negativeAmount) {
            result = "Minus " + result;
        }

        return result;

    }
}
