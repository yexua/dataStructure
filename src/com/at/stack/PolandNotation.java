package com.at.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * （后缀表达式）逆波兰表达式
 * (3+4)x5-6 <==> 3 4 + 5 x 6 -
 */
public class PolandNotation {

    public static void main(String[] args) {
        //(3+4)x5-6
//        String suffix = "3 4 + 5 * 6 -";
//        List<String> stringList = getSuffixList(suffix);
//        int res = calculate(stringList);
//        System.out.println(res);
        String inffix = "1+((2+3)*4)-5";
        List<String> inffixList = getInffixList(inffix);
        System.out.println(inffixList);
        List<String> list = infixListToSuffixList(inffixList);
        System.out.println(list);
        System.out.println(calculate(list));
    }

    /**
     * 解析后缀表达式
     *
     * @param suffix
     * @return
     */
    public static List<String> getSuffixList(String suffix) {
        String[] split = suffix.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 解析中缀表达式
     * @param inffix
     * @return
     */
    public static List<String> getInffixList(String inffix) {
        List<String> list = new ArrayList<>();
        String str;
        char c;
        int i = 0;
        while (i < inffix.length()) {
            //c不是一个数字
            if ((c = inffix.charAt(i)) < 48 || (c = inffix.charAt(i)) > 57) {
                list.add(c + "");
                i++;
            } else {//如果是一个数字，考虑多位数的情况
                str = "";
                while (i < inffix.length() && (c = inffix.charAt(i)) >= 48 && (c = inffix.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String> infixListToSuffixList(List<String> inffix) {
        Stack<String> s1 = new Stack<>();
        //由于第二个栈没有出栈操作，还要逆序输出，比较麻烦，这里用List替换
//        Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for (String s : inffix) {
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //将(  弹出s，消除雄小括号
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        //将s1中剩余的运算符依次弹出到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("表达式有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    public static final int ADD = 1;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}