package org.ekstep.ep.samza;

import java.util.Map;

public class Event {
    private Map<String, Object> data;

    public Event(Map<String, Object> data) {
        this.data = data;
    }

    public boolean belongsToAPartner() {
        String partnerId = getPartnerId();
        return partnerId !=null && !partnerId.isEmpty();
    }

    public String routeTo(){
        String partnerId = getPartnerId();
        return String.format("%s.events", partnerId);
    }

    public void updateType(){
        data.put("type","partner.events");
    }

    private String getPartnerId() {
        Map<String, Map<String, Object>> edata = (Map<String, Map<String, Object>>) data.get("edata");
        if(edata == null)
            return null;
        Map<String, Object> eks = edata.get("eks");
        if(eks==null)
            return null;
        return (String) eks.get("partnerid");
    }

    public Map<String, Object> getData() {
        return data;
    }
}