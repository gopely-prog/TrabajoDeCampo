package clases;

import java.util.ArrayList;

public class ArregloProveedor {
    private static ArregloProveedor instancia;
    private ArrayList<Proveedor> listaProveedores;
    
    private ArregloProveedor() {
        listaProveedores = new ArrayList<Proveedor>();
    }
    
    public static ArregloProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ArregloProveedor();
        }
        return instancia;
    }
    
    public void Adicionar(Proveedor p) {
        listaProveedores.add(p);
    }
    
    public Proveedor BuscarPorRuc(String ruc) {
        for (Proveedor p : listaProveedores) {
            if (p.getRuc().equals(ruc)) {
                return p;
            }
        }
        return null;
    }
    
    public int Tamaño() {
        return listaProveedores.size();
    }
}