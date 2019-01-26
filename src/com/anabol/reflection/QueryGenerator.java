package com.anabol.reflection;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class QueryGenerator {
    public String getAll(Class<?> clazz) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(getAllColumnsName(clazz));
        stringBuilder.append(" FROM ");
        stringBuilder.append(getTableName(clazz));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String insert(Object value) {
        Class<?> clazz = value.getClass();
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(getTableName(clazz));
        stringBuilder.append("(");
        stringBuilder.append(getAllColumnsName(clazz));
        stringBuilder.append(") VALUES ");
        stringBuilder.append(getAllColumnsValue(clazz, value));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    // UPDATE persons SET person_name = 'John', salary = 678.9 WHERE id = 1;
    public String update(Object value) {
        Class<?> clazz = value.getClass();
        StringBuilder stringBuilder = new StringBuilder("UPDATE ");
        stringBuilder.append(getTableName(clazz));
        stringBuilder.append(" SET ");
        stringBuilder.append(getColumnNameValue(clazz, value, false));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(getColumnNameValue(clazz, value, true));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    // SELECT id, person_name, salary FROM persons WHERE id = 1;
    public String getById(Class<?> clazz, Object id) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(getAllColumnsName(clazz));
        stringBuilder.append(" FROM ");
        stringBuilder.append(getTableName(clazz));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(getIdColumnName(clazz));
        stringBuilder.append(" = " + id.toString());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    // DELETE FROM persons WHERE id = 1;
    public String delete(Class<?> clazz, Object id) {
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        stringBuilder.append(getTableName(clazz));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(getIdColumnName(clazz));
        stringBuilder.append(" = " + id.toString());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    private String getTableName(Object value) {
        return getTableName(value.getClass());
    }

    private String getTableName(Class<?> clazz) {
        Table annotation = clazz.getAnnotation(Table.class);
        if (annotation == null) {
            throw new IllegalArgumentException("@Table is missing");
        }
        return annotation.name().isEmpty() ? clazz.getName() : annotation.name();
    }

    private String getAllColumnsName(Class<?> clazz) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        return stringJoiner.toString();
    }

    private String getIdColumnName(Class<?> clazz) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            Id idAnnotation = declaredField.getAnnotation(Id.class);
            if (columnAnnotation != null && idAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        return stringJoiner.toString();
    }

    private String getAllColumnsValue(Class<?> clazz, Object value) {
        StringJoiner stringJoiner = new StringJoiner(", ", "(", ")");
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                stringJoiner.add(getColumnValue(value, declaredField));
            }
        }
        return stringJoiner.toString();
    }

    private String getColumnNameValue(Class<?> clazz, Object value, boolean isId) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            Id idAnnotation = declaredField.getAnnotation(Id.class);
            if (columnAnnotation != null && isId == (idAnnotation != null)) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName + " = " + getColumnValue(value, declaredField));
            }
        }
        return stringJoiner.toString();
    }

    private String getColumnValue(Object value, Field field) {
        Object columnValue = null;
        try {
            field.setAccessible(true);
            columnValue = field.get(value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        columnValue = field.getType().equals(String.class) ? "'" + columnValue + "'" : columnValue;
        return columnValue.toString();
    }

}