package co.edu.poli.modelo.Visitor;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public interface IVisitor {
    String visit(Producto producto);
    String visit(Pedido pedido);
}
