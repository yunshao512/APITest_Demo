package com.server.utils.functionsUtil;

import com.server.utils.RandomUtil;

public class RandomCHFunction implements Function{
    @Override
    public String execute(String[] args) {
        int len = args.length;
        int length = 6;// 默认为6
        if (len > 0) {// 第一个参数字符串长度
            length = Integer.valueOf(args[0]);
        }
        return RandomUtil.getRandomCH(length);
    }

    @Override
    public String getReferenceKey() {
        return "randomCH";
    }
}
