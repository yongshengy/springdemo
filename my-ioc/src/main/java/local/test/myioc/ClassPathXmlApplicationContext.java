package local.test.myioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private Map<String, Object> ioc = new HashMap<>();

    public ClassPathXmlApplicationContext(String path) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(ClassPathXmlApplicationContext.class.getClassLoader().getResourceAsStream("bean.xml"));
            Element rootElement = document.getRootElement();
            Iterator<Element> elementIterator = rootElement.elementIterator();
            while(elementIterator.hasNext()) {
                Element element = elementIterator.next();
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class<?> clazz = Class.forName(className);
                Constructor<?> constructor = clazz.getConstructor();
                Object instance = constructor.newInstance();

                Iterator<Element> elementIterator1 = element.elementIterator();

                while (elementIterator1.hasNext()) {
                    Element element1 = elementIterator1.next();
                    String name = element1.attributeValue("name");
                    String value = element1.attributeValue("value");
                    PropertyDescriptor p1 = new PropertyDescriptor(name, clazz);
                    Method writeMethod = p1.getWriteMethod();
                    if (p1.getPropertyType().equals(int.class)) {
                        writeMethod.invoke(instance, Integer.parseInt(value));
                    } else {
                        writeMethod.invoke(instance, value);
                    }
                }
                ioc.put(id, instance);

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String id) {
        return ioc.get(id);
    }
}
