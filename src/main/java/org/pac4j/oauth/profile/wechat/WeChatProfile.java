
package org.pac4j.oauth.profile.wechat;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.core.profile.Gender;
import org.pac4j.oauth.profile.OAuth10Profile;

/**
 * 用于添加返回用户信息
 * 
 * @author linzl
 *
 *         2016年12月25日
 */
public class WeChatProfile extends OAuth10Profile {

	private static final long serialVersionUID = 6790248892408246089L;

	@Override
	protected AttributesDefinition getAttributesDefinition() {
		return new WeChatAttributesDefinition();
	}

	public String getOpenId() {
		return (String) getAttribute(WeChatAttributesDefinition.OPEN_ID);
	}

	public String getNickName() {
		return (String) getAttribute(WeChatAttributesDefinition.NICK_NAME);
	}

	public Gender getGender() {
		final Integer sex = (Integer) getAttribute(WeChatAttributesDefinition.SEX);
		Gender gender = null;
		switch (sex) {
		case 1:
			gender = Gender.MALE;
			break;
		case 2:
			gender = Gender.FEMALE;
			break;
		default:
			gender = Gender.UNSPECIFIED;
			break;
		}
		return gender;
	}

	public String getHeadimgurl() {
		return (String) getAttribute(WeChatAttributesDefinition.HEAD_IMG_URL);
	}

	public String getUnionid() {
		return (String) getAttribute(WeChatAttributesDefinition.UNION_ID);
	}

}
