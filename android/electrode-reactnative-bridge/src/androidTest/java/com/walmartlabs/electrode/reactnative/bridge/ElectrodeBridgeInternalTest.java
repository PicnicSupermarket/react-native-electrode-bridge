package com.walmartlabs.electrode.reactnative.bridge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.walmartlabs.electrode.reactnative.bridge.util.BridgeArguments;

import java.util.concurrent.CountDownLatch;

public class ElectrodeBridgeInternalTest extends BaseBridgeTestCase {

    public void testSendRequestForTimeOut() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ElectrodeBridge electrodeBridge = ElectrodeBridgeInternal.instance();

        ElectrodeBridgeRequest electrodeBridgeRequest = new ElectrodeBridgeRequest.Builder("sampleRequest").build();

        electrodeBridge.sendRequest(electrodeBridgeRequest, new ElectrodeBridgeResponseListener<Bundle>() {
            @Override
            public void onFailure(@NonNull FailureMessage failureMessage) {
                assertNotNull(failureMessage);
                countDownLatch.countDown();
            }

            @Override
            public void onSuccess(@Nullable Bundle responseData) {
                fail();
            }
        });
        waitForCountDownToFinishOrFail(countDownLatch);
    }

    public void testSendRequestWithEmptyRequestDataAndNonEmptyResponse() {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final String expectedResult = "yay tests";
        ElectrodeBridge electrodeBridge = ElectrodeBridgeInternal.instance();

        electrodeBridge.registerRequestHandler("sampleRequest", new ElectrodeBridgeRequestHandler<Bundle, Bundle>() {
            @Override
            public void onRequest(@Nullable Bundle payload, @NonNull ElectrodeBridgeResponseListener<Bundle> responseListener) {
                assertNotNull(payload);
                assertTrue(payload.isEmpty());
                assertNotNull(responseListener);
                responseListener.onSuccess(BridgeArguments.generateBundle(expectedResult, BridgeArguments.Type.RESPONSE));
                countDownLatch.countDown();
            }
        });


        ElectrodeBridgeRequest electrodeBridgeRequest = new ElectrodeBridgeRequest.Builder("sampleRequest").build();
        electrodeBridge.sendRequest(electrodeBridgeRequest, new ElectrodeBridgeResponseListener<Bundle>() {
            @Override
            public void onFailure(@NonNull FailureMessage failureMessage) {
                fail();
            }

            @Override
            public void onSuccess(@Nullable Bundle responseData) {
                assertNotNull(responseData);
                assertEquals(expectedResult, responseData.getString("rsp"));
                countDownLatch.countDown();
            }
        });
        waitForCountDownToFinishOrFail(countDownLatch);
    }

    public void testSendRequestWithRequestDataAndEmptyResponse() {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final String expectedInput = "expectedInput";
        ElectrodeBridge electrodeBridge = ElectrodeBridgeInternal.instance();

        electrodeBridge.registerRequestHandler("sampleRequest", new ElectrodeBridgeRequestHandler<Bundle, Bundle>() {
            @Override
            public void onRequest(@Nullable Bundle payload, @NonNull ElectrodeBridgeResponseListener<Bundle> responseListener) {
                assertNotNull(payload);
                assertEquals(expectedInput, payload.getString("req"));
                assertNotNull(responseListener);
                responseListener.onSuccess(null);
                countDownLatch.countDown();
            }
        });


        Bundle bundle = new Bundle();
        bundle.putString("req", expectedInput);
        ElectrodeBridgeRequest electrodeBridgeRequest = new ElectrodeBridgeRequest.Builder("sampleRequest").withData(bundle).build();
        electrodeBridge.sendRequest(electrodeBridgeRequest, new ElectrodeBridgeResponseListener<Bundle>() {
            @Override
            public void onFailure(@NonNull FailureMessage failureMessage) {
                fail();
            }

            @Override
            public void onSuccess(@Nullable Bundle responseData) {
                assertNotNull(responseData);
                assertTrue(responseData.isEmpty());
                countDownLatch.countDown();
            }
        });

        waitForCountDownToFinishOrFail(countDownLatch);
    }
}