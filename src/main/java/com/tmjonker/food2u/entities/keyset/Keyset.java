package com.tmjonker.food2u.entities.keyset;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Keyset {

    @Id
    private Integer id;
    @NotNull
    private String keysetHandle;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getKeysetHandle() {
        return keysetHandle;
    }

    public void setKeysetHandle(String keysetHandle) {
        this.keysetHandle = keysetHandle;
    }
}
