package com.graduate.hospital_manage.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);


	@Autowired
	private ObjectMapper objectMapper ;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");

		String jsonResp = objectMapper.writeValueAsString(
				Result.getInstance(ResultCode.UN_AUTHENTICATED)
		) ;

		this.output(response, jsonResp) ;
	}

	private void output(HttpServletResponse resp, Object result) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType(MediaType.APPLICATION_JSON_VALUE) ;
		resp.getWriter().println(result);
	}


}
