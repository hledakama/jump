/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "UNIT_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u")
    , @NamedQuery(name = "Unit.findByUnitTId", query = "SELECT u FROM Unit u WHERE u.unitTId = :unitTId")
    , @NamedQuery(name = "Unit.findByUnit", query = "SELECT u FROM Unit u WHERE u.unit = :unit")})
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_unit", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "UNIT_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_unit" )
    @Column(name = "UNIT_T_ID")
    private Long unitTId;
    @Size(max = 10)
    @Column(name = "UNIT")
    private String unit;

    public Unit() {
    }

    public Unit(Long unitTId) {
        this.unitTId = unitTId;
    }

    public Long getUnitTId() {
        return unitTId;
    }

    public void setUnitTId(Long unitTId) {
        this.unitTId = unitTId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitTId != null ? unitTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.unitTId == null && other.unitTId != null) || (this.unitTId != null && !this.unitTId.equals(other.unitTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.Unit[ unitTId=" + unitTId + " ]";
    }
    
}
