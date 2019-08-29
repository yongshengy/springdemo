package local.test.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class MyAdviceTest {
    public Object proceedTX(ProceedingJoinPoint pre) throws Throwable {

        System.out.println("Around增强处理：执行目标方法前，执行模拟开启事务.......");
        Object[] objs = pre.getArgs();
        if (objs != null && objs.length >
                0
                && objs[0].getClass() == String.class) {
            objs[0] = "被修改的参数";
        }
        Object obj = pre.proceed(objs);

        System.out.println("Around增强处理:执行目标方法之后，模拟结束事务.....");

        return obj + ",新增加的内容";
    }

    public void authority(JoinPoint jp) {
        System.out.println("Before增强：模拟权限检查");
        System.out.println("Before增强：被织入增强处理的目标方法："
                + jp.getSignature().getName());
        System.out.println("Before增强：目标方法的参数为：" +
                Arrays.toString(jp.getArgs()));
        System.out.println("Before增强：被注入增强的处理的目标对象：" + jp.getTarget());
    }

    public void log(JoinPoint jp, Object object) {

        System.out.println("AfterReturning增强:获取目标方法的返回值：" + object);
        System.out.println("AfterReturning增强:模拟日志记录功能.....");
        System.out.println("AfterReturning增强：被织入增强处理的目标方法："
                + jp.getSignature().getName());
        System.out.println("AfterReturning增强：目标方法的参数为："
                + Arrays.toString(jp.getArgs()));
        System.out.println("AfterReturning增强：被注入增强的处理的目标对象：" + jp.getTarget());
    }

    public void release(JoinPoint jp) {
        System.out.println("After增强：模拟方法结束后，资源释放.....");
        System.out.println("After增强：被织入增强处理的目标方法："
                + jp.getSignature().getName());
        System.out.println("After增强：目标方法的参数为：" + Arrays.toString(jp.getArgs()));
        System.out.println("After增强：被注入增强的处理的目标对象：" + jp.getTarget());
    }

    public void doRecoverActions(Throwable th){
        System.out.println("####th = [" + th + "]");
        System.out.println("模拟异常处理。。。");
    }
}
