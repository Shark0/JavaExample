package com.shark.example.encrypt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class LicenseContentDo implements Serializable {
    @SerializedName("1")
    private boolean algorithmPrediction;
    @SerializedName("2")
    private boolean algorithmRootCause;
    @SerializedName("3")
    private boolean algorithmProportion;
    @SerializedName("4")
    private boolean algorithmCorrVerification;
    @SerializedName("5")
    private boolean algorithmCorrExploration;

    @SerializedName("6")
    private int maxDataStorageSize;
    @SerializedName("7")
    private long expirationDate;
    @SerializedName("8")
    private int maxUsers;

    //    @SerializedName("showMaxDataStorageSize")
//    private int showMaxDataStorageSize;
}
