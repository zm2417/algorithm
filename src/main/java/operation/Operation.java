package operation;

import java.util.Stack;

public class Operation {

    private static int ADDITION = 1;
    private static int SUBTRACTION = 1;
    private static int MULTIPLICATION = 2;
    private static int DIVISION = 2;

    /**
     * 中缀转后缀
     * @param s 中缀
     * @return 后缀
     */
    public String parseSuffixExpression(String s){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            }else if(c == ')'){
                while (stack.peek() != '('){
                    stringBuilder.append(stack.pop());
                }
                stack.pop();
            }else if(getValue(c) == 0){
                stringBuilder.append(c);
            }else {
                while (!stack.empty() &&  getValue(stack.peek()) >= getValue(c)){
                    stringBuilder.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.empty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    /**
     * 计算后缀表达式的结果
     * @param s 后缀
     * @return value
     */
    public static int calculate(String s){
        Stack<String> stack = new Stack<>();
        for (int i = 0;i<s.length();i++){
            String s1 = s.substring(i,i+1);
            if(s1.matches("\\d+")){
                stack.push(s1);
            }else {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                int value = 0;
                switch (s1){
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = b - a;
                        break;
                    case "*":
                        value = a * b;
                        break;
                    case "/":
                        value = b / a;
                        break;
                }
                stack.push(String.valueOf(value));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private int getValue(char c){
        int res = 0;
        switch (c){
            case '+':
                res = ADDITION;
                break;
            case '-':
                res = SUBTRACTION;
                break;
            case '/':
                res = DIVISION;
                break;
            case '*':
                res = MULTIPLICATION;
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        Operation operation = new Operation();
        String s = operation.parseSuffixExpression("1+((2+3)*4)-5");
        System.out.println(Operation.calculate(s));
    }

}
