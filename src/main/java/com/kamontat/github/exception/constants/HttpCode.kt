package com.kamontat.github.exception.constants

import com.beust.klaxon.json

/**
 * All HTTP Responded Status Code. <br></br>
 * Base on wikipedia and Ben Griffiths Code
 *
 *  * [Github gist](https://github.com/whostolebenfrog/status-codes/blob/master/src/main/java/com/floatbackwards/statuscodes/HttpCode.java)
 *  * [Wiki](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
 *

 * @author kamontat
 * *
 * @version 1.0
 * *
 * @since Fri 10/Mar/2017 - 12:19 AM
 */
enum class HttpCode constructor(
        /**
         * Returns the int status code this enum represents

         * @return the int status code this enum represents
         */
        val code: Int,
        /**
         * Returns a description of the HTTP status this enum represents

         * @return a description of the HTTP status this enum represents
         */
        val description: String) {
    /**
     * **Information** <br></br>
     * The server has received the request headers and the client should proceed to send the request body (in the case of a request for which a body needs to be sent; for example, a POST request).
     */
    Continue(100, "The client should continue with its request."),
    /**
     * **Information** <br></br>
     * The requester has asked the server to switch protocols and the server has agreed to do so.
     */
    Switching_Protocols(101, "Informs the client that the server will switch to the protocol specified in the Upgrade message header field."),
    /**
     * **Information** <br></br>
     * A WebDAV request may contain many sub-requests involving file operations, requiring a long time to complete the request. This code indicates that the server has received and is processing the request, but no response is available yet. This prevents the client from timing out and assuming the request was lost.
     */
    Processing(102, "The server requiring a long time to complete the request. This prevents the client from timing out and assuming the request was lost."),
    /**
     * **Information** <br></br>
     * (ResumableHttpRequestsProposal) Used in the resumable requests proposal to resume aborted PUT or POST requests.
     */
    Checkpoint(103, "Used in the resumable requests proposal to resume aborted PUT or POST requests.[65]"),
    /**
     * **Information** <br></br>
     * (Oku, Kazuho) Used to return some response headers before entire HTTP response.
     */
    Early_Hints(103, "Used to return some response headers before entire HTTP response."),

    // --------------------------------------------------------------------

    /**
     * **Success** <br></br>
     * Standard response for successful HTTP requests. The actual response will depend on the request method used. In a GET request, the response will contain an entity corresponding to the requested resource. In a POST request, the response will contain an entity describing or containing the result of the action.
     */
    OK(200, "The request sent by the client was successful."),
    /**
     * **Success** <br></br>
     * The request has been fulfilled, resulting in the creation of a new resource.
     */
    Created(201, "The request was successful and the resource has been created."),
    /**
     * **Success** <br></br>
     * The request has been accepted for processing, but the processing has not been completed. The request might or might not be eventually acted upon, and may be disallowed when processing occurs.
     */
    Accepted(202, "The request has been accepted but has not yet finished processing."),
    /**
     * **Success** <br></br>
     * The server is a transforming proxy (e.g. a Web accelerator) that received a 200 OK from its origin, but is returning a modified version of the origin's response.
     */
    Non_Authoritative_Information(203, "The returned meta-information in the entity header is not the definitive set of information, it might be a local copy or contain local alterations."),
    /**
     * **Success** <br></br>
     * The server successfully processed the request and is not returning any content.
     */
    No_Content(204, "The request was successful but not require the return of an entity body."),
    /**
     * **Success** <br></br>
     * The server successfully processed the request, but is not returning any content. Unlike a 204 response, this response requires that the requester reset the document view.
     */
    Reset_Content(205, "The request was successful and the user agent should reset the view that sent the request."),
    /**
     * **Success** <br></br>
     * The server is delivering only part of the resource (byte serving) due to a range header sent by the client. The range header is used by HTTP clients to enable resuming of interrupted downloads, or split a download into multiple simultaneous streams.
     */
    Partial_Content(206, "The partial request was successful."),
    /**
     * **Success** <br></br>
     * The message body that follows is an XML message and can contain a number of separate response codes, depending on how many sub-requests were made.
     */
    Multi_Status(207, "The message body that follows is an XML message and can contain a number of separate response codes, depending on how many sub-requests were made."),
    /**
     * **Success** <br></br>
     * The members of a DAV binding have already been enumerated in a previous reply to this request, and are not being included again.
     */
    Already_Reported(208, "The members of a DAV binding have already been enumerated in a previous reply to this request, and are not being included again.[16]"),
    /**
     * **Success** <br></br>
     * The server has fulfilled a request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance.
     */
    IM_Used(226, "The server has fulfilled a request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."),

    // --------------------------------------------------------------------

    /**
     * **Redirection** <br></br>
     * Indicates multiple options for the resource from which the client may choose (via agent-driven content negotiation). For example, this code could be used to present multiple video format options, to list files with different filename extensions, or to suggest word-sense disambiguation.
     */
    Multiple_Choices(300, "The requested resource has multiple choices, each of which has a different location."),
    /**
     * **Redirection** <br></br>
     * This and all future requests should be directed to the given URI.
     */
    Moved_Permanently(301, "The requested resources has moved permanently to a new location."),
    /**
     * **Redirection** <br></br>
     * This is an example of industry practice contradicting the standard. The HTTP/1.0 specification (RFC 1945) required the client to perform a temporary redirect (the original describing phrase was "Moved Temporarily"), but popular browsers implemented 302 with the functionality of a 303 See Other. Therefore, HTTP/1.1 added status codes 303 and 307 to distinguish between the two behaviours.
     */
    Found(302, "The requested resource has been found at a different location but the client should use the original URI."),
    /**
     * **Redirection** <br></br>
     * The response to the request can be found under another URI using a GET method. When received in response to a POST (or PUT/DELETE), the client should presume that the server has received the data and should issue a redirect with a separate GET message.
     */
    See_Other(303, "The requested resource is located at a different location which should be returned by the location field in the response."),
    /**
     * **Redirection** <br></br>
     * Indicates that the resource has not been modified since the version specified by the request headers If-Modified-Since or If-None-Match. In such case, there is no need to retransmit the resource since the client still has a previously-downloaded copy.
     */
    Not_Modified(304, "The resource has not been modified since the last request."),
    /**
     * **Redirection** <br></br>
     * The requested resource is available only through a proxy, the address for which is provided in the response. Many HTTP clients (such as Mozilla and Internet Explorer) do not correctly handle responses with this status code, primarily for security reasons.
     */
    Use_Proxy(305, "The requested resource can only be accessed through a proxy which should be provided in the location field."),
    /**
     * **Redirection** <br></br>
     * No longer used.
     */
    Switch_Proxy(306, "No longer used. Originally meant Subsequent requests should use the specified proxy."),
    /**
     * **Redirection** <br></br>
     * In this case, the request should be repeated with another URI; however, future requests should still use the original URI. In contrast to how 302 was historically implemented, the request method is not allowed to be changed when reissuing the original request. For example, a POST request should be repeated using another POST request.
     */
    Temporary_Redirect(307, "The requested resource is temporarily moved to the provided location but the client should continue to use this location as the resource may again move."),
    /**
     * **Redirection** <br></br>
     * The request and all future requests should be repeated using another URI. 307 and 308 parallel the behaviors of 302 and 301, but do not allow the HTTP method to change. So, for example, submitting a form to a permanently redirected resource may continue smoothly.
     */
    Permanent_Redirect(308, "The request and all future requests should be repeated using another URI. Do not allow the HTTP method to change."),

    // --------------------------------------------------------------------

    /**
     * **Client Error** <br></br>
     * The server cannot or will not process the request due to an apparent client error (e.g., malformed request syntax, too large size, invalid request message framing, or deceptive request routing).
     */
    Bad_Request(400, "The request could not be understood by the server."),
    /**
     * **Client Error** <br></br>
     * Similar to 403 Forbidden, but specifically for use when authentication is required and has failed or has not yet been provided. The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource. See Basic access authentication and Digest access authentication.
     */
    Unauthorized(401, "The request requires authorization."),
    /**
     * **Client Error** <br></br>
     * Reserved for future use. The original intention was that this code might be used as part of some form of digital cash or micropayment scheme, but that has not happened, and this code is not usually used. Google Developers API uses this status if a particular developer has exceeded the daily limit on requests.
     */
    Payment_Required(402, "Reserved for future use."),
    /**
     * **Client Error** <br></br>
     * The request was valid, but the server is refusing action. The user might not have the necessary permissions for a resource.
     */
    Forbidden(403, "Whilst the server did understand the request, the server is refusing to complete it. This is not an authorization problem."),
    /**
     * **Client Error** <br></br>
     * The requested resource could not be found but may be available in the future. Subsequent requests by the client are permissible.
     */
    Not_Found(404, "The requested resource was not found."),
    /**
     * **Client Error** <br></br>
     * A request method is not supported for the requested resource; for example, a GET request on a form that requires data to be presented via POST, or a PUT request on a read-only resource.
     */
    Method_Not_Allowed(405, "The supplied method was not allowed on the given resource."),
    /**
     * **Client Error** <br></br>
     * The requested resource is capable of generating only content not acceptable according to the Accept headers sent in the request.
     */
    Not_Acceptable(406, "The resource is not able to return a response that is suitable for the characteristics required by the accept headers of the request."),
    /**
     * **Client Error** <br></br>
     * The client must first authenticate itself with the proxy.
     */
    Proxy_Authentication_Required(407, "The client must authenticate themselves with the proxy."),
    /**
     * **Client Error** <br></br>
     * The server timed out waiting for the request. According to HTTP specifications: The client did not produce a request within the time that the server was prepared to wait. The client MAY repeat the request without modifications at any later time.
     */
    Request_Timeout(408, "The client did not supply a request in the period required by the server."),
    /**
     * **Client Error** <br></br>
     * Indicates that the request could not be processed because of conflict in the request, such as an edit conflict between multiple simultaneous updates.
     */
    Conflict(409, "The request could not be completed as the resource is in a conflicted state."),
    /**
     * **Client Error** <br></br>
     * Indicates that the resource requested is no longer available and will not be available again. This should be used when a resource has been intentionally removed and the resource should be purged. Upon receiving a 410 status code, the client should not request the resource in the future. Clients such as search engines should remove the resource from their indices.[40] Most use cases do not require clients and search engines to purge the resource, and a "404 Not Found" may be used instead.
     */
    Gone(410, "The requested resource is no longer available on the server and no redirect address is available."),
    /**
     * **Client Error** <br></br>
     * The request did not specify the length of its content, which is required by the requested resource.
     */
    Length_Required(411, "The server will not accept the request without a Content-Length field."),
    /**
     * **Client Error** <br></br>
     * The server does not meet one of the preconditions that the requester put on the request.
     */
    Precondition_Failed(412, "The supplied precondition evaluated to false on the server."),
    /**
     * **Client Error** <br></br>
     * The request is larger than the server is willing or able to process. Previously called "Request Entity Too Large".
     */
    Request_Entity_Too_Large(413, "The request was unsuccessful because the request entity was larger than the server would allow"),
    /**
     * **Client Error** <br></br>
     * The URI provided was too long for the server to process. Often the result of too much data being encoded as a query-string of a GET request, in which case it should be converted to a POST request. Called "Request-URI Too Long" previously.
     */
    Requested_URI_Too_Long(414, "The request was unsuccessful because the requested URI is longer than the server is willing to process (that's what she said)."),
    /**
     * **Client Error** <br></br>
     * The request entity has a media type which the server or resource does not support. For example, the client uploads an image as image/svg+xml, but the server requires that images use a different format.
     */
    Unsupported_Media_Type(415, "The request was unsuccessful because the request was for an unsupported format."),
    /**
     * **Client Error** <br></br>
     * The client has asked for a portion of the file (byte serving), but the server cannot supply that portion. For example, if the client asked for a part of the file that lies beyond the end of the file. Called "Requested Range Not Satisfiable" previously.
     */
    Request_Range_Not_Satisfiable(416, "The range of the resource does not overlap with the values specified in the requests Range header field and not alternative If-Range field was supplied."),
    /**
     * **Client Error** <br></br>
     * The server cannot meet the requirements of the Expect request-header field.
     */
    Expectation_Failed(417, "The expectation supplied in the Expectation header field could not be met by the server."),
    /**
     * **Client Error** <br></br>
     * This code was defined in 1998 as one of the traditional IETF April Fools' jokes, in RFC 2324, Hyper Text Coffee Pot Control Protocol, and is not expected to be implemented by actual HTTP servers. The RFC specifies this code should be returned by teapots requested to brew coffee. This HTTP status is used as an Easter egg in some websites, including Google.com.
     */
    Im_A_Teapot(418, "I'm a teapot (IETF April Fools' jokes)"),
    /**
     * **Client Error** <br></br>
     * (Spring Framework) A deprecated response used by the Spring Framework when a method has failed.
     */
    Method_Failure(420, "A deprecated response when a method has failed."),
    /**
     * **Client Error** <br></br>
     * (Twitter) Returned by version 1 of the Twitter Search and Trends API when the client is being rate limited; versions 1.1 and later use the 429 Too Many Requests response code instead.
     */
    Enhance_Your_Calm(420, "The client is being rate limited"),
    /**
     * **Client Error** <br></br>
     * The request was directed at a server that is not able to produce a response (for example because a connection reuse).
     */
    Misdirected_Request(421, "The request was directed at a server that is not able to produce a response (for example because a connection reuse)"),
    /**
     * **Client Error** <br></br>
     * The request was well-formed but was unable to be followed due to semantic errors.
     */
    Unprocessed_Entity(422, "The request was well-formed but was unable to be followed due to semantic errors."),
    /**
     * **Client Error** <br></br>
     * The resource that is being accessed is locked.
     */
    Locked(423, "The resource that is being accessed is locked."),
    /**
     * **Client Error** <br></br>
     * The request failed due to failure of a previous request (e.g., a PROPPATCH).
     */
    Failed_Dependency(424, "The request failed due to failure of a previous request."),
    /**
     * **Client Error** <br></br>
     * The client should switch to a different protocol such as TLS/1.0, given in the Upgrade header field.
     */
    Upgrade_Required(426, "The client should switch to a different protocol , given in the Upgrade header field."),
    /**
     * **Client Error** <br></br>
     * The origin server requires the request to be conditional. Intended to prevent "the 'lost update' problem, where a client GETs a resource's state, modifies it, and PUTs it back to the server, when meanwhile a third party has modified the state on the server, leading to a conflict."
     */
    Precondition_Required(428, "The origin server requires the request to be conditional. Intended to prevent the lost update problem."),
    /**
     * **Client Error** <br></br>
     * The user has sent too many requests in a given amount of time. Intended for use with rate-limiting schemes.
     */
    Too_Many_Requests(429, "The user has sent too many requests in a given amount of time. Intended for use with rate-limiting schemes."),
    /**
     * **Client Error** <br></br>
     * The server is unwilling to process the request because either an individual header field, or all the header fields collectively, are too large.
     */
    Request_Header_Fields_Too_Large(431, "The server is unwilling to process the request because either an individual header field, or all the header fields, are too large."),
    /**
     * **Client Error** <br></br>
     * The client's session has expired and must log in again.
     */
    Login_Time_Out(440, "The client's session has expired."),
    /**
     * **Client Error** <br></br>
     * (nginx) Used to indicate that the server has returned no information to the client and closed the connection.
     */
    No_Response(444, "returned no information to the client and closed the connection."),
    /**
     * **Client Error** <br></br>
     * The server cannot honour the request because the user has not provided the required information.
     */
    Retry_With(449, "The server cannot honour the request because the user has not provided the required information."),
    /**
     * **Client Error** <br></br>
     * (Microsoft) The Microsoft extension code indicated when Windows Parental Controls are turned on and are blocking access to the given webpage.
     */
    Blocked_by_Windows_Parental_Controls(450, "when Windows Parental Controls are turned on and are blocking access to the given webpage."),
    /**
     * **Client Error** <br></br>
     * A server operator has received a legal demand to deny access to a resource or to a set of resources that includes the requested resource.[54] The code 451 was chosen as a reference to the novel Fahrenheit 451.
     */
    Unavailable_For_Legal_Reasons(451, "A server operator has received a legal demand to deny access to a resource or to a set of resources that includes the requested resource."),
    /**
     * **Client Error** <br></br>
     * (Exchange ActiveSync) Used when either a more efficient server is available or the server cannot access the users' mailbox.[79] The client is expected to re-run the HTTP AutoDiscover operation to find a more appropriate server.
     */
    Redirect(451, "Used when either a more efficient server is available or the server cannot access the users' mailbox."),
    /**
     * **Client Error** <br></br>
     * (nginx) An expansion of the 400 Bad Request response code, used when the client has provided an invalid client certificate.
     */
    SSL_Certificate_Error(495, "The client has provided an invalid client certificate."),
    /**
     * **Client Error** <br></br>
     * (nginx) An expansion of the 400 Bad Request response code, used when a client certificate is required but not provided.
     */
    SSL_Certificate_Required(496, "A client certificate is required but not provided."),
    /**
     * **Client Error** <br></br>
     * (nginx) An expansion of the 400 Bad Request response code, used when the client has made a HTTP request to a port listening for HTTPS requests.
     */
    HTTP_Request_Sent_to_HTTPS_Port(497, "client has made a HTTP request to a port listening for HTTPS requests."),
    /**
     * **Client Error** <br></br>
     * (ArcGIS Server) indicates an expired or otherwise invalid token.
     */
    Invalid_Token(498, "indicates an expired or otherwise invalid token."),
    /**
     * **Client Error** <br></br>
     * (ArcGIS Server) indicates that a token is required but was not submitted.
     */
    Token_Required(499, "indicates that a token is required but was not submitted."),
    /**
     * **Client Error** <br></br>
     * (nginx) Used when the client has closed the request before the server could send a response.
     */
    Client_Closed_Request(499, "The client has closed the request before the server could send a response."),

    // --------------------------------------------------------------------

    /**
     * **Server Error** <br></br>
     * A generic error message, given when an unexpected condition was encountered and no more specific message is suitable.
     */
    Internal_Server_Error(500, "The request was unsuccessful because the server encountered an unexpected error."),
    /**
     * **Server Error** <br></br>
     * The server either does not recognize the request method, or it lacks the ability to fulfill the request. Usually this implies future availability (e.g., a new feature of a web-service API).
     */
    Not_Implemented(501, "The server does not support the request."),
    /**
     * **Server Error** <br></br>
     * The server was acting as a gateway or proxy and received an invalid response from the upstream server.
     */
    Bad_Gateway(502, "The server, whilst acting as a proxy, received an invalid response from the server that was fulfilling the request."),
    /**
     * **Server Error** <br></br>
     * The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this is a temporary state.
     */
    Service_Unavailable(503, "The request was unsuccessful as the server is either down or slash reedited."),
    /**
     * **Server Error** <br></br>
     * The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.
     */
    Gateway_Timeout(504, "The server, whilst acting as a proxy, did not receive a response from the upstream server in an acceptable time."),
    /**
     * **Server Error** <br></br>
     * The server does not support the HTTP protocol version used in the request.
     */
    Http_Version_Not_Supported(505, "The server does not supported the HTTP protocol version specified in the request"),
    /**
     * **Server Error** <br></br>
     * Transparent content negotiation for the request results in a circular reference.
     */
    Variant_Also_Negotiates(506, "Transparent content negotiation for the request results in a circular reference."),
    /**
     * **Server Error** <br></br>
     * The server is unable to store the representation needed to complete the request.
     */
    Insufficient_Storage(507, "The server is unable to store the representation needed to complete the request."),
    /**
     * **Server Error** <br></br>
     * The server detected an infinite loop while processing the request.
     */
    Loop_Detected(508, "The server detected an infinite loop while processing the request."),
    /**
     * **Server Error** <br></br>
     * The server has exceeded the bandwidth specified by the server administrator; this is often used by shared hosting providers to limit the bandwidth of customers.
     */
    Bandwidth_Limit_Exceeded(509, "The server has exceeded the bandwidth specified by the server administrator."),
    /**
     * **Server Error** <br></br>
     * Further extensions to the request are required for the server to fulfill it.
     */
    Not_Extended(510, "Further extensions to the request are required for the server to fulfill it."),
    /**
     * **Server Error** <br></br>
     * The client needs to authenticate to gain network access. Intended for use by intercepting proxies used to control access to the network (e.g., "captive portals" used to require agreement to Terms of Service before granting full Internet access via a Wi-Fi hotspot).
     */
    Network_Authentication_Required(511, "The client needs to authenticate to gain network access."),
    /**
     * **Server Error** <br></br>
     * (The Pantheon web platform) Used to indicate a site that has been frozen due to inactivity.
     */
    Site_is_Frozen(530, "indicate a site that has been frozen due to inactivity."),
    /**
     * **Server Error** <br></br>
     * Used by some HTTP proxies to signal a network read timeout behind the proxy to a client in front of the proxy.
     */
    Network_read_timeout_error(598, "to signal a network read timeout behind the proxy to a client in front of the proxy."),
    /**
     * **Server Error** <br></br>
     * Used to indicate when the connection to the network times out.
     */
    Network_connect_timeout_error(599, "indicate when the connection to the network times out."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) The 520 error is used as a "catch-all response for when the origin server returns something unexpected", listing connection resets, large headers, and empty or invalid responses as common triggers.
     */
    Unknown_Error(520, "server returns something unexpected."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) The origin server has refused the connection from Cloudflare.
     */
    Web_Server_is_Down(521, "refused the connection."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Cloudflare could not negotiate a TCP handshake with the origin server.
     */
    Connection_Timed_Out(522, "cannot negotiate a TCP handshake with the origin server."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Cloudflare could not reach the origin server; for example, if the DNS records for the origin server are incorrect.
     */
    Origin_is_Unreachable(523, "cannot reach the origin server"),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Cloudflare was able to complete a TCP connection to the origin server, but did not receive a timely HTTP response.
     */
    A_Timeout_Occurred(524, "did not receive a timely HTTP response."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Cloudflare could not negotiate a SSL/TLS handshake with the origin server.
     */
    SSL_Handshake_Failed(525, "cannot negotiate a SSL/TLS handshake with the origin server."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Cloudflare could not validate the SSL/TLS certificate that the origin server presented.
     */
    Invalid_SSL_Certificate(526, "cannot validate the SSL/TLS certificate that the origin server presented."),
    /**
     * **Server Error** <br></br>
     * (Cloudflare) Error 527 indicates that the requests timeout or failed after the WAN connection has been established.
     */
    Railgun_Error(527, "The requests timeout or failed after the WAN connection has been established."),


    // --------------------------------------------------------------------

    /**
     * unknown code or not import yet.
     */
    Unknown(999, "Unknown or unsupported HTTP status code");

    /**
     * Returns the name of the HTTP status this enum represents
     * @return the name of the HTTP status this enum represents
     */
    val title: String = this.name.replace("_", " ")

    /**
     * Returns a json string representing this main.java.HttpCode
     * @return a json string representing this main.java.HttpCode
     */
    val jsonString: String

    init {
        this.jsonString = toString()
    }

    final override fun toString(): String {
        return json {
            obj("code" to code, "name" to name, "description" to description)
        }.toJsonString(true)
    }

    companion object {

        /**
         * Returns the main.java.HttpCode object with a code matching the supplied int

         * @param httpStatus
         * * 		the httpStatus code
         * *
         * @return the Http Status object with a code matching the supplied int
         */
        fun getByCode(httpStatus: Int): Array<HttpCode> {
            val statusList = HttpCode.values().filter { it.code == httpStatus }
            return if (statusList.isEmpty()) arrayOf(Unknown) else statusList.toTypedArray()
        }

        /**
         * Returns the main.java.HttpCode with the Integer code that matches the supplied
         * String. Returns main.java.HttpCode.Unknown if the supplied String is not a valid
         * Integer or is not in the list of available HTTP status codes.

         * @param httpStatus
         * * 		the String containing the status code to match
         * *
         * @return the Http Status for the supplied String
         */
        fun getByCode(httpStatus: String): Array<HttpCode> {
            val statusCode: Int
            try {
                statusCode = Integer.parseInt(httpStatus)
            } catch (e: NumberFormatException) {
                return arrayOf(Unknown)
            }

            return getByCode(statusCode)
        }
    }
}