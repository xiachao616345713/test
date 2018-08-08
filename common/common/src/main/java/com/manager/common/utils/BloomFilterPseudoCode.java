package com.manager.common.utils;

/**
 * bloom filter pseudo code
 * <p>数组元素大于0</p>
 * <p>一个byte记录8个数，byte[0]记录0,1,2,3,4,5,6,7,即二进制11111111</p>
 * <p>https://blog.csdn.net/zdxiq000/article/details/57626464</p>
 *
 * @author chao
 * @since 2018-08-08
 */
public class BloomFilterPseudoCode {


    private static final int _1MB = 1024 * 1024;
    //每个byte记录8bit信息,也就是8个数是否存在于数组中
    private static byte[] flags = new byte[ 512 * _1MB ];

    public static void main(String[] args) {
        //待判重数据
        int[] array = {255, 1024, 0, 65536, 255};

        int index = 0;
        for(int num : array) {
            if(!getFlags(num)) {
                //未出现的元素
                array[index] = num;
                index = index + 1;
                //设置标志位
                setFlags(num);
                System.out.println("set " + num);
            } else {
                System.out.println(num + " already exist");
            }
        }
    }

    private static void setFlags(int num) {
        //使用每个数的低三位作为byte内的映射
        //例如: 255 = 0x11111111
        //低三位(也就是num & (0x07))为0x111 = 7, 则byte的第7位为1, 表示255已存在
        flags[num >> 3] |= 0x01 << (num & (0x07));
    }

    private static boolean getFlags(int num) {
        return (flags[num >> 3] >> (num & (0x07)) & 0x01) == 0x01;
    }
}
