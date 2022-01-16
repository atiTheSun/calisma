package tr.start.point.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DefType.
 */
@Table("def_type")
public class DefType implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Size(max = 10)
    @Id
    @Column("id")
    private String id;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    @Column("name")
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DefType id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public DefType name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefType)) {
            return false;
        }
        return id != null && id.equals(((DefType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
