package hexlet.code;

public class DiffDTO {
    private String key;
    private String value; // при статусе CHANGED хранит левое значение
    private Utils.Status status;
    private String secondValue; // используется только при статусе CHANGED; хранит правое значение

    DiffDTO(String key, String value, Utils.Status status) {
        setKey(key);
        setValue(value);
        setStatus(status);
        setSecondValue(null);
    }
    DiffDTO(String key, String value, Utils.Status status, String secondValue) {
        setKey(key);
        setValue(value);
        setStatus(status);
        setSecondValue(secondValue);
    }

    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setStatus(Utils.Status status) {
        this.status = status;
    }
    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public String getKey() {
        return this.key;
    }
    public String getValue() {
        return this.value;
    }
    public Utils.Status getStatus() {
        return this.status;
    }
    public String getSecondValue() {
        return this.secondValue;
    }
}
