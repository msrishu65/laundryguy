package com.laundryguy.model.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO class for the Message in Response Code.
 * @author tiru
 * @JsonInclude annotation to automatically remove null variables from the final JSON.
 */
@JsonInclude(Include.NON_NULL)
public class MessageApiResponse {

  private static final String CODE = "code";
  private static final String TEXT = "text";
  private String code;
  private String text;
  
  
  
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
  
  public String getText() {
    return text;
  }

  public MessageApiResponse(String code, String text) {
    this.code = code;
    this.text = text;
  }

  public void setText(String text) {
    this.text = text;
  }


  /**
   * Static builder for building the MessageApiResponse.
   * @param code
   * @param text
   * @return
   */
  public static MessageApiResponse build(String code,String text){
    MessageApiResponse messageApiResponse = new MessageApiResponse(code,text);
    return messageApiResponse;
  }
  
  public Map<String, String> toMap() {
	    Map<String,String> map=new HashMap<String, String>();
	    map.put(CODE,this.code);
	    map.put(TEXT,this.text);
	    return map;
	  }
  
  
}
