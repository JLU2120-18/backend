package com.salary.vo;

import com.salary.enums.ResponeEnum;
import lombok.Data;

/**
 * 描述：
 */
@Data
public class AdminReportVO<T> {

    private T data;

    public AdminReportVO(T data) {
        this.data = data;
    }

    public static <T> AdminReportVO<T> success(T data){
        return new AdminReportVO<>(data);
    }

    public static  AdminReportVO<String> error(){
        return new AdminReportVO<>(ResponeEnum.ERROR.getDesc());
    }
}
