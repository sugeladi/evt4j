package io.everitoken.sdk.java.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransactionDetailTest {

    @Test
    @DisplayName("Can create from json object")
    void create() {
        Assertions.assertDoesNotThrow(() -> {
            JSONObject obj = new JSONObject();
            obj.put("block_num", 1);
            obj.put("packed_trx", "test_packed_trx");
            obj.put("id", "test_id");
            obj.put("compression", "test_compression");
            obj.put("signatures", new JSONArray(new String[]{
                    "test_sig1", "test_sig2"
            }));
            obj.put("transaction", new JSONObject());
            obj.put("block_id", "test_block_id");
            TransactionDetail.create(obj);
        });
    }

    @Test
    @DisplayName("Throw exception when input is not valid json")
    void throwException() {
        Assertions.assertThrows(JSONException.class, () -> {
            TransactionDetail.create(new JSONObject());
        });
    }
}