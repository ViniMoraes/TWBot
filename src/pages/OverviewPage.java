package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import constants.PageName;

public class OverviewPage extends Page{
	
	
	public OverviewPage() {
		this.pageName = PageName.OVERVIEW;
	}
	
	@Override
	public void goToRallyPoint(){
		//TODO - Checar se est� na visualiza��o sem gr�ficos
        WebElement lmain = browser.findElement(By.partialLinkText("Edif�cio principal"));
        lmain.click();
	}

	
}
