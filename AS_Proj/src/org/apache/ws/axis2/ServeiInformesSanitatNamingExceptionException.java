
/**
 * ServeiInformesSanitatNamingExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package org.apache.ws.axis2;

public class ServeiInformesSanitatNamingExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1370883502124L;
    
    private org.apache.ws.axis2.ServeiInformesSanitatStub.ServeiInformesSanitatNamingException faultMessage;

    
        public ServeiInformesSanitatNamingExceptionException() {
            super("ServeiInformesSanitatNamingExceptionException");
        }

        public ServeiInformesSanitatNamingExceptionException(java.lang.String s) {
           super(s);
        }

        public ServeiInformesSanitatNamingExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ServeiInformesSanitatNamingExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.apache.ws.axis2.ServeiInformesSanitatStub.ServeiInformesSanitatNamingException msg){
       faultMessage = msg;
    }
    
    public org.apache.ws.axis2.ServeiInformesSanitatStub.ServeiInformesSanitatNamingException getFaultMessage(){
       return faultMessage;
    }
}
    