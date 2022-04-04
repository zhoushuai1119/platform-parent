package com.cloud.platform.common.constants;


/**
 * @Author： Zhou Shuai
 * @Date： 17:49 2019/1/7
 * @Description：
 * @Version: 01
 */
public class PlatformCommonConstant {

    /**
     * 符号
     */
    public interface SymbolParam {
        /**
         * 逗号
         */
        String COMMA = ",";
        /**
         * 连接号
         */
        String HYPHEN = "-";
        /**
         * 等号
         */
        String EQUAL = "=";
        /**
         * 波浪号
         */
        String WAVE = "~";
        /**
         * 分割字段和值的符号
         */
        String SEMICOLON = ":";
        /**
         * 分割字段和值得符号
         */
        String VALUE_SYMBOL = "?";
        /**
         * 字符串符号
         */
        String STRING_SYMBOL = "\"";
        /**
         * 空格
         */
        String SPACE_SYMBOL = " ";
    }

    /**
     * 消息头
     */
    public interface Netty {
        int HEAD_DATA = 0X76;
    }

    /**
     * RocketMQ--Topic头
     */
    public interface Topic {
        /**
         * activiti-server TOPIC
         */
        String ACTIVITI_SERVER_TOPIC = "TP_ACTIVITI_SERVER_TOPIC";
        /**
         * activiti-server TRANSACTION TOPIC
         */
        String ACTIVITI_SERVER_TOPIC_TRANSACTION = "TP_ACTIVITI_SERVER_TOPIC_TRANSACTION";
        /**
         * order-server TOPIC
         */
        String ORDER_SERVER_TOPIC = "TP_ORDER_SERVER_TOPIC";
        /**
         * order-server TRANSACTION TOPIC
         */
        String ORDER_SERVER_TOPIC_TRANSACTION = "TP_ORDER_SERVER_TOPIC_TRANSACTION";
        /**
         * payment-server TOPIC
         */
        String PAYMENT_SERVER_TOPIC = "TP_PAYMENT_SERVER_TOPIC";
        /**
         * payment-server TRANSACTION TOPIC
         */
        String PAYMENT_SERVER_TOPIC_TRANSACTION = "TP_PAYMENT_SERVER_TOPIC_TRANSACTION";
        /**
         * product-server TOPIC
         */
        String PRODUCT_SERVER_TOPIC = "TP_PRODUCT_SERVER_TOPIC";
        /**
         * product-server TRANSACTION TOPIC
         */
        String PRODUCT_SERVER_TOPIC_TRANSACTION = "TP_PRODUCT_SERVER_TOPIC_TRANSACTION";
        /**
         * user-server TOPIC
         */
        String USER_SERVER_TOPIC = "TP_USER_SERVER_TOPIC";
        /**
         * user-server TRANSACTION TOPIC
         */
        String USER_SERVER_TOPIC_TRANSACTION = "TP_USER_SERVER_TOPIC_TRANSACTION";

    }


    /**
     * 定时任务发送的 TOPIC
     */
    public interface ScheduledJobTopic {
        String SCHEDULED_JOB_TOPIC = "TP_F_SC";
    }

    /**
     * 定时任务回调的 TOPIC:EVENTCODE
     */
    public interface FeedBackTopic {

        String FEEDBACK_TASK_TOPIC = "TP_F_FB";

        String FEEDBACK_TASK_EVENTCODE = "EC_RESULT";
    }

    /**
     * 定时任务MQ执行器名称
     */
    public interface ExecutorHandler {
        String EXECUTOR_HANDLER_NAME = "mqJobHandler";
    }

}
