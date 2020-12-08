package com.paavam.learningcurve

import org.junit.Test

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nAuthentication $token"
            .let {
                next?.addHeader(it) ?: it
            }
}

class ContentTypeHeader(val contentType: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nContentType: $contentType"
            .let {
                next?.addHeader(it) ?: it
            }
}

class BodyPayloadHeader(val body: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) =
        "$inputHeader\n$body"
            .let {
                next?.addHeader(it) ?: it
            }
}

class ChainOfResponsibility {
    @Test
    fun testChainOfResponsibility() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("""Body: {"username":"john"}""")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Headers with authentication")
        println(messageWithAuthentication)

        println("----------")

        val messageWithoutAuthentication = contentTypeHeader.addHeader("Headers without authentication")
        println(messageWithoutAuthentication)
    }
}