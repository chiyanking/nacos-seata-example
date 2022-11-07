package com.wangtk.mvc.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ContentRowHeight(40) // 设置内容高度
@HeadRowHeight(50)// 设置表头高度
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleVO {
    @ExcelProperty(value = "序号", index = 0) // 设置表头字段名
    @ColumnWidth(8)// 设置行宽
    private Integer idx;

    @ExcelProperty(value = "姓名", index = 1)
    @ColumnWidth(20)
    private String name;

    @ExcelProperty(value = "性别", index = 2)
    @ColumnWidth(8)
    private String genderName;

    @ExcelProperty(value = "J号", index = 3)
    @ColumnWidth(15)
    private String policeNo;


    @ExcelProperty(value = "单位名称", index = 5)
    @ColumnWidth(50)
    private String bzDwCodeName;

    @ExcelProperty(value = "人员类别", index = 6)
    @ColumnWidth(30)
    private String assessTypeName;
}
