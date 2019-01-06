package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.*;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.model.DomainName;
import io.everitoken.sdk.java.model.GroupName;
import io.everitoken.sdk.java.model.TokenName;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.PublicKeysParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.List;

public class Api {
    private NetParams netParams;
    private KeyProvider keyProvider;

    public Api(NetParams netParams, @Nullable KeyProvider keyProvider) {
        this.netParams = netParams;
        this.keyProvider = keyProvider;
    }

    public Api() {
        this(new TestNetNetParams(), null);
    }

    public JSONObject getInfo() throws EvtSdkException {
        Info info = new Info();
        return info.get(netParams, null);
    }

    public JSONObject getHeadBlockHeaderState() throws EvtSdkException {
        HeadBlockHeaderState headBlockHeaderState = new HeadBlockHeaderState();
        return headBlockHeaderState.get(netParams, null);
    }


    public List<DomainName> getCreatedDomains(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryDomain historyDomain = new HistoryDomain();
        return historyDomain.get(netParams, publicKeysParams);
    }

    public List<TokenName> getOwnedTokens(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryToken historyToken = new HistoryToken();
        return historyToken.get(netParams, publicKeysParams);
    }

    public List<GroupName> getManagedGroups(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryGroup historyGroup = new HistoryGroup();
        return historyGroup.get(netParams, publicKeysParams);
    }

    public static void main(String[] args) {
        Api api = new Api();

        try {
            PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
            });
            List<GroupName> res = api.getManagedGroups(publicKeysParams);
            res.stream().forEach(group -> System.out.println(group.getName()));
            System.out.println(res);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}
