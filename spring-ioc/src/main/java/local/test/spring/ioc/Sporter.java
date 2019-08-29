package local.test.spring.ioc;

import java.util.Map;
import java.util.Set;

public class Sporter {
    private int age;
    private String name;
    private Set<Integer> scores1;
    private Map<String, Integer> scores2;
    private int num;

    private MessageService messageService;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScores1(Set<Integer> scores1) {
        this.scores1 = scores1;
    }

    public void setScores2(Map<String, Integer> scores2) {
        this.scores2 = scores2;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getScores1() {
        return scores1;
    }

    public Map<String, Integer> getScores2() {
        return scores2;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
