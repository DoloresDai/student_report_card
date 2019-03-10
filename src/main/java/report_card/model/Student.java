package main.java.report_card.model;

import java.util.TreeMap;
import java.util.stream.Stream;

public class Student {
    private String name;
    private long id;
    private TreeMap<String, Long> subject;
    private long total_score;
    private long average;

    public Student(String name, long id, TreeMap<String, Long> subject) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TreeMap<String, Long> getSubject() {
        return subject;
    }

    public void setTotal_score(long total_score) {
        this.total_score = total_score;
    }

    public void countTotalScore() {
        long sum = Stream.of(getSubject()).map(x ->
                x.get(x.entrySet().iterator().next())).reduce((x, y) -> x + y).get();
        setTotal_score(sum);
    }

    public long getTotal_score() {
        return total_score;
    }

    public void setAverage(long average) {
        this.average = average;
    }

    public void countAverage() {
        long average;
        average = getTotal_score() / getSubject().size();
        setAverage(average);
    }

    public long getAverage() {
        return average;
    }

}
