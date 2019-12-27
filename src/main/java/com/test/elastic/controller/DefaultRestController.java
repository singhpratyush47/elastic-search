package com.test.elastic.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.elastic.domain.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Default API" }, description = "API from class DefaultRestController")
@RestController
@RequestMapping("/api")
public class DefaultRestController {

	private Logger log = LoggerFactory.getLogger(DefaultRestController.class);

	@GetMapping("/welcome")
	public String welcome() {
		log.info(StringUtils.join("Hello ", "This is ", "Spring Boot ", "REST API"));

		return "Welcome to Spring Boot";
	}

	@GetMapping("/time")
	public String time() {
		return LocalTime.now().toString();
	}

	@ApiOperation(value = "Get header and display it")
	@GetMapping("/header-one")
	public String headerByAnnotation(
			@ApiParam(value = "Header User-agent") @RequestHeader(name = "User-agent") String headerUserAgent,
			@ApiParam(value = "Header Practical-java") @RequestHeader(name = "Practical-java", required = false) String headerPracticalJava) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + headerUserAgent);
		sb.append(" || ");
		sb.append("Practical-java : " + headerPracticalJava);

		return sb.toString();
	}

	@GetMapping("/getHeaders")
	public String getHeaders(@RequestHeader(name="User-agent") String headerUserAgent,
			@RequestHeader(name="test",required=false) String test) {
		return headerUserAgent+" -------- "+test;
	}

	@GetMapping("/getHeadersFromServlet")
	public String getHeadersFromServlet(HttpServletRequest request) {
		String userAgent= request.getHeader("User-agent");
		String test= request.getHeader("test");
		return userAgent+" -------- "+test;
	}


	@GetMapping("/getHeadersFromServletContainer")
	public Map<String, String> getHeadersFromServletContainer(@RequestHeader HttpHeaders headers) {
		return headers.toSingleValueMap();
	}

	@ApiOperation(value="Get Sample Error Response")
	@GetMapping("/getErrorResponse/{test}")
	@ApiResponses({
		@ApiResponse(code=400,message="Bad request",response=ErrorResponse.class),
		@ApiResponse(code=200,message="Returns list Of Integer",response=Integer.class)
		
	})
	public ResponseEntity<Object> getErrorResponse(@ApiParam(value="test") @PathVariable String test) {
		
		List<String> dummyList=new ArrayList<String>
		(Arrays.asList(new String[] {"one","two","three","four","five"}));
		
		HttpHeaders responseHeaders=new HttpHeaders();
		responseHeaders.add(HttpHeaders.SERVER, "spring");
		responseHeaders.add("Custom", "Custom response Header");
		
		if(test.equals("test")) {
			ErrorResponse errorResponse=new ErrorResponse();
			errorResponse.setMessage("Error Occured");
			errorResponse.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<>(errorResponse, responseHeaders,HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().headers(responseHeaders).body(dummyList);
	}
	
	
	@GetMapping("/header-two")
	public String headerByServlet(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + request.getHeader("User-agent"));
		sb.append(" || ");
		sb.append("Practical-java : " + request.getHeader("Practical-java"));

		return sb.toString();
	}

	@GetMapping("/header-three")
	public Map<String, String> headerByAll(@RequestHeader HttpHeaders headers) {
		return headers.toSingleValueMap();
	}

	@GetMapping("/handle-exception")
	public String handleException() {
		if(1==1) {
			throw new IllegalArgumentException();
		}
		return "handleException";
	}

	@GetMapping("/random-error")
	public ResponseEntity<String> randomError() {
		int remainder = new Random().nextInt() % 6;
		String body = "Kibana";

		switch (remainder) {
		case 0:
			return ResponseEntity.ok().body(body);
		case 1:
		case 2:
			return ResponseEntity.badRequest().body(body);
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
	}

}