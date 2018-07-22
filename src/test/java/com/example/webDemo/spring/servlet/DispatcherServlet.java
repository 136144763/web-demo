package com.example.webDemo.spring.servlet;

import com.example.webDemo.spring.annotation.Controller;
import com.example.webDemo.spring.annotation.Quatifier;
import com.example.webDemo.spring.annotation.RequestMapping;
import com.example.webDemo.spring.annotation.Service;
import com.example.webDemo.spring.controller.SpringmvcController;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luofei on 2018/5/28.
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    List<String> packageNames = new ArrayList<>();

    //所有类的实例,key是注解的value,value是所有类的实例
    Map<String, Object> instanceMap = new HashMap<>();
    Map<String, Object> handerMap = new HashMap<>();

    public DispatcherServlet() {
        super();
    }

    public void init(ServletConfig config) {

        //包扫描,获取包中的文件
        scanPackage("com.example.webDemo.spring");
        try {
            filterAndInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //建立映射关系
        handerMap();

        //实现注入
        ioc();

    }

    private void ioc() {
        if (instanceMap.isEmpty())
            return;
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            // 拿到里面的所有属性
            Field fields[] = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);// 可访问私有属性
                if (field.isAnnotationPresent(Quatifier.class)) ;
                Quatifier quatifier = field.getAnnotation(Quatifier.class);
                String value = quatifier.value();
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), instanceMap.get(value));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        SpringmvcController wuqi = (SpringmvcController) instanceMap.get("wuqi");
        System.out.print(wuqi);
    }

    private void filterAndInstance() throws Exception {
        if (packageNames.size() <= 0) {
            return;
        }
        for (String className : packageNames) {
            Class<?> cName = Class.forName(className.replace(".class", "").trim());
            if (cName.isAnnotationPresent(Controller.class)) {
                Object instance = cName.newInstance();
                Controller controller = cName.getAnnotation(Controller.class);
                String key = controller.value();
                instanceMap.put(key, instance);
            } else if (cName.isAnnotationPresent(Service.class)) {
                Object instance = cName.newInstance();
                Service service = cName.getAnnotation(Service.class);
                String key = service.value();
                instanceMap.put(key, instance);
            } else {
                continue;
            }
        }
    }

    /**
     * 扫描包下所有文件
     *
     * @param Package
     */
    private void scanPackage(String Package) {
        URL url = this.getClass().getClassLoader().getResource("" + replaceTo(Package));
        String pathFile = url.getFile();
        File file = new File(pathFile);
        String fileList[] = file.list();
        for (String path : fileList) {
            File echoFile = new File(pathFile + path);
            if (echoFile.isDirectory()) {
                scanPackage(Package + "." + echoFile.getName());
            } else {
                packageNames.add(Package + "." + echoFile.getName());
            }
        }
    }


    private String replaceTo(String path) {
        return path.replaceAll("\\.", "/");
    }

    /**
     * 建立映射关系
     */
    private void handerMap() {
        if (instanceMap.size() <= 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
                Controller controller = entry.getValue().getClass().getAnnotation(Controller.class);
                String ctvalue = controller.value();
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping rm = method.getAnnotation(RequestMapping.class);
                        String rmvalue = rm.value();
                        handerMap.put("/" + ctvalue + "/" + rmvalue, method);
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    }


}
