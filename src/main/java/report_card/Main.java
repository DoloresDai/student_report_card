package main.java.report_card;

import main.java.report_card.model.Student;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        menu();
        select(new Scanner(System.in).next());
    }

    public static void menu() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }

    public static void select(String in) {
        switch (in) {
            case "1":
                System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                Scanner input = new Scanner(System.in);
                String content = input.nextLine();
                if (isCorrectInput(content)) {
                    saveStudent(newStudent(getInput(content)));
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
                if (isCorrectOutput(out)) {
                    System.out.println(getList(getSelected(out)));
                    saveList(getSelected(out));
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

    public static String[] getInput(String in) {
        return in.split("，");
    }

    public static void saveStudent(Student student) {
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream((new FileOutputStream("student.txt",true)));
            o.writeObject(student);
            o.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("学生" + student.getName() + "的成绩被添加");
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveList(List<Student> list) {
        BufferedWriter o = null;
        try {
            o = new BufferedWriter(new FileWriter("list.txt"));

            o.write(getList(list));
            o.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static double getTotalScore(List<Student> list) {
        return list.stream().map(x -> x.getTotal_score()).reduce(0.00, (x, y) -> x + y);
    }

    public static double getAverage(List<Student> list) {
        return list.stream().map(x -> x.getAverage()).reduce(0.00, (x, y) -> x + y);
    }

    public static String getList(List<Student> list) {
        return "成绩单\r\n" +
                "姓名|数学|语文|英语|编程|平均分|总分 \r\n" +
                "========================\r\n" +
                Arrays.toString(getAllStudent(list))
                + "\r\n========================\r\n" +
                "全班总分平均数：" + getTotalScore(list)
                + "\r\n全班总分中位数：" + getAverage(list);
    }

    public static String[] getAllStudent(List<Student> list) {
        String[] detail = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            detail[i]=student.getName() + "|" + student.getSubject().get("数学") +
                    "|" + student.getSubject().get("语文") +
                    "|" + student.getSubject().get("英语") +
                    "|" + student.getSubject().get("编程") +
                    "|" + student.getAverage() +
                    "|" + student.getTotal_score();
        }
        return detail;
    }

    public static List<Student> getSelected(String in) {
        String[] ids = getInput(in);
        List<Student> list = new ArrayList<>();
        for (String s : ids) {
            list.add(ifStudent(s));
        }
        return list;
    }

    public static Student ifStudent(String id) {
        List<Student> students = getStudent();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static List<Student> getStudent() {
        ObjectInputStream i = null;
        List<Student> students = new ArrayList<>();
        try {
            i = new ObjectInputStream(new FileInputStream("student.txt"));
            while (i.available()>0) {
                Student student = (Student) i.readObject();
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (i != null) {
                try {
                    i.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    public static boolean isCorrectInput(String in) {
        String regex = "[\\u4E00-\\u9FA5]{1,4}\\uff0c(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)\\uff0c[\\u4E00-\\u9FA5]{2}\\uff1a(0|[1-9][0-9]?|100)";
        return in.matches(regex);
    }

    public static boolean isCorrectOutput(String out) {
        String regex = "^(0|[1-9][0-9]?)(，\\d+)*$";
        return out.matches(regex);
    }

    public static double getSubject(String[] student, String subject) {
        for (String s : student) {
            if (s.startsWith(subject)) {
                return Double.parseDouble(s.split("：")[1]);
            }
        }
        return 0;
    }

    public static Student newStudent(String[] strings) {
        TreeMap<String, Double> sss = new TreeMap<>();
        sss.put("数学", getSubject(strings, "数学"));
        sss.put("语文", getSubject(strings, "语文"));
        sss.put("英语", getSubject(strings, "英语"));
        sss.put("编程", getSubject(strings, "编程"));
        return new Student(strings[0], strings[1], sss);
    }

}

