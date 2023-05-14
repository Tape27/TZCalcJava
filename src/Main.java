import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите пример");
            String example = in.nextLine();
            String answer = Calc(example);
            System.out.println(answer);
        }
    }
    public static String Calc(String input) throws Exception {
        boolean flag = true; // флаг отвечающий за то, какие цифры мы используем
        char operation = '0';
        int indexoperation = 0;
        String firstnum = "";
        String secondnum = "";
        String answer;
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) { // Нахождение знака арифметической операции
            if (arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*') {
                operation = arr[i];
                indexoperation = i;
                break;
            }
        }
        if (operation == '0') { throw new Exception("Строка не является математической операцией!"); }

        for (int i = 0; i < indexoperation; i++) { // Нахождение 1го значения
            firstnum += arr[i];
        }
        for (int i = indexoperation + 1; i < arr.length; i++) { // Нахожденпие 2го значения
            secondnum += arr[i];
        }

        flag = CheckNums(String.valueOf(arr[0]));

        if (flag == true) {
            answer = RimNums(firstnum, secondnum, operation);
            return answer;
        }
        if (flag == false) {
           answer = ArabicNums(firstnum, secondnum, operation);
           return answer;
        }

        return "0";
    }
    public static boolean CheckNums(String input){
        String[] symbolsForCheck = { "1","2","3","4","5","6","7","8","9","10"};

        for(int i = 0; i < symbolsForCheck.length; i++){
            if(input.equals(symbolsForCheck[i])) { return true; }
        }
        return false;
    }
    public static String RimNums(String firstnum, String secondnum, char operaion) throws Exception {
        int answer = 0;

        try {
            if (Integer.parseInt(firstnum) > 10 || Integer.parseInt(secondnum) > 10 || Integer.parseInt(firstnum) < 1 || Integer.parseInt(secondnum) < 1) {
                throw new Exception("Калькулятор принимает числа от 1 до 10 включительно!");
            }
        } catch (Exception ex) {
            throw new Exception("Калькулятор умеет работать только с целыми римскими или арабскими числами!");
        }

        switch (operaion) {
            case ('+'):
                answer = Integer.parseInt(firstnum) + Integer.parseInt(secondnum);
                break;
            case ('-'):
                answer = Integer.parseInt(firstnum) - Integer.parseInt(secondnum);
                break;
            case ('/'):
                answer = Integer.parseInt(firstnum) / Integer.parseInt(secondnum);
                break;
            case ('*'):
                answer = Integer.parseInt(firstnum) * Integer.parseInt(secondnum);
                break;
        }
        return String.valueOf(answer);
    }
    public static String ArabicNums(String firstnum, String secondnum, char operation) throws Exception {
        int answer = 0;
        int index = 1;
        int temp;
        String answerrim = "";
        String[][] matrix = {
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XL", "L", "XC", "C"},
                {"1", "2",   "3",  "4",  "5", "6",   "7",    "8",  "9",  "10","40", "50","90","100"}
        };

        for (int i = 0; i < 10; i++) { // Расшифровка арабского числа в римское
            if (firstnum.equals(matrix[0][i])) {
                firstnum = matrix[1][i];
            }
        }
        for (int i = 0; i < 10; i++) { // Расшифровка арабского числа в римское
            if (secondnum.equals(matrix[0][i])) {
                secondnum = matrix[1][i];
            }
        }

        try { if (Integer.parseInt(firstnum) > 10 || Integer.parseInt(secondnum) > 10) {
                throw new Exception("Максимально допустимыe числа от 1 до 10");
            }
        }
        catch (Exception ex) {
            throw new Exception("Калькулятор умеет работать только с целыми римскими или арабскими числами от 1 до 10! ");
        }

        switch (operation) {
            case ('+'):
                answer = Integer.parseInt(firstnum) + Integer.parseInt(secondnum);
                break;
            case ('-'):
                answer = Integer.parseInt(firstnum) - Integer.parseInt(secondnum);
                break;
            case ('/'):
                answer = Integer.parseInt(firstnum) / Integer.parseInt(secondnum);
                break;
            case ('*'):
                answer = Integer.parseInt(firstnum) * Integer.parseInt(secondnum);
                break;
        }

        if(answer < 1){ throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа"); }

        while (answer != 0) {
            temp = answer / Integer.parseInt(matrix[1][matrix[1].length - index]); // проверка на остаток от деления
            if (temp != 0) { // если есть остаток, то берем максимальное кратное число переменной answer
                answer = answer - Integer.parseInt(matrix[1][matrix[1].length - index]); // необходимо для проверки, перевели ли мы полностью рим число в арб
                answerrim += matrix[0][matrix[0].length - index]; // То число, которое было максимально кратно answer записываем в ответ арб числами
            }
            else { // если нет остатка, то мы опускаемся на значение меньше
                index++;
            }
        }

        return answerrim;
    }
}