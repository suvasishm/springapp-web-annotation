package org.springframework.springapp.web;

import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.springapp.service.PriceIncrease;
import org.springframework.springapp.service.ProductManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.stereotype.Controller
@RequestMapping("/priceincrease.htm")
public class PriceIncreaseController {

	/** Logger for this class and subclasses */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private ProductManager productManager;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("priceIncrease") PriceIncrease priceIncrease, BindingResult result, Map model) throws ServletException {

		//new PriceIncreaseValidator().validate(priceIncrease, )
		int increase =priceIncrease.getPercentage();
		logger.info("Increasing prices by " + increase + "%.");

		productManager.increasePrice(increase);

		logger.info("returning from PriceIncreaseForm view to "
				+ "inventory.htm");
		
		return new ModelAndView(new RedirectView("inventory.htm"));
	}
	
			
	@RequestMapping(method = RequestMethod.GET)
    public PriceIncrease setupForm() {
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(20);
		return priceIncrease;
    }

	@Autowired
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

}
