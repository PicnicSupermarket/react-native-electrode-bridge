package com.walmartlabs.electrode.reactnative.sample.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.walmartlabs.electrode.reactnative.bridge.Bridgeable;

import static com.walmartlabs.electrode.reactnative.bridge.util.BridgeArguments.getIntegerValue;

public class Status implements Parcelable, Bridgeable {

    private Boolean log;
    private Boolean member;

    private Status() {
    }

    private Status(Builder builder) {
        this.log = builder.log;
        this.member = builder.member;
    }

    private Status(Parcel in) {
        this(in.readBundle());
    }

    public Status(Bundle bundle) {
        this.log = bundle.containsKey("log") ? bundle.getBoolean("log") : null;
        this.member = bundle.getBoolean("member");
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    /**
     * Log ???
     *
     * @return Boolean
     */
    @Nullable
    public Boolean getLog() {
        return log;
    }

    /**
     * Is the user a Sam&#39;s club member
     *
     * @return Boolean
     */
    @NonNull
    public Boolean getMember() {
        return member;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(toBundle());
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if(log != null) {
            bundle.putBoolean("log", log);
        }
        bundle.putBoolean("member", member);
        return bundle;
    }

    public static class Builder {
        private Boolean log;
        private final Boolean member;

        public Builder(@NonNull Boolean member) {
            this.member = member;
        }

        @NonNull
        public Builder log(@Nullable Boolean log) {
            this.log = log;
            return this;
        }

        @NonNull
        public Status build() {
            return new Status(this);
        }
    }
}