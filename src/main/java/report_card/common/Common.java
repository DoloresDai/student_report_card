package main.java.report_card.common;

import main.java.report_card.model.Student;

import java.util.TreeMap;

public class Common {
    public static boolean isCorrectInput(String in) {
        String regex = "[\\u4E00-\\u9FA5]{1,4}\\uff0c(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)";
        return in.matches(regex);
    }

    public static boolean isCorrectOutput(String out) {
        String regex = "^(0|[1-9][0-9]?)(，\\d+)*$";
        return out.matches(regex);
    }

    private static double getSubject(String[] student, String subject) {
        for (String s : student) {
            if (s.startsWith(subject)) {
                return Double.parseDouble(s.split("：")[1]);
            }
        }
        return 0;
    }

    public static Student newStudent(String[] strings) {
        TreeMap<String, Double> student = new TreeMap<>();
        student.put("数学", getSubject(strings, "数学"));
        student.put("语文", getSubject(strings, "语文"));
        student.put("英语", getSubject(strings, "英语"));
        student.put("编程", getSubject(strings, "编程"));
        return new Student(strings[0], strings[1], student);
    }
}
