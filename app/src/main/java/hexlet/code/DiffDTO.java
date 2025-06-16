package hexlet.code;

public final class DiffDTO {
    private String key;
    private Object value; // при статусе CHANGED хранит левое значение
    private Status status;
    private Object secondValue; // используется только при статусе CHANGED; хранит правое значение

    DiffDTO(String key, Object value, Status status) {
        setKey(key);
        setValue(value);
        setStatus(status);
        setSecondValue(null);
    }
    DiffDTO(String key, Object value, Status status, Object secondValue) {
        setKey(key);
        setValue(value);
        setStatus(status);
        setSecondValue(secondValue);
    }

    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setSecondValue(Object secondValue) {
        this.secondValue = secondValue;
    }

    public String getKey() {
        return this.key;
    }
    public Object getValue() {
        return this.value;
    }
    public Status getStatus() {
        return this.status;
    }
    public Object getSecondValue() {
        return this.secondValue;
    }
}
