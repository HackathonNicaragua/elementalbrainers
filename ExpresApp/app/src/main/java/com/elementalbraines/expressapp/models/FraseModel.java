package com.elementalbraines.expressapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maurel on 26/11/2017.
 */

public class FraseModel {

    @SerializedName("frase")
    @Expose
    private String frase;

    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("activo")
    @Expose
    private boolean activo;
    @SerializedName("es_frase")
    @Expose
    private boolean es_frase;

    @SerializedName("imagen")
    @Expose
    private FraseModel.Imagen imagen;

    public FraseModel(String frase, String created_at, String updated_at, boolean activo, boolean es_frase, FraseModel.Imagen imagen) {
        this.frase = frase;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.activo = activo;
        this.es_frase = es_frase;
        this.imagen = imagen;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isEs_frase() {
        return es_frase;
    }

    public void setEs_frase(boolean es_frase) {
        this.es_frase = es_frase;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public FraseModel.Imagen getImagen() {
        return this.imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public class Imagen{
        @SerializedName("url")
        @Expose
        private String url;

        public Imagen(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
