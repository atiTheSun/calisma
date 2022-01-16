package tr.start.point.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DefKitap.
 */
@Table("def_kitap")
public class DefKitap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Size(max = 20)
    @Column("isbn")
    private String isbn;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    @Column("kitap_adi")
    private String kitapAdi;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DefKitap id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public DefKitap isbn(String isbn) {
        this.setIsbn(isbn);
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getKitapAdi() {
        return this.kitapAdi;
    }

    public DefKitap kitapAdi(String kitapAdi) {
        this.setKitapAdi(kitapAdi);
        return this;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefKitap)) {
            return false;
        }
        return id != null && id.equals(((DefKitap) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefKitap{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", kitapAdi='" + getKitapAdi() + "'" +
            "}";
    }
}
