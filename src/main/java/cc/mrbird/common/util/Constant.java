package cc.mrbird.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Constant {

	static final String XLSX_SUFFIX = ".xlsx";


	static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * 默认时间格式
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 小红花默认发放时间 09:00:00
	 */
	public static final String DEFAULT_DATE_TIME = " 09:00:00";
	public static final String AccessKey = "ZjT_FX0FsSXMHBjKzEfA90BzyEoT0-O_B67QRPiL";
	public static final String SecretKey = "DxEBsZxKo4NdiLUi5QBKliLYVFffrs9dOTpeL9gP";
	public static final String bucket = "ywy-pro";
	/**
	 * 无头像用户展示照片
	 */
	public static final String NOT_HEAD_IMAGE = "http://yycmedia.image.alimmdn.com/RetailShare/d981c674-10dc-4861-9422-1fd4a8240b91";

	/**
	 * 一次同步日志的默认条数
	 */
	public static final Integer SYNC_LOG_NUMBER = 1000;

	/**
	 * 福利充值系统交易号在redis中key
	 */
	public static final String WELFARE_RECHARGE_KEY = "welfare_recharge_key";

	/**
	 * 福利账户交易流水号在redis中key
	 */
	public static final String WELFARE_SERIAL_KEY = "welfare_serial_key";

	/**
	 * 固定时间发放积分在redis中key
	 */
	public static final String FIXED_REWARD_KEY = "fixed_reward_key";

	/**
	 * 日期格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Latin-1字符格式
	 */
	public static final String LATIN_1_ENCODING = "ISO-8859-1";

	/**
	 * 默认字符格式
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 是否 （正常），是1
	 */
	public static final Byte DEFULT_YES = 1;

	/**
	 * 是否（不正常） ，否0
	 */
	public static final Byte DEFULT_NO = 0;

	/**
	 * 默认最大时间
	 */
	public static final String DEFAULT_MAX_DATE = "3000";

	/**
	 * 商品默认skardingKey
	 */
	public static final Long DEFAULT_SHARDING_KEY = 2016010000000000L;

	/**
	 * 导出excel异常的后缀标识
	 */
	public static final String SUFFIX_EXCEL_ERROR = "_EXCEL_ERROR";

	/**
	 * 预付款充值系统交易号在redis中key
	 */
	public static final String RECHARGE_KEY = "recharge_key";

	/**
	 * 系统异常的后缀标识
	 */
	public static final String MSG_SYSTEM_EXCEPTION = "系统可能存在网络中断、数据库无法连接，内存不足等系统性问题，请与管理员联系。";

	/**
	 * 批量更新成功的后缀标识
	 */
	public static final String SUFFIX_BATCH_SUCCESS = "_BATCH_SUCCESS";

	/**
	 * 批量更新失败的后缀标识
	 */
	public static final String SUFFIX_BATCH_ERROR = "_BATCH_ERROR";

	/**
	 * 蜂采易订单自动更新已完成状态的设置时间名称
	 */
	public static final String CHANNEL_UPDATE_STATUS_FINISH = "ordChannelStatusFinished";

	/**
	 * 普通订单自动更新已收货状态的设置时间名称
	 */
	public static final String UPDATE_STATUS_COM_RECEIPT = "ordCommonReceiptTime";

	/**
	 * 跨境订单自动更新已收货状态的设置时间名称
	 */
	public static final String UPDATE_STATUS_CROSS_RECEIPT = "ordCrossReceiptTime";

	/**
	 * 同步到来店易记录最多100条
	 */
	public static final Integer MAX_SYNC_NUM = 100;

	/**
	 * 采购订单自动更新已完成状态的设置时间名称
	 */
	public static final String UPDATE_STATUS_FINISH = "ordStatusFinished";

	/**
	 * Json格式化对象
	 */
	public static final ObjectMapper OBJECT_MAPPAER = new ObjectMapper();

	static {
		OBJECT_MAPPAER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		OBJECT_MAPPAER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	/**
	 * 订单来源：蜂采易交易单号
	 */
	public static final String SOURCE_FCY = "7";

	/**
	 * 预付款交易流水号在redis中key
	 */
	public static final String SERIAL_KEY = "serial_key";

	/**
	 * 订单号在redis中key
	 */
	public static final String ORDER_KEY = "order_key";

	/**
	 * 分页默认页数
	 */
	public static final int DEFAULT_PAGE = 0;

	/**
	 * 分页默认数据数量
	 */
	public static final int DEFAULT_SIZE = 20;

	/**
	 * 不分页默认全量
	 */
	public static final int DEFAULT_ALL = -1;

	/**
	 * 设置cookie过期时间为1年
	 */
	public static final int EXPIRATION_TIME = 365 * 24 * 60 * 60;

	/**
	 * 设置直采易cookie过期时间
	 */
	public static final int ENTERPRISE_LOGOUT_TIME = 60 * 60;

	/**
	 * 未设置订单更新为已完成时间时取该默认时间（单位：小时）
	 */
	public static final Integer DEFAULT_DATE = 15 * 24;

	/**
	 * 设置session超时时间（单位：秒）
	 */
	public static final int SESSION_TIMEOUT = 7200;

	/**
	 * 必填字段的默认值
	 */
	public static final Long DEFAULT_LONG = 0L;

	/**
	 * 必填字段的默认值
	 */
	public static final Integer DEFAULT_INT = 0;

	/**
	 * 必填字段的默认值
	 */
	public static final Byte DEFAULT_BYTE = 0;

	/**
	 * 初始化密码
	 */
	public static final String DEFAULT_PWD = "888888";

	/**
	 * 查询类型 1:按创建时间查询
	 */
	public static final Byte QUERY_TYPE = 1;

	/**
	 * APP对接请求超时时间(默认24小时，86400000ms)
	 */
	public static final String APP_REQUEST_TIME_OUT = "appRequestTimeOut";

	/**
	 * 无头像用户展示照片
	 */
	public static final String DEFAULT_IMAGE = "http://yycmedia.image.alimmdn.com/RetailShare/d981c674-10dc-4861-9422-1fd4a8240b91";

	/**
	 * 顽兔生成圆角参数
	 */
	public static final String WAN_TU_PARAMETER = "@100w_100h_1e_1c_25-2ci";

	/**
	 * 直采易商城信息
	 */
	public static final String ENTERPRISE_STORE_INFO = "enterprise_store_info";

	/**
	 * 直采易商城子账号信息
	 */
	public static final String ENTERPRISE_STORE_USER_INFO = "enterprise_store_user_info";

	/**
	 * 直采易商城资源权限
	 */
	public static final String ENTERPRISE_STORE_RESOURCE = "enterprise_store_resource";

	/**
	 * 预存款确认充值短信通知号码
	 */
	public static final String RECHARGE_SMS_CHECK_PHONE = "rechargeSmsCheckPhone";

	/**
	 * 商品中心-编辑图片时，限制图片大小 读取系统设置
	 */
	public static final String IMAGE_LIMIT_SIZE = "imageLimitSize";

	/**
	 * 商品中心-编辑图片时，限制图片宽高 读取系统设置
	 */
	public static final String IMAGE_LIMIT_WIDTH = "imageLimitWidth";

	/**
	 * 商品中心-编辑图片时，限制图片大小默认 最大不超过200k
	 */
	public static final int IMAGE_DEFAULT_SIZE = 200 * 1000;

	/**
	 * 商品中心-编辑图片时，限制图片宽高默认 最大不超过800像素
	 */
	public static final int IMAGE_DEFAULT_WIDTH = 800;

	/**
	 * GBK编码
	 */
	public static final String ENCODING_GBK = "GBK";

	/**
	 * 友盟推送消息最多一次50条
	 */
	public static final Integer MAX_SYNC_NUM_YM = 50;

	/**
	 * 降序
	 */
	public static final Integer SORT_DESC = 0;

	/**
	 * 升序
	 */
	public static final Integer SORT_ASC = 1;

	/**
	 * 物流对照字典组名称
	 */
	public static final String EXPRESS_COMPARE = "ExpressCompare";

	/**
	 * 等于
	 */
	public static final String OPERATE_EQUAL = "equal";

	/**
	 * 不等于
	 */
	public static final String OPERATE_UNEQUAL = "unequal";

	/**
	 * 升序
	 */
	public static final String DIRECTION_ASC = "asc";

	/**
	 * 降序
	 */
	public static final String DIRECTION_DESC = "desc";

	/**
	 * 默认sku单位
	 */
	public static final String DEFAULT_UNIT = "件";

	/**
	 * 默认sku单位
	 */
	public static final String DEFAULT_PSD_TYPE = "/psd";

	/**
	 * 海关限额
	 */
	public static final String CUSTOMS_QUOTA = "customsQuota";

	/**
	 * 短信默认前缀
	 */
	public static final String SMS_PREFIX_FCY = "【蜂采易】";

	/**
	 * 用户标识
	 */
	public static final String USER_NAME_FLAG = "FCY_USERNAME";

	/**
	 * 代理商shardingKey
	 */
	public static final String CHANNEL_SHARDINGKEY = "FCY_SHARDINGKEY";

	/**
	 * 获取平台商家id
	 */
	public static final String CHANNELID = "channelId";

	/**
	 * 商城企业id
	 */
	public static final String ENTERPRISE_STORE_ID = "enterprise_store_id";

	/**
	 * 微信小程序第三放session key
	 */
	public static final String MINI_PROGRAM_THIRD_SESSION_KEY = "mini_program_third_session_key";

	/**
	 * API项目 SESSION
	 */
	public static final String WEB_API_SESSION = "webApiSession";

	/**
	 * 是否授权全部商品：1是 0否
	 */
	public static final Byte IS_HAVE_ALL_GOODS = 1;

	/**
	 * 是否授权全部商品：1是 0否
	 */
	public static final Byte NOT_HAVE_ALL_GOODS = 0;

	/**
	 * 微信门店id
	 */
	public static final String WECHAT_STORE_ID = "wechat_store_id";

	/**
	 * 短信默认类型 0为通知
	 */
	public static final String SMS_NOTICFE = "0";

	/**
	 * 微信开放平台服务器推送ticket 10分钟推送一次
	 */
	public static final String COMPONET_VERIFY_TICKET = "component_verify_ticket";

	/**
	 * 微信开放平台component_access_token
	 */
	public static final String COMPONET_ACCESS_TOKEN = "component_access_token";

	/**
	 * redis 失效时间（单位：分钟）
	 */
	public static final Long REDIS_CLEAR_TIME = 30L;

	/**
	 * 调用微信接口返回代码
	 */
	public static final Integer WX_ERROR_CODE = 0;

	/**
	 * 调用微信接口返回代码(TOKEN过期)
	 */
	public static final Integer WX_ERROR_CODE_EXPIRED = 42001;

	/**
	 * 小程序模板的页面集合
	 */
	public static final String MINIPROGRAM_PAGES = "miniprogram_pages";

	/**
	 * 每个ip每分钟最大访问次数
	 */
	public static final String REQUEST_LIMIT_TIMES = "request_limit_times";

	/**
	 * 区域运营系统登录地址
	 */
	public static final String KEY_FCY_LOGIN_URL = "fcy_login_url";

	/**
	 * 区域运营系统DES加密Key
	 */
	public static final String KEY_FCY_LOGIN_DES_KEY = "fcy_login_des_key";

	/**
	 * 开启负库存开关返回默认库存数
	 */
	public static final int MINUS_INVENTORY_NUM = 9999;

	/**
	 * 积分别名
	 */
	public static final String REWARD_NICK_NAME = "REWARD_NICK_NAME";
}
