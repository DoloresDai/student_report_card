package main.java.report_card.service;

import main.java.report_card.common.Common;
import main.java.report_card.dao.GetList;
import main.java.report_card.dao.GetStudent;
import main.java.report_card.dao.SaveList;
import main.java.report_card.dao.SaveStudent;
import main.java.report_card.tools.Tools;

import java.util.Scanner;

public class Menu {
    public void mainMenu() {
        menu();
        select(new Scanner(System.in).next());
    }

    private void menu() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }

    private void select(String in) {
        switch (in) {
            case "1":
                System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                Scanner input = new Scanner(System.in);
                String content = input.nextLine();
                if (Common.isCorrectInput(content)) {
                    SaveStudent saveStudent = new SaveStudent();
                    saveStudent.save(Common.newStudent(Tools.getInput(content)));
                    mainMenu();
                } else {
                    System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                    select("1");
                }
                break;
            case "2":
                System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                Scanner output = new Scanner(System.in);
                String out = output.nextLine();
                if (Common.isCorrectOutput(out)) {
                    GetList getList = new GetList();
                    GetStudent getStudent = new GetStudent();
                    System.out.println(getList.get(getStudent.getSelected(out)));
                    SaveList saveList = new SaveList();
                    saveList.save(getStudent.getSelected(out));
                } else {
                    System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    select("2");
                }
                break;
            case "3":
                System.out.println("已退出");
                break;
        }
    }

}
