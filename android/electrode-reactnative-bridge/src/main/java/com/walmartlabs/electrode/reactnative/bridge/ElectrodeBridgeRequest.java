package com.walmartlabs.electrode.reactnative.bridge;

import android.os.Bundle;

public class ElectrodeBridgeRequest {
    private static final int DEFAULT_REQUEST_TIMEOUT = 5000;

    private final String mType;
    private final Bundle mData;
    private final int mTimeoutMs;
    private DispatchMode mDispatchMode;

    public enum DispatchMode {
        JS, NATIVE, GLOBAL
    }

    private ElectrodeBridgeRequest(Builder requestBuilder) {
        mType = requestBuilder.mType;
        mData = requestBuilder.mData;
        mTimeoutMs = requestBuilder.mTimeoutMs;
        mDispatchMode = requestBuilder.mDispatchMode;
    }

    /**
     * @return The type of this request
     */
    public String getType() {
        return this.mType;
    }

    /**
     * @return The data of this request
     */
    public Bundle getData() {
        return this.mData;
    }

    /**
     * @return The timeout of this request
     */
    public int getTimeoutMs() {
        return this.mTimeoutMs;
    }

    /**
     * @return The dispatch mode of this request
     */
    public DispatchMode getDispatchMode() {
        return this.mDispatchMode;
    }

    public static class Builder {
        private final String mType;
        private Bundle mData;
        private int mTimeoutMs;
        private DispatchMode mDispatchMode;

        /**
         * Initializes a new request builder
         *
         * @param type The type of the request to build
         */
        public Builder(String type) {
            mType = type;
            mTimeoutMs = DEFAULT_REQUEST_TIMEOUT;
            mData = Bundle.EMPTY;
            mDispatchMode = DispatchMode.JS;
        }

        /**
         * Specifies the request timeout
         *
         * @param timeoutMs The timeout in milliseconds
         * @return Current builder instance for chaining
         */
        public Builder withTimeout(int timeoutMs) {
            this.mTimeoutMs = timeoutMs;
            return this;
        }

        /**
         * Specifies the request data
         *
         * @param data The data
         * @return Current builder instance for chaining
         */
        public Builder withData(Bundle data) {
            this.mData = data;
            return this;
        }

        /**
         * Specifies the dispatch mode
         *
         * @param dispatchMode The dispatch mode to use
         * @return Current builder instance for chaining
         */
        public Builder withDispatchMode(DispatchMode dispatchMode) {
            this.mDispatchMode = dispatchMode;
            return this;
        }

        /**
         * Builds the request
         *
         * @return The built request
         */
        public ElectrodeBridgeRequest build() {
            return new ElectrodeBridgeRequest(this);
        }
    }
}