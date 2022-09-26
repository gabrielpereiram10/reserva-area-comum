package com.gabriel.reservaareacomum.domain.valueObjects;

import com.gabriel.reservaareacomum.domain.exceptions.InvalidCPFException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CPF {

    private final String value;
    public CPF(String value) {
        if (!value.matches("^\\d{11}$") || !checkDigits(value)) {
            throw new InvalidCPFException();
        };

        this.value = value;
    }

    private static boolean checkDigits(String value) {
        List<Integer> digits = new ArrayList<>();
        List<Integer> digitsToCompare = new ArrayList<>();
        for (int i = 0; i < value.length(); i++) {
            Integer digit = Integer.parseInt(value.substring(i, i+1));
            if (i > 8) digitsToCompare.add(digit);
            else digits.add(digit);
        }

        List<Integer> validationDigits = generateValidationDigits(digits, new ArrayList<>());
        return digitsToCompare.equals(validationDigits);
    }

    private static List<Integer> generateValidationDigits(List<Integer> digits, List<Integer> validationDigits) {
        if (validationDigits.size() == 2) return validationDigits;

        int sum = 0;
        for (int i = 0, j = 10; j >= 2 ; i++, j--) {
            sum += digits.get(i) * j;
        }

        int validationDigit = 11 - (sum % 11);
        digits.add(validationDigit);
        validationDigits.add(validationDigit);
        return generateValidationDigits(digits.subList(1, digits.size()), validationDigits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
