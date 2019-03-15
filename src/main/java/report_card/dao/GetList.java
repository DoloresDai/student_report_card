package main.java.report_card.dao;

import main.java.report_card.model.Student;

import java.util.Arrays;
import java.util.List;

public class GetList {
    public double getTotalScore(List<Student> list) {
        return list.stream().map(x -> x.getTotal_score()).reduce(0.00, (x, y) -> x + y);
    }

    public double getAverage(List<Student> list) {
        return list.stream().map(x -> x.getAverage()).reduce(0.00, (x, y) -> x + y);
    }

    public String get(List<Student> list) {
        GetStudent getStudent = new GetStudent();

        return "成绩单\r\n" +
                "姓名|数学|语文|英语|编程|平均分|总分 \r\n" +
                "========================\r\n" +
                Arrays.toString(getStudent.getAllStudent(list))
                + "\r\n========================\r\n" +
                "全班总分平均数：" + getTotalScore(list)
                + "\r\n全班总分中位数：" + getAverage(list);
    }
}
