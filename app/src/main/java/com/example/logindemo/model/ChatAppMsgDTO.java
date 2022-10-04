package com.example.logindemo.model;

public class ChatAppMsgDTO {
    public final static String MSG_TYPE_SENT = "MSG_TYPE_SENT";
    public final static String MSG_TYPE_RECEIVED = "MSG_TYPE_RECEIVED";

    // Message content.
    private String msgContent;

    // Message type.
    private String msgType;

    //Image type
    private  String msgImage;

    public ChatAppMsgDTO(String msgType, String msgContent,String msgImage) {
        this.msgType = msgType;
        this.msgContent = msgContent;
        this.msgImage = msgImage;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgImage(){
        return msgImage;
    }

    public void setMsgImage(String msgImage){
        this.msgImage = msgImage;
    }
}
