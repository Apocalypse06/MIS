package top.yyf.mess.retmess;

import top.yyf.util.ErrorCode;

/**
 * Created by Daniel on 2017/3/2.
 * 实际返回的类型，需要保存返回码和返回信息，可以添加具体的内容
 *
 */
public class BaseMessage<T> {
    private int retCode;
    private String retMess;
    private T retContent;

    public BaseMessage() {
        this(ErrorCode.SERVERFAIL, "");
    }


    public BaseMessage(int retCode, String retMess, T retContent) {
        this.retCode = retCode;
        this.retMess = retMess;
        this.retContent = retContent;
    }

    public BaseMessage(int retCode, String retMess) {
        this.retCode = retCode;
        this.retMess = retMess;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMess() {
        return retMess;
    }

    public void setRetMess(String retMess) {
        this.retMess = retMess;
    }

    public T getRetContent() {
        return retContent;
    }

    public void setRetContent(T retContent) {
        this.retContent = retContent;
    }

    public void setRetCodeAndMess(int retCode, String retMess) {
        this.retCode = retCode;
        this.retMess = retMess;
    }
}
