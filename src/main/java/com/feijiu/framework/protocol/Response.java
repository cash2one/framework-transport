package com.feijiu.framework.protocol;

/**
 * Created by zhangtao on 2016/8/8.
 * 响应信息
 */
public class Response {

    private Long requestId ;
    private String serverName ;
    private String version ;
    private Object resultDesc ;
    private Throwable cause ;

    public Response(){}

    public Response(Throwable cause){
        this.cause = cause ;
    }


    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(Object resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
