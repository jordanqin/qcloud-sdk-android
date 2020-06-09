package com.tencent.qcloud.cosxml.cssg;

import com.tencent.cos.xml.*;
import com.tencent.cos.xml.common.*;
import com.tencent.cos.xml.exception.*;
import com.tencent.cos.xml.listener.*;
import com.tencent.cos.xml.model.*;
import com.tencent.cos.xml.model.object.*;
import com.tencent.cos.xml.model.bucket.*;
import com.tencent.cos.xml.model.tag.*;
import com.tencent.cos.xml.transfer.*;
import com.tencent.qcloud.core.auth.*;
import com.tencent.qcloud.core.common.*;
import com.tencent.qcloud.core.http.*;
import com.tencent.cos.xml.model.service.*;
import com.tencent.qcloud.cosxml.cssg.BuildConfig;

import android.content.Context;
import android.util.Log;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.net.*;
import java.util.*;
import java.nio.charset.Charset;
import java.io.*;

public class DeleteObject {

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
     * 删除对象
     */
    private void deleteObject() {
        //.cssg-snippet-body-start:[delete-object]
        String bucket = "examplebucket-1250000000"; //存储桶名称，格式：BucketName-APPID
        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即对象键
        
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, cosPath);
        cosXmlService.deleteObjectAsync(deleteObjectRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult result) {
                DeleteObjectResult deleteObjectResult = (DeleteObjectResult) result;
            }
        
            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException,
                               CosXmlServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                } else {
                    serviceException.printStackTrace();
                }
            }
        });
        
        //.cssg-snippet-body-end
    }
    /**
     * 删除多个对象
     */
    private void deleteMultiObject() {
        //.cssg-snippet-body-start:[delete-multi-object]
        String bucket = "examplebucket-1250000000"; //存储桶，格式：BucketName-APPID
        List<String> objectList = new ArrayList<String>();
        objectList.add("exampleobject"); //对象在存储桶中的位置标识符，即对象键
        
        DeleteMultiObjectRequest deleteMultiObjectRequest = new DeleteMultiObjectRequest(bucket, objectList);
        deleteMultiObjectRequest.setQuiet(true);
        cosXmlService.deleteMultiObjectAsync(deleteMultiObjectRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult result) {
                DeleteMultiObjectResult deleteMultiObjectResult = (DeleteMultiObjectResult) result;
            }
        
            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException,
                               CosXmlServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                } else {
                    serviceException.printStackTrace();
                }
            }
        });
        
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
    public void testDeleteObjects() {
        initService();

        // 删除对象
        deleteObject();
        
        // 删除多个对象
        deleteMultiObject();
        
    }
}