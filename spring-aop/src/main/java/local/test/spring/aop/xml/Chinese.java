package local.test.spring.aop.xml;

public class Chinese implements Person {
    @Override
    public String sayHello(String word) {
        return word;
    }

    @Override
    public void eat(String food) {
        System.out.println("我正在吃" + food);
    }


    @Override
    public void divide() {

        int i = 1/0;
    }
}
