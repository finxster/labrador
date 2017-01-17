package br.com.labrador.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import br.com.labrador.domain.enumeration.BoneStatus;

/**
 * A Bone.
 */
@Entity
@Table(name = "bone")
public class Bone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BoneStatus status;

    @ManyToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Bone name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Bone description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoneStatus getStatus() {
        return status;
    }

    public Bone status(BoneStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(BoneStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public Bone owner(User user) {
        this.owner = user;
        return this;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bone bone = (Bone) o;
        if (bone.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, bone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Bone{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
