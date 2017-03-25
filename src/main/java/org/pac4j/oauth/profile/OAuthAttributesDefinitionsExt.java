package org.pac4j.oauth.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.wechat.WeChatAttributesDefinition;

/**
 * 参考OAuthAttributesDefinitions
 * 
 * @author linzl
 *
 */
public class OAuthAttributesDefinitionsExt {
	public final static AttributesDefinition wechatDefinition = new WeChatAttributesDefinition();
}
