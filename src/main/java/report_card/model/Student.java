package main.java.report_card.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeMap;

public class Student implements Serializable, Comparable {
    private static final long serializableUID = 15645445551L;
    private String name;
    private String id;
    private TreeMap<String, Double> subject;
    private double total_score;
    private double average;

    public Student(String name, String id, TreeMap<String, Double> subject) {
        this.name = name;
        this.id = id;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeMap<String, Double> getSubject() {
        return subject;
    }

    public double countTotalScore() {

        Iterator<Double> i = getSubject().values().iterator();
        double sum = 0;
        while (i.hasNext()) {
            sum += i.next();
        }
        return sum;
//         Stream.of(getSubject().value s().iterator()).map(Iterator::next).reduce( 0,(x, y) -> x + y);
    }

    public void setTotal_score() {
        this.total_score = countTotalScore();
    }

    public double countAverage() {
        return average = getTotal_score() / getSubject().size();
    }

    public void setAverage() {
        this.average = countAverage();
    }

    public double getTotal_score() {
        setTotal_score();
        return total_score;
    }

    public double getAverage() {
        setAverage();
        return average;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Student s = (Student) obj;
        return getId() == s.getId();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.name.compareTo(s.name);
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + "|" + subject.get("数学") +
                "|" + subject.get("语文") +
                "|" + subject.get("英语") +
                "|" + subject.get("编程") +
                "|" + getAverage() +
                "|" + getTotal_score();

    }
}
