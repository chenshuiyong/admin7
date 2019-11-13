package cc.mrbird.common.http.model;

public enum IndexPageType {
    HAS(0),
    NO(1);

    private int type = 0;

    IndexPageType(int t) {
        type = t;
    }

    public int getType() {
        return type;
    }
}
