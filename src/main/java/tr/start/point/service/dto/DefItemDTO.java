package tr.start.point.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tr.start.point.domain.DefItem} entity.
 */
public class DefItemDTO implements Serializable {

    private UUID id;

    @NotNull(message = "must not be null")
    @Size(max = 20)
    private String code;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    private String name;

    private DefTypeDTO type;

    private DefItemDTO parent;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefTypeDTO getType() {
        return type;
    }

    public void setType(DefTypeDTO type) {
        this.type = type;
    }

    public DefItemDTO getParent() {
        return parent;
    }

    public void setParent(DefItemDTO parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefItemDTO)) {
            return false;
        }

        DefItemDTO defItemDTO = (DefItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, defItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefItemDTO{" +
            "id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", parent=" + getParent() +
            "}";
    }
}
