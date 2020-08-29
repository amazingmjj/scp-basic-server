package org.zhd.basic.util;

import org.xy.api.utils.FreeMarkerUtil;

/**
 * 自动生成entity,mapper,service,controller
 * !!! 一旦生成将覆盖原有的，谨慎使用
 *
 * @author juny
 */
public class GeneratorFile {
    public static void main(String[] args) {
        try {
            FreeMarkerUtil.generateDto2Entity("Area", "org.zhd.basic", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
