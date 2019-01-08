package io.everitoken.sdk.java.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TransactionDetail {
    private int blockNum;
    private String packedTrx;
    private String id;
    private String compression;
    private List<String> signatures;
    private JSONObject transaction;
    private String blockId;

    public TransactionDetail(JSONObject raw) {
        blockNum = raw.getInt("block_num");
        packedTrx = raw.getString("packed_trx");
        id = raw.getString("id");
        compression = raw.getString("compression");
        JSONArray signaturesArray = raw.getJSONArray("signatures");
        signatures =
                StreamSupport.stream(signaturesArray.spliterator(), false).map(sig -> sig.toString()).collect(Collectors.toList());
        transaction = raw.getJSONObject("transaction");
        blockId = raw.getString("block_id");
    }

    public int getBlockNum() {
        return blockNum;
    }

    public String getPackedTrx() {
        return packedTrx;
    }

    public String getId() {
        return id;
    }

    public String getCompression() {
        return compression;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public JSONObject getTransaction() {
        return transaction;
    }

    public String getBlockId() {
        return blockId;
    }
}