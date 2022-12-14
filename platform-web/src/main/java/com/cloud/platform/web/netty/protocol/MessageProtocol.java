package com.cloud.platform.web.netty.protocol;

import com.cloud.platform.common.constants.PlatformCommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 *  自己定义的协议
 *   数据包格式
 *  +——----——+——-----——+——----——+
 *  |协议开始标志|  长度             |   数据       |
 *  +——----——+——-----——+——----——+
 *  1.协议开始标志head_data，为int类型的数据，16进制表示为0X76
 *  2.传输数据的长度contentLength，int类型
 *  3.要传输的数据
 *
 * @Author: ZhouShuai
 * @Date: 2021-07-14 20:57
 */
@Data
@AllArgsConstructor
public class MessageProtocol {

    /**
     * 消息的开头的信息标志
     */
    private int headData = PlatformCommonConstant.Netty.HEAD_DATA;
    /**
     * 消息的长度
     */
    private int contentLength;
    /**
     * 消息的内容
     */
    private byte[] content;

    /**
     * 用于初始化，SmartCarProtocol
     *
     * @param contentLength
     *            协议里面，消息数据的长度
     * @param content
     *            协议里面，消息的数据
     */
    public MessageProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }


    @Override
    public String toString() {
        return "SmartCarProtocol [head_data=" + headData + ", contentLength="
                + contentLength + ", content=" + new String(content) + "]";
    }

}
