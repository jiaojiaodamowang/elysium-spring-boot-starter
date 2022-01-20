package com.hooz.elysium.core;

/**
 * Typed parameter for AOP
 *
 * @author Kidd Zhou
 * @date 2022-01-20
 */
public class TypedParam {

    private final Class<?> type;

    private final String name;

    private final Object value;

    public TypedParam(Class<?> type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypedParam that = (TypedParam) o;
        if (!type.equals(that.type)) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypedParam{" +
                "type=" + type.getName() +
                ", name=" + name +
                ", value=" + value +
                "}";
    }
}
