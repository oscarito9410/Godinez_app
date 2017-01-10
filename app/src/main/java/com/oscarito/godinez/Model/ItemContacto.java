package com.oscarito.godinez.Model;

/**
 * Created by oemy9 on 06/01/2017.
 */

public class ItemContacto {

    private String descripcion;
    private String informacion;
    private int imageFile;

    public  ItemContacto(String descripcion,String informacion, int imageFile){
        this.descripcion=descripcion;
        this.informacion=informacion;
        this.imageFile=imageFile;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImageFile() {
        return imageFile;
    }

    public void setImageFile(int imageFile) {
        this.imageFile = imageFile;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;

    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }


}
