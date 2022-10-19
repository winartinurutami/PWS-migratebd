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
@Table(name = "list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "List.findAll", query = "SELECT l FROM List l"),
    @NamedQuery(name = "List.findByIdTiket", query = "SELECT l FROM List l WHERE l.idTiket = :idTiket"),
    @NamedQuery(name = "List.findByKotaAsal", query = "SELECT l FROM List l WHERE l.kotaAsal = :kotaAsal"),
    @NamedQuery(name = "List.findByKotaTujuan", query = "SELECT l FROM List l WHERE l.kotaTujuan = :kotaTujuan"),
    @NamedQuery(name = "List.findByGerbong", query = "SELECT l FROM List l WHERE l.gerbong = :gerbong"),
    @NamedQuery(name = "List.findByKelas", query = "SELECT l FROM List l WHERE l.kelas = :kelas"),
    @NamedQuery(name = "List.findByWaktuAwal", query = "SELECT l FROM List l WHERE l.waktuAwal = :waktuAwal"),
    @NamedQuery(name = "List.findByWaktuAkhir", query = "SELECT l FROM List l WHERE l.waktuAkhir = :waktuAkhir"),
    @NamedQuery(name = "List.findByWaktuTempuh", query = "SELECT l FROM List l WHERE l.waktuTempuh = :waktuTempuh"),
    @NamedQuery(name = "List.findByHarga", query = "SELECT l FROM List l WHERE l.harga = :harga")})
public class List implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tiket")
    private String idTiket;
    @Basic(optional = false)
    @Column(name = "kota_asal")
    private String kotaAsal;
    @Basic(optional = false)
    @Column(name = "kota_tujuan")
    private String kotaTujuan;
    @Basic(optional = false)
    @Column(name = "gerbong")
    private String gerbong;
    @Basic(optional = false)
    @Column(name = "kelas")
    private String kelas;
    @Basic(optional = false)
    @Column(name = "waktu_awal")
    private String waktuAwal;
    @Basic(optional = false)
    @Column(name = "waktu_akhir")
    private String waktuAkhir;
    @Basic(optional = false)
    @Column(name = "waktu_tempuh")
    private String waktuTempuh;
    @Basic(optional = false)
    @Column(name = "harga")
    private String harga;

    public List() {
    }

    public List(String idTiket) {
        this.idTiket = idTiket;
    }

    public List(String idTiket, String kotaAsal, String kotaTujuan, String gerbong, String kelas, String waktuAwal, String waktuAkhir, String waktuTempuh, String harga) {
        this.idTiket = idTiket;
        this.kotaAsal = kotaAsal;
        this.kotaTujuan = kotaTujuan;
        this.gerbong = gerbong;
        this.kelas = kelas;
        this.waktuAwal = waktuAwal;
        this.waktuAkhir = waktuAkhir;
        this.waktuTempuh = waktuTempuh;
        this.harga = harga;
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
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

    public String getGerbong() {
        return gerbong;
    }

    public void setGerbong(String gerbong) {
        this.gerbong = gerbong;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getWaktuAwal() {
        return waktuAwal;
    }

    public void setWaktuAwal(String waktuAwal) {
        this.waktuAwal = waktuAwal;
    }

    public String getWaktuAkhir() {
        return waktuAkhir;
    }

    public void setWaktuAkhir(String waktuAkhir) {
        this.waktuAkhir = waktuAkhir;
    }

    public String getWaktuTempuh() {
        return waktuTempuh;
    }

    public void setWaktuTempuh(String waktuTempuh) {
        this.waktuTempuh = waktuTempuh;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiket != null ? idTiket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof List)) {
            return false;
        }
        List other = (List) object;
        if ((this.idTiket == null && other.idTiket != null) || (this.idTiket != null && !this.idTiket.equals(other.idTiket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klmpk8.traktix.List[ idTiket=" + idTiket + " ]";
    }
    
}
