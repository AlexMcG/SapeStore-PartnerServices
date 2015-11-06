
package com.sapestore.partner.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keyword", "isSortedByComments"
})
@XmlRootElement(name = "getSearchBookList")
public class GetSearchBookList {

    @XmlElement(nillable = true)
    protected String keyword;

    @XmlElement(nillable = false)
    protected boolean isSortedByComments;

    /**
     * Gets the value of the keyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the value of the keyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyword(String value) {
        this.keyword = value;
    }
    
    /**
     * Gets the value of the isSortedByComments property.
     * 
     * @return
     *     possible object is
     *     {@link boolean }
     *     
     */
    
    public boolean getIsSortedByComments() {
        return isSortedByComments;
    }

    /**
     * Sets the value of the isSortedByComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link boolean }
     *     
     */
    public void setIsSortedByComments(boolean value) {
        this.isSortedByComments = value;
    }

}
