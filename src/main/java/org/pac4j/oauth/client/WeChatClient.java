package org.pac4j.oauth.client;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.OAuthAttributesDefinitionsExt;
import org.pac4j.oauth.profile.wechat.WeChatProfile;
import org.scribe.builder.api.WeiXinApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.WeiXinOAuth20ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 此类用于处理CAS与微信的OAUTH通信,类名必须以Client结尾
 * 
 * @author linzl
 *
 *         2016年11月30日
 */
public class WeChatClient extends BaseOAuth20Client<WeChatProfile> {
	protected static final Logger logger = LoggerFactory.getLogger(WeChatClient.class);

	public WeChatClient() {
	}

	public WeChatClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected WeChatClient newClient() {
		WeChatClient newClient = new WeChatClient();
		return newClient;
	}

	@Override
	protected void internalInit(final WebContext context) {
		super.internalInit(context);
		WeiXinApi20 api = new WeiXinApi20();
		this.service = new WeiXinOAuth20ServiceImpl(api,
				new OAuthConfig(this.key, this.secret, this.callbackUrl, SignatureType.Header, null, null),
				this.connectTimeout, this.readTimeout, this.proxyHost, this.proxyPort);
	}

	/**
	 * 获取用户信息
	 */
	@Override
	protected String getProfileUrl(final Token accessToken) {
		return "https://api.weixin.qq.com/sns/userinfo";
	}

	/**
	 * 解析微信返回的数据
	 */
	@Override
	protected WeChatProfile extractUserProfile(String body) {
		WeChatProfile profile = new WeChatProfile();
		final JsonNode json = JsonHelper.getFirstNode(body);
		if (null != json) {
			profile.setId(JsonHelper.get(json, "id"));
			for (final String attribute : OAuthAttributesDefinitionsExt.wechatDefinition.getPrincipalAttributes()) {
				profile.addAttribute(attribute, JsonHelper.get(json, attribute));
			}
		}
		return profile;
	}

	@Override
	protected boolean hasBeenCancelled(final WebContext context) {
		return false;
	}

}
