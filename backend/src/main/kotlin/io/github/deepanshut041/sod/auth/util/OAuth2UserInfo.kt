package io.github.deepanshut041.sod.auth.util

import io.github.deepanshut041.sod.domain.model.enums.AuthProvider
import io.github.deepanshut041.sod.util.OAuth2AuthenticationProcessingException


abstract class OAuth2UserInfo(val attributes: Map<String, Any>) {
    abstract fun getId(): String
    abstract fun getName(): String
    abstract fun getEmail(): String
    abstract fun getImageUrl(): String?
}

class GoogleOAuth2UserInfo(attributes: Map<String, Any>): OAuth2UserInfo(attributes){
    override fun getId(): String  = attributes["sub"] as String
    override fun getName(): String = attributes["name"] as String
    override fun getEmail(): String = attributes["email"] as String
    override fun getImageUrl(): String? = attributes["picture"] as String

}

class GithubOAuth2UserInfo(attributes: Map<String, Any>) : OAuth2UserInfo(attributes) {
    override fun getId(): String = (attributes["id"] as Int).toString()
    override fun getName(): String = attributes["name"] as String
    override fun getEmail(): String = attributes["email"] as String
    override fun getImageUrl(): String? = attributes["avatar_url"] as String?
}

class FacebookOAuth2UserInfo(attributes: Map<String, Any>): OAuth2UserInfo(attributes){
    override fun getId(): String  = attributes["id"] as String
    override fun getName(): String = attributes["name"] as String
    override fun getEmail(): String = attributes["email"] as String
    override fun getImageUrl(): String? {
        if (attributes.containsKey("picture")) {
            val pictureObj = attributes["picture"] as Map<*, *>?
            if (pictureObj!!.containsKey("data")) {
                val dataObj = pictureObj["data"] as Map<*, *>?
                if (dataObj!!.containsKey("url")) {
                    return (dataObj["url"] as String?)!!
                }
            }
        }
        return null
    }
}

object OAuth2UserInfoFactory {
    fun getOAuth2UserInfo(registrationId: String, attributes: Map<String, Any>): OAuth2UserInfo {
        return when {
            registrationId.equals(AuthProvider.google.toString(), ignoreCase = true) -> GoogleOAuth2UserInfo(attributes)
            registrationId.equals(AuthProvider.facebook.toString(), ignoreCase = true) -> FacebookOAuth2UserInfo(attributes)
            registrationId.equals(AuthProvider.github.toString(), ignoreCase = true) -> GithubOAuth2UserInfo(attributes)
            else -> {
                throw OAuth2AuthenticationProcessingException("Sorry! Login with $registrationId is not supported yet.")
            }
        }
    }
}