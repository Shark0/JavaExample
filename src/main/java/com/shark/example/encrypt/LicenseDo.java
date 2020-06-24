package com.shark.example.encrypt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class LicenseDo implements Serializable {
    @SerializedName("publicKey")
    private String publicKey;
    @SerializedName("privateKey")
    private String privateKey;
    @SerializedName("license")
    private String license;
}
