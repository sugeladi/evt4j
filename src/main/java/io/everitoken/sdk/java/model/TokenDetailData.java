package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TokenDetailData extends NameableResource implements Meta {
    private List<PublicKey> owner;
    private JSONArray metas;

    private TokenDetailData(JSONObject raw) throws JSONException {
        super(raw.getString("name"));
        name = raw.getString("domain");
        metas = raw.getJSONArray("metas");

        JSONArray owner = raw.getJSONArray("owner");
        this.owner = StreamSupport.stream(owner.spliterator(), true).map(publicKey -> {
            try {
                return new PublicKey((String) publicKey);
            } catch (EvtSdkException ex) {
                return null;
            }
        }).filter(key -> !Objects.isNull(key)).collect(Collectors.toList());
    }

    public static TokenDetailData create(JSONObject raw) throws NullPointerException, JSONException {
        Objects.requireNonNull(raw);
        return new TokenDetailData(raw);
    }

    public String getName() {
        return name;
    }

    public List<PublicKey> getOwner() {
        return owner;
    }

    public JSONArray getMetas() {
        return metas;
    }

    public String toString() {
        return String.format("%s, %s -> %s, %s -> %s", super.toString(), "Domain", getName(), "owner",
                owner.stream().map(PublicKey::toString).reduce(String::concat)
        );
    }
}