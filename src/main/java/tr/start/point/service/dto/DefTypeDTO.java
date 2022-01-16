package tr.start.point.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tr.start.point.domain.DefType} entity.
 */
public class DefTypeDTO implements Serializable {

    @NotNull(message = "must not be null")
    @Size(max = 10)
    private String id;

    @NotNull(message = "must not be null")
    @Size(max = 200)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefTypeDTO)) {
            return false;
        }

        DefTypeDTO defTypeDTO = (DefTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, defTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefTypeDTO{" +
            "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
