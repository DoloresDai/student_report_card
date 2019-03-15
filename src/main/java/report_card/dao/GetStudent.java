package main.java.report_card.dao;

import main.java.report_card.model.Student;
import main.java.report_card.tools.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GetStudent {
    public List<Student> getStudent() {
        ObjectInputStream i = null;
        List<Student> students = new ArrayList<>();
        try {
            i = new ObjectInputStream(new FileInputStream("src\\main\\java\\report_card\\dao\\student.txt"));
            Student student = (Student) i.readObject();
            students.add(student);
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

    public List<Student> getSelected(String in) {
        String[] ids = Tools.getInput(in);
        List<Student> list = new ArrayList<>();
        for (String s : ids) {
            list.add(ifStudent(s));
        }
        return list;
    }

    public Student ifStudent(String id) {
        List<Student> students = getStudent();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public String[] getAllStudent(List<Student> list) {
        String[] detail = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            detail[i] = student.getName() + "|" + student.getSubject().get("数学") +
                    "|" + student.getSubject().get("语文") +
                    "|" + student.getSubject().get("英语") +
                    "|" + student.getSubject().get("编程") +
                    "|" + student.getAverage() +
                    "|" + student.getTotal_score();
        }
        return detail;
    }
}
