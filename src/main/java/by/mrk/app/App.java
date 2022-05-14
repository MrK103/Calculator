package by.mrk.app;

import java.util.Scanner;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое действие");
        String inputValue = scanner.nextLine();
        System.out.println(calculate(inputValue));
    }

    public static String calculate(String input) {
        int firstNumber = 0;
        int secondNumber = 0;
        int answer;
        int operand = 0; // 1 = +, 2 = -, 3 = /, 4 = *;
        String firstRomanNumber = null;
        String secondRomanNumber = null;
        String[] splitInput = new String[0];
        String output = null;
        boolean dataType = true; // true - int, false - roman

        input = input.replaceAll(" ", ""); //delete all space in "input"

        if (input.contains("+")) {
            operand = 1;
            splitInput = input.split("\\+");
        } else if (input.contains("-")) {
            operand = 2;
            splitInput = input.split("-");
        } else if (input.contains("*")) {
            operand = 3;
            splitInput = input.split("\\*");
        } else if (input.contains("/")) {
            operand = 4;
            splitInput = input.split("/");
        }

        if (splitInput.length != 2 || splitInput[0] == null || splitInput[1] == null) {
            throw new RuntimeException("\"Введено не корректное значение\"");
        }

        try {
            firstNumber = Integer.parseInt(splitInput[0]);
            secondNumber = Integer.parseInt(splitInput[1]);
            if (firstNumber > 10 || secondNumber > 10) {
                throw new RuntimeException("Ваше введенное число больше 10");
            }
        } catch (NumberFormatException ex) {
            firstRomanNumber = splitInput[0];
            secondRomanNumber = splitInput[1];
            dataType = false;
        }

        if (!dataType) {
            String val1 = getIntNumber(firstRomanNumber);
            String val2 = getIntNumber(secondRomanNumber);
            if (val1.equals("-1") || val2.equals("-1")) {
                throw new RuntimeException("Ваше римское число больше 10 либо введено некорректно");
            }
            firstNumber = Integer.parseInt(val1);
            secondNumber = Integer.parseInt(val2);
        }

        answer = count(operand, firstNumber, secondNumber);

        if (!dataType) {
            try {
                output = getRomanNumber(answer);
            } catch (IllegalArgumentException ex) {
                System.out.println("В римской системе нет отрицательных чисел");
                System.exit(0);
            }
        } else output = String.valueOf(answer);
        return output;
    }

    private static String getIntNumber(String romanNumber) {
        String value;
        switch (romanNumber) {
            case "I" -> value = "1";
            case "II" -> value = "2";
            case "III" -> value = "3";
            case "IV" -> value = "4";
            case "V" -> value = "5";
            case "VI" -> value = "6";
            case "VII" -> value = "7";
            case "VIII" -> value = "8";
            case "IX" -> value = "9";
            case "X" -> value = "10";
            default -> value = "-1";
        }
        return value;
    }

    private static String getRomanNumber(int number) {
        return join("", nCopies(number, "I"))
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
    }

    private static int count(int operand, int first, int second) {
        int answer;
        switch (operand) {
            case 1 -> answer = first + second;
            case 2 -> answer = first - second;
            case 3 -> answer = first * second;
            case 4 -> {
                if (second == 0) throw new ArithmeticException("Делить на ноль нельзя");
                answer = first / second;
            }
            default -> {
                System.out.println("some error");
                answer = -1;
            }
        }
        return answer;
    }
}
