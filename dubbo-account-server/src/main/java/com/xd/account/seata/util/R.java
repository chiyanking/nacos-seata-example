package com.xd.account.seata.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname R
 * @Description 操作返回结果
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-11-25 12:54
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private boolean success;
    private String msg;
    private T data;
}
