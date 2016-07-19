package com.laundryguy.booking.model.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Single Response class which can be used to return all type of data related to
 * User Profile.
 * Custom methods can be constructed using different parameters in the name of
 * static buildSuccess(params....) or static buildFail(params...),
 * Corresponding constructors can be called or created for such methods.
 *
 * @author tiru
 */
@JsonInclude(Include.NON_NULL)
public class RestApiResponse {
    private static Logger logger = LogManager.getLogger(RestApiResponse.class);

    private boolean success;
    private MessageApiResponse message;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MessageApiResponse getMessage() {
        return message;
    }

    public void setMessage(MessageApiResponse message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RestApiResponse() {
        super();
    }

    private RestApiResponse(boolean success, MessageApiResponse message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private RestApiResponse(boolean success, MessageApiResponse message) {
        this.success = success;
        this.message = message;
    }

    private RestApiResponse(boolean success) {
        this.success = success;
    }

    private RestApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    /**
     * A static function which returns the ApiResponse JSON object and calls the private
     * "relevant" constructor from within it. Create one when creating a new buildSuccess or buildFail
     * if required.
     *
     * @param data
     * @return
     */
    public static RestApiResponse buildSuccess(Object data) {
        logger.info(data);
        return new RestApiResponse(Boolean.TRUE, data);
    }

    public static RestApiResponse buildFail(MessageApiResponse message) {
        return new RestApiResponse(Boolean.FALSE, message);
    }

    public static RestApiResponse buildSuccess() {
        return new RestApiResponse(Boolean.TRUE);
    }

    public static RestApiResponse buildFail() {
        return new RestApiResponse(Boolean.FALSE);
    }
}
