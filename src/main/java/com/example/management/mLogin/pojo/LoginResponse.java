package com.example.management.mLogin.pojo;

public class LoginResponse {
    private int result; //0成功1失败
    private int sucMessage;//1社员2社团部门管理员3社团admin4superadmin 0 no suc
    private int errorMessage;//1用户不存在 2密码错误 3用户名密码空 0no err

    public LoginResponse(int result,int sucMessage,int errorMessage){
        this.result=result;
        this.errorMessage=errorMessage;
        this.sucMessage=sucMessage;
    }

    public int getErrorMessage() {
        return errorMessage;
    }

    public int getResult() {
        return result;
    }

    public int getSucMessage() {
        return sucMessage;
    }

    public void setErrorMessage(int errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setSucMessage(int sucMessage) {
        this.sucMessage = sucMessage;
    }
}
