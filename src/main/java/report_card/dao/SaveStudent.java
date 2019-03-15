package main.java.report_card.dao;

import main.java.report_card.model.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveStudent {
    public void save(Student student) {
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream((new FileOutputStream("src\\main\\java\\report_card\\dao\\student.txt", true)));
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
}
