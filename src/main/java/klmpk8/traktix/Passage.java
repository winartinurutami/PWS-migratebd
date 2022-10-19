/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klmpk8.traktix;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author winarti nur utami
 */
@Entity
@Table(name = "passage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passage.findAll", query = "SELECT p FROM Passage p"),
    @NamedQuery(name = "Passage.findByTipeid", query = "SELECT p FROM Passage p WHERE p.tipeid = :tipeid"),
    @NamedQuery(name = "Passage.findByTipePenumpang", query = "SELECT p FROM Passage p WHERE p.tipePenumpang = :tipePenumpang")})
public class Passage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipeid")
    private String tipeid;
    @Column(name = "tipe_penumpang")
    private String tipePenumpang;

    public Passage() {
    }

    public Passage(String tipeid) {
        this.tipeid = tipeid;
    }

    public String getTipeid() {
        return tipeid;
    }

    public void setTipeid(String tipeid) {
        this.tipeid = tipeid;
    }

    public String getTipePenumpang() {
        return tipePenumpang;
    }

    public void setTipePenumpang(String tipePenumpang) {
        this.tipePenumpang = tipePenumpang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipeid != null ? tipeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passage)) {
            return false;
        }
        Passage other = (Passage) object;
        if ((this.tipeid == null && other.tipeid != null) || (this.tipeid != null && !this.tipeid.equals(other.tipeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klmpk8.traktix.Passage[ tipeid=" + tipeid + " ]";
    }
    
}
