package com.nekoimi.fastclaw.framework.utils;

import cn.hutool.core.util.NumberUtil;

/**
 * nekoimi  2022/3/30 13:34
 */
public class MathUtils {

    /**
     * 安全除法
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 精确度，保留小数位数
     * @return
     */
    public static double div(double a, double b, int scale) {
        if (a == 0 || b == 0) {
            return 0;
        }
        double v = NumberUtil.div(a, b, scale);
        String format = "%." + scale + "f";
        return NumberUtil.parseDouble(String.format(format, v));
    }

    /**
     * 安全除法 带百分号
     * 需要先除 得带结果 * 100 之后再进行保留小数位
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 精确度，保留小数位数
     * @return
     */
    public static double divWithPercent(double a, double b, int scale) {
        if (a == 0 || b == 0) {
            return 0;
        }
        double v = NumberUtil.div(a, b, scale) * 100;
        String format = "%." + scale + "f";
        return NumberUtil.parseDouble(String.format(format, v));
    }
}
