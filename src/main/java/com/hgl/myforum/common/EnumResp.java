package com.hgl.myforum.common;

/**
 * @author HGL
 * @Date 2020/9/6
 */
public enum EnumResp {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    USNAMEPWDNOTNULL(1,"用户名或密码不能为空");

    private Integer code;
    private String desc;

    private EnumResp(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 自己定义一个静态方法,通过code返回枚举常量对象
     * @param code
     * @return
     */
    public static EnumResp getValue(Integer code) {
        EnumResp[] enumResps = values();
        if (enumResps != null){
            for (EnumResp e : enumResps) {
                if (e.getCode() == code){
                    return e;
                }
            }
        }
        return null;
    }

}
