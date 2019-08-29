package local.test.spring.ioc;

/**
 * 抽象产品类，比如没有类别的产品是系统中不存在的，就可以定义一个抽象类
 */
public abstract class AbstractProduct {
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
