package main.java.report_card.dao;

import main.java.report_card.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveList {
    public void save(List<Student> list) {
        BufferedWriter o = null;
        try {
            o = new BufferedWriter(new FileWriter("src\\main\\java\\report_card\\dao\\list.txt"));
            GetList getList = new GetList();
            o.write(getList.get(list));
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
}
