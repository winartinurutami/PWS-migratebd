/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klmpk8.traktix;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author winarti nur utami
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findByIdKereta", query = "SELECT d FROM Data d WHERE d.idKereta = :idKereta"),
    @NamedQuery(name = "Data.findByKotaAsal", query = "SELECT d FROM Data d WHERE d.kotaAsal = :kotaAsal"),
    @NamedQuery(name = "Data.findByKotaTujuan", query = "SELECT d FROM Data d WHERE d.kotaTujuan = :kotaTujuan"),
    @NamedQuery(name = "Data.findByTanggal", query = "SELECT d FROM Data d WHERE d.tanggal = :tanggal")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kereta")
    private String idKereta;
    @Column(name = "kota_asal")
    private String kotaAsal;
    @Column(name = "kota_tujuan")
    private String kotaTujuan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    public Data() {
    }

    public Data(String idKereta) {
        this.idKereta = idKereta;
    }

    public String getIdKereta() {
        return idKereta;
    }

    public void setIdKereta(String idKereta) {
        this.idKereta = idKereta;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKereta != null ? idKereta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.idKereta == null && other.idKereta != null) || (this.idKereta != null && !this.idKereta.equals(other.idKereta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klmpk8.traktix.Data[ idKereta=" + idKereta + " ]";
    }
    
}
