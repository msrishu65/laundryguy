package com.laundryguy.account.session.interceptor;

import com.laundryguy.account.session.model.dto.SessionDTO;
import com.laundryguy.account.session.service.SessionService;
import com.laundryguy.booking.helper.JsonHelper;
import com.laundryguy.booking.model.Exception.SessionException;
import com.laundryguy.booking.model.apiResponse.MessageApiResponse;
import com.laundryguy.booking.model.apiResponse.RestApiResponse;
import com.laundryguy.booking.model.constants.ParamConstants;
import com.laundryguy.booking.model.enums.SessionErrorCode;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.model.enums.UserProfileErrorCode;
import com.laundryguy.booking.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by maninder
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    SessionService sessionService;

    @Autowired
    JsonHelper jsonHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");

        UserClientType clientType = null;
        try {
            String client = request.getHeader(ParamConstants.X_M_CLIENT);
            clientType = UserClientType.fromIdentifier(Integer.parseInt(client));
            if (clientType.equals(UserClientType.Invalid))
                return buildJsonFailureResponse(UserProfileErrorCode.E107, response);

            request.setAttribute("client", clientType);
        } catch (NumberFormatException e) {
            return buildJsonFailureResponse(UserProfileErrorCode.E107, response);
        }

        SessionDTO sessionDTO = null;
        try {
            sessionDTO = sessionService.authenticateTokenAndGetData(token, clientType);
            request.setAttribute("email", sessionDTO.getMemebrId());
        } catch (SessionException e) {
            return buildJsonFailureResponse(e.getErrorCode(), response);
        }
        if (StringUtils.isNotBlank(sessionDTO.getNewToken()))
            response.setHeader("token", sessionDTO.getNewToken());

        // saving all request important params in a single object
        String latitude = request.getHeader("latitude");
        String longitude = request.getHeader("longitude");
        String ipAddress = WebUtils.getClientIpAddress(request);
        return Boolean.TRUE;
    }

    private boolean buildJsonFailureResponse(UserProfileErrorCode codeEnum, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        RestApiResponse restApiResponse =
                RestApiResponse.buildFail(MessageApiResponse.build(codeEnum.name(), codeEnum.getErrorDesc()));
        response.getWriter().write(jsonHelper.toJson(restApiResponse));
        return false;
    }

    private boolean buildJsonFailureResponse(SessionErrorCode codeEnum, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        RestApiResponse restApiResponse =
                RestApiResponse.buildFail(MessageApiResponse.build(codeEnum.name(), codeEnum.getErrorDesc()));
        response.getWriter().write(jsonHelper.toJson(restApiResponse));
        return false;
    }

}
