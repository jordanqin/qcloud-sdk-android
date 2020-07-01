package com.tencent.qcloud.cosxml.cssg;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider;
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials;
import com.tencent.qcloud.core.auth.SessionQCloudCredentials;
import com.tencent.qcloud.core.common.QCloudClientException;

import org.junit.Test;

public class ObjectTagging {

    private Context context;
    private CosXmlService cosXmlService;

    public static class ServerCredentialProvider extends BasicLifecycleCredentialProvider {
        
        @Override
        protected QCloudLifecycleCredentials fetchNewCredentials() throws QCloudClientException {
    
            // 首先从您的临时密钥服务器获取包含了密钥信息的响应
    
            // 然后解析响应，获取密钥信息
            String tmpSecretId = "临时密钥 secretId";
            String tmpSecretKey = "临时密钥 secretKey";
            String sessionToken = "临时密钥 TOKEN";
            long expiredTime = 1556183496L;//临时密钥有效截止时间戳，单位是秒
    
            /*强烈建议返回服务器时间作为签名的开始时间，用来避免由于用户手机本地时间偏差过大导致的签名不正确 */
            // 返回服务器时间作为签名的起始时间
            long startTime = 1556182000L; //临时密钥有效起始时间，单位是秒
    
            // 最后返回临时密钥信息对象
            return new SessionQCloudCredentials(tmpSecretId, tmpSecretKey, sessionToken, startTime, expiredTime);
        }
    }

    /**
     * 设置对象标签
     */
    private void putObjectTagging() {
        //.cssg-snippet-body-start:[put-object-tagging]
//        String bucket = "examplebucket-1250000000"; //格式：BucketName-APPID
//        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即对象键。
//        PutObjectTaggingRequest putObjectTaggingRequest = new PutObjectTaggingRequest(bucket, cosPath);
//        // 设置标签
//        putObjectTaggingRequest.addTag("key", "value");
//        putObjectTaggingRequest.addTag("hello", "world");
//
//        cosXmlService.putObjectTaggingAsync(putObjectTaggingRequest, new CosXmlResultListener() {
//            @Override
//            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
//                PutObjectTaggingResult putObjectTaggingResult = (PutObjectTaggingResult) result;
//            }
//
//            @Override
//            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException,
//                               CosXmlServiceException serviceException)  {
//                if (clientException != null) {
//                    clientException.printStackTrace();
//                } else {
//                    serviceException.printStackTrace();
//                }
//            }
//        });
        //.cssg-snippet-body-end
    }

    /**
     * 获取对象标签
     */
    private void getObjectTagging() {
        //.cssg-snippet-body-start:[get-object-tagging]
//        String bucket = "examplebucket-1250000000"; //格式：BucketName-APPID
//        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即对象键。
//        GetObjectTaggingRequest getObjectTaggingRequest = new GetObjectTaggingRequest(bucket, cosPath);
//
//        cosXmlService.getObjectTaggingAsync(getObjectTaggingRequest, new CosXmlResultListener() {
//            @Override
//            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
//                GetObjectTaggingResult getObjectTaggingResult = (GetObjectTaggingResult)result;
//            }
//
//            @Override
//            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException,
//                               CosXmlServiceException serviceException)  {
//                if (clientException != null) {
//                    clientException.printStackTrace();
//                } else {
//                    serviceException.printStackTrace();
//                }
//            }
//        });
        //.cssg-snippet-body-end
    }

    /**
     * 删除对象标签
     */
    private void deleteObjectTagging() {
        //.cssg-snippet-body-start:[delete-object-tagging]
//        String bucket = "examplebucket-1250000000"; //格式：BucketName-APPID
//        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即对象键。
//        DeleteObjectTaggingRequest deleteObjectTaggingRequest = new DeleteObjectTaggingRequest(bucket, cosPath);
//
//        cosXmlService.deleteObjectTaggingAsync(deleteObjectTaggingRequest, new CosXmlResultListener() {
//            @Override
//            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
//                DeleteObjectTaggingResult getObjectTaggingResult = (DeleteObjectTaggingResult)result;
//            }
//
//            @Override
//            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException,
//                               CosXmlServiceException serviceException)  {
//                if (clientException != null) {
//                    clientException.printStackTrace();
//                } else {
//                    serviceException.printStackTrace();
//                }
//
//            }
//        });
        //.cssg-snippet-body-end
    }


    private void initService() {
        String region = "ap-guangzhou";
        
        CosXmlServiceConfig serviceConfig = new CosXmlServiceConfig.Builder()
                .setRegion(region)
                .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
                .builder();
        
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cosXmlService = new CosXmlService(context, serviceConfig, new ServerCredentialProvider());
    }

    @Test
    public void testObjectTagging() {
        initService();

        // 设置对象标签
        putObjectTagging();
        
        // 获取对象标签
        getObjectTagging();
        
        // 删除对象标签
        deleteObjectTagging();
        
    }
}
