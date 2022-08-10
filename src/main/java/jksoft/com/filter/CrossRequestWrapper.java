package jksoft.com.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletRequestWrapper; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CrossRequestWrapper extends HttpServletRequestWrapper {     
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	public CrossRequestWrapper(HttpServletRequest servletRequest) {         
		super(servletRequest);     
	}     
	
	@Override
	public String[] getParameterValues(String parameter) {       
		String[] values = super.getParameterValues(parameter); 
		
		if (values == null)  {                   
			return null;            
		}
		
		int count = values.length;       
		String[] encodedValues = new String[count];       

	    for (int i = 0; i < count; i++) {                  
	        encodedValues[i] = FilterUtil.encodeXSS(values[i]); 
	        
	        if(log.isDebugEnabled()){
	            log.debug("CrossRequestWrapper.getParameterValues encodeValues :: {} ", i, encodedValues[i]);
	        }
	    }
	    
		return encodedValues;     
	}     
	
	@Override
	public String getParameter(String parameter) {           
		String value = super.getParameter(parameter);           
		if (value == null) {                  
			return null;                   
		}
        value = FilterUtil.encodeXSS(value);
        
        if(log.isDebugEnabled()){
            log.debug("CrossRequestWrapper.getParameter encodeValues :: {} ", value);
        }
        
        return value;     
	}
	
	@Override
    public String getHeader(String name) {         
		String value = super.getHeader(name);         
		if (value == null)             
			return null;         
		return FilterUtil.encodeXSS(value);     
	}
	
	@Override
    public Map<String,String[]> getParameterMap(){
        Map<String,String[]> parameterMap = super.getParameterMap();

        Set<String> keySet = parameterMap.keySet();
        Iterator<String>  itrator = keySet.iterator();

        Map<String,String[]> cleanMap = new HashMap<String, String[]>();

        while(itrator.hasNext()){
            String key = itrator.next();
            String[] paramValues = parameterMap.get(key);

            if(paramValues == null){
                cleanMap.put(key, paramValues);
            } else{
                int count = paramValues.length;

                String[] encodedValues = new String[count];
                for (int i = 0; i < count; i++) {
                    encodedValues[i] = FilterUtil.encodeXSS(paramValues[i]);
                }

                cleanMap.put(key, encodedValues);
            }
        }

        return cleanMap;
    }
}
