package ru.vladrus13.bean;

import org.json.JSONObject;

public class Hack extends Bean {

    Integer id;
    Integer creationTimeSeconds;
    Party hacker;
    Party defender;
    @CanBeNull
    String verdict;
    Problem problem;
    @CanBeNull
    String test;
    // TODO judgeProtocol	Объект, содержащий три поля: "manual", "protocol" и "verdict". Поле "manual" принимает значения "true" и "false" и имеет значение "true" если тест для взлома введен вручную. Поля "protocol" и "verdict" содержат строки с человеческими описаниями протокола тестирования и вердикта взлома. Локализовано. Может отсутствовать.

    public Hack(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public Party createHacker(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Party((JSONObject) object);
    }

    public Party createDefender(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Party((JSONObject) object);
    }

    public Problem createProblem(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Problem((JSONObject) object);
    }
}
