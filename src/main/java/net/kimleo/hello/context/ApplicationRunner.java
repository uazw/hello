package net.kimleo.hello.context;

import net.kimleo.hello.annotation.Application;
import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.annotation.Factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationRunner {

    Map<Class, Object> context = new ConcurrentHashMap<>();
    Map<Class, Class> components = new ConcurrentHashMap<>();

    public void run(Class<?> appClass) {
        assert (appClass.getAnnotation(Application.class) != null);
        try {
            Class[] classes = getClasses(appClass.getPackage().getName());
            for (Class aClass : classes) {
                addToComponentMappings(aClass);
            }

            for (Class aClass : components.keySet()) {
                addToContextIfItIsAComponent(aClass);
            }

            Constructor ctor = appClass.getDeclaredConstructors()[0];
            ArrayList<Object> objects = new ArrayList<>();
            for (Class aClass : ctor.getParameterTypes()) {
                objects.add(context.get(components.get(aClass)));
            }
            Object app = ctor.newInstance(objects.toArray());
            appClass.getDeclaredMethod("run").invoke(app);
        } catch (Exception ignored) {
        }
    }

    private void addToComponentMappings(Class clz) {
        if (clz.getAnnotation(Component.class) != null && !clz.isInterface()) {
            components.put(clz, clz);
            Class superclass = clz.getSuperclass();
            Class[] interfaces = clz.getInterfaces();
            for (Class intf : interfaces) {
                if (intf.getAnnotation(Component.class) != null) {
                    components.put(intf, clz);
                }
            }
        }
    }


    private void addToContextIfItIsAComponent(Class clz)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (!clz.isInterface()) {
            context.put(clz, createInstance(clz));
        }
    }

    private Object createInstance(Class clz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (context.containsKey(clz)) return context.get(clz);
        if (clz.getAnnotation(Factory.class) != null) {
            return clz.getDeclaredMethod("getInstance").invoke(clz);
        }

        Constructor[] constructors = clz.getDeclaredConstructors();
        for (Constructor ctor : constructors) {
            Class[] parameterTypes = ctor.getParameterTypes();
            ArrayList<Object> objects = new ArrayList<>();
            for (Class param : parameterTypes) {
                addToContextIfItIsAComponent(components.get(param));
                objects.add(context.get(components.get(param)));
            }
            return ctor.newInstance(objects.toArray());
        }
        return null;
    }

    private Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
