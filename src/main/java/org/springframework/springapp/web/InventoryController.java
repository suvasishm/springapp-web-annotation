package org.springframework.springapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.springapp.service.ProductManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class InventoryController {

	protected final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    
    private ProductManager productManager;

    @RequestMapping("/inventory.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String now = (new java.util.Date()).toString();
        logger.debug("returning hello view with " + now);
        
        /*Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());*/

                
        ModelAndView mav = new ModelAndView();
        mav.addObject("now", now);
        mav.addObject(this.productManager.getProducts());
        
        //return new ModelAndView("hello", "model", myModel);
        return mav;
    }
    
    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

}