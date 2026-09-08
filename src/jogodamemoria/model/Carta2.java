package model;

import javax.swing.ImageIcon;

public class Carta2 {
    private int id;
    private ImageIcon imagemFrente;
    private ImageIcon imagemVerso;
    private boolean virada;
    private boolean encontrada;

    public Carta2(int id, ImageIcon imagemFrente, ImageIcon imagemVerso) {
        this.id = id;
        this.imagemFrente = imagemFrente;
        this.imagemVerso = imagemVerso;
        this.virada = false;
        this.encontrada = false;
    }

    public int getId() { return id; }
    public boolean isVirada() { return virada; }
    public boolean isEncontrada() { return encontrada; }
    public ImageIcon getImagemAtual() { return virada || encontrada ? imagemFrente : imagemVerso; }

    public void virar() { this.virada = !this.virada; }
    public void setEncontrada(boolean encontrada) { this.encontrada = encontrada; }
}