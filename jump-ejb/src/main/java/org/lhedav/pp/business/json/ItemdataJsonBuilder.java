/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.json;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.ProviderAddress;

/**
 *
 * @author client
 */
public class ItemdataJsonBuilder { 
    private static Itemdata m_itemdata;
    
public ItemdataJsonBuilder() {   
}

public static JsonObject buildItemdata(@NotNull Itemdata anItemdata) {
     m_itemdata = anItemdata;   
     JsonObjectBuilder itemdataBuilder = Json.createObjectBuilder();
     
     JsonArrayBuilder providerAddressBuilder = Json.createArrayBuilder();
     List<ProviderAddress> theList = anItemdata.getProviderAddressList();
     for (ProviderAddress aProviderAddress : theList) {
	providerAddressBuilder.add(AddressJsonBuilder.builderAddress(aProviderAddress));
      }
     
     itemdataBuilder.add("Itemdata", Json.createObjectBuilder()
        .add("itemdataTId", m_itemdata.getItemdataTId())
        .add("mdate", m_itemdata.getMdate().toString())
        .add("avatar", m_itemdata.getAvatar())
        .add("comment", m_itemdata.getComment())
        .add("duration", m_itemdata.getDuration())
        .add("unit", m_itemdata.getUnit()));
     itemdataBuilder.add("provider_addresses", providerAddressBuilder);
     
     return itemdataBuilder.build();
}

public static JsonArray  buildProviderAddress(@NotNull Itemdata anItemdata) {
     m_itemdata = anItemdata;      
     JsonArrayBuilder providerAddressBuilder = Json.createArrayBuilder();
     List<ProviderAddress> theList = anItemdata.getProviderAddressList();
     for (ProviderAddress aProviderAddress : theList) {
	providerAddressBuilder.add(AddressJsonBuilder.builderAddress(aProviderAddress));
      }     
     return providerAddressBuilder.build();
}

    
}


