/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.logic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
public class ScheduleEJB {
    @PersistenceContext(unitName = Global.PERSISTENCE_UNIT)
    private EntityManager em;
    
}
