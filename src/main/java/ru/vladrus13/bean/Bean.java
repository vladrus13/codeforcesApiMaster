package ru.vladrus13.bean;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class Bean {

    public static ArrayList<Class> classes = new ArrayList<>(List.of(
            String.class, Long.class, Integer.class, Boolean.class, Double.class, BigDecimal.class));
    public static ArrayList<Class> subBean = new ArrayList<>(List.of(
            BlogEntry.class, Comment.class, Contest.class, Hack.class, Member.class,
            Party.class, Problem.class, ProblemResult.class, ProblemStatistics.class,
            RanklistRow.class, RecentAction.class, Submission.class, User.class));

    private Object getField(Field field, Object object) {
        for (Class it : classes) {
            if (field.getType().isAssignableFrom(it)) {
                if (!object.getClass().isAssignableFrom(it)) {
                    throw new UnsupportedClassVersionError(
                            "Can't cast type " + object.getClass().getName() + " in " + field.getType().getName() + " on field " + field.getName());
                }
                return object;
            }
        }
        for (Class subClass : subBean) {
            if (field.getType().isAssignableFrom(subClass)) {
                try {
                    String fieldName = field.getName();
                    String methodName = "create" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    return this.getClass().getMethod(methodName, Object.class).invoke(this, object);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                /*
                if (!object.getClass().isAssignableFrom(JSONObject.class)) {
                    throw new UnsupportedClassVersionError(
                            "Can't cast type " + object.getClass().getName() + " in " + field.getType().getName() + " on field " + field.getName());
                } else {
                    try {
                        return subClass.getConstructor(JSONObject.class).newInstance(object);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
        if (field.getType().isAssignableFrom(java.util.ArrayList.class)) {
            try {
                String fieldName = field.getName();
                String methodName = "create" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                return this.getClass().getMethod(methodName, Object.class).invoke(this, object);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        throw new UnsupportedClassVersionError("Can't parse: " + field.getType().getName());
    }

    public Bean(JSONObject object) throws IllegalAccessException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!object.has(field.getName())
                    && Arrays.stream(field.getDeclaredAnnotations()).noneMatch(annotation -> annotation instanceof CanBeNull)) {
                throw new IllegalArgumentException("Don't have " + field.getName() + " field in object: " + this.getClass().getName());
            }
            if (object.has(field.getName())) {
                field.set(this, getField(field, object.get(field.getName())));
            }
        }
    }
}