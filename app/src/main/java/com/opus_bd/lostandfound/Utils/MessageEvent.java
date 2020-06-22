package com.opus_bd.lostandfound.Utils;

public class MessageEvent {
    public MessageEvent(boolean update) {
        this.update = update;
    }

    private boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
