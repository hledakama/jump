/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.json;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.model.service.ProviderAddress;

/**
 *
 * @author client
 */
public class AddressJsonBuilder {
    private static ProviderAddress m_address;
    
    public static JsonObject buildAddress(@NotNull ProviderAddress anAddress) {
        m_address = anAddress;
        return Json.createObjectBuilder().add("address", Json.createObjectBuilder()
        .add("providerAddressTId", anAddress.getProviderAddressTId())
        .add("streetNumber", anAddress.getStreetNumber())
        .add("street1", anAddress.getStreet1())
        .add("street2", anAddress.getStreet2())
        .add("city", anAddress.getCity())
        .add("state", anAddress.getState())
        .add("country", anAddress.getCountry())
        .add("zipcode", anAddress.getZipcode())).build();
    }    


    
    public static JsonObjectBuilder builderAddress(@NotNull ProviderAddress anAddress) {
        m_address = anAddress;
        return Json.createObjectBuilder().add("address", Json.createObjectBuilder()
        .add("providerAddressTId", anAddress.getProviderAddressTId())
        .add("streetNumber", anAddress.getStreetNumber())
        .add("street1", anAddress.getStreet1())
        .add("street2", anAddress.getStreet2())
        .add("city", anAddress.getCity())
        .add("state", anAddress.getState())
        .add("country", anAddress.getCountry())
        .add("zipcode", anAddress.getZipcode()));
    }   
}
