package com.example.amitrai.demomusicplayer.modals;

/**
 * Created by amitrai on 15/12/16.
 */

public class BaseBean {

    private boolean isProgressEnable;

    private String Status_message;

    private String Error_Message;

    public boolean isProgressEnable() {
        return isProgressEnable;
    }

    public void setProgressEnable(boolean progressEnable) {
        isProgressEnable = progressEnable;
    }

    public String getStatus_message() {
        return Status_message;
    }

    public void setStatus_message(String status_message) {
        Status_message = status_message;
    }

    public String getError_Message() {
        return Error_Message;
    }

    public void setError_Message(String error_Message) {
        Error_Message = error_Message;
    }
}
