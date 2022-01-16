package tr.start.point.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tr.start.point.domain.DefKitap} entity.
 */
public class DefKitapDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    @Size(max = 20)
    private String isbn;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    private String kitapAdi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefKitapDTO)) {
            return false;
        }

        DefKitapDTO defKitapDTO = (DefKitapDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, defKitapDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefKitapDTO{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", kitapAdi='" + getKitapAdi() + "'" +
            "}";
    }
}
