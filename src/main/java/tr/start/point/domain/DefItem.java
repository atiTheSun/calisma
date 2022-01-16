package tr.start.point.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DefItem.
 */
@Table("def_item")
public class DefItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private UUID id;

    @NotNull(message = "must not be null")
    @Size(max = 20)
    @Column("code")
    private String code;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    @Column("name")
    private String name;

    @Transient
    private DefType type;

    @Transient
    @JsonIgnoreProperties(value = { "type", "parent" }, allowSetters = true)
    private DefItem parent;

    @Column("type_id")
    private String typeId;

    @Column("parent_id")
    private UUID parentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public DefItem id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public DefItem code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public DefItem name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefType getType() {
        return this.type;
    }

    public void setType(DefType defType) {
        this.type = defType;
        this.typeId = defType != null ? defType.getId() : null;
    }

    public DefItem type(DefType defType) {
        this.setType(defType);
        return this;
    }

    public DefItem getParent() {
        return this.parent;
    }

    public void setParent(DefItem defItem) {
        this.parent = defItem;
        this.parentId = defItem != null ? defItem.getId() : null;
    }

    public DefItem parent(DefItem defItem) {
        this.setParent(defItem);
        return this;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String defType) {
        this.typeId = defType;
    }

    public UUID getParentId() {
        return this.parentId;
    }

    public void setParentId(UUID defItem) {
        this.parentId = defItem;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefItem)) {
            return false;
        }
        return id != null && id.equals(((DefItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefItem{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
