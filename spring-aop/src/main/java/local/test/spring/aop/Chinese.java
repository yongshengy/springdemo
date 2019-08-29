package local.test.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Chinese implements Person {
    @Override
    public String sayHello(String word) {
        return word;
    }

    @Override
    public void eat(String food) {
        System.out.println("我正在吃" + food);
    }

    public void divide(){
        int i = 1/0;
        System.out.println(i);
    }

}
