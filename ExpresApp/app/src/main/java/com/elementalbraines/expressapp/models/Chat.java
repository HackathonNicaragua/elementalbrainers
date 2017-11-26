package com.elementalbraines.expressapp.models;

/**
 * Created by Maurel on 25/11/2017.
 */

public class Chat {

    private String user_id;
    private String nombre;
    private String mensaje;
    private String image;

    public Chat( String user_id, String nombre, String mensaje, String image) {
        this.user_id = user_id;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.image = image;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
