package model;

public enum Ruolo {
    CLIENTE(1),
    PERSONALTRAINER(2),
    PROPRIETARIO(3);

    private final int id;

    Ruolo(int id) {
        this.id = id;
    }

    public static Ruolo fromInt(int id) {
        for (Ruolo type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
