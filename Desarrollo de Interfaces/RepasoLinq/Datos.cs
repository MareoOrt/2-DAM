using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAppLINQ
{
    public class Datos
    {
        public List<Cliente> Clientes
        {
            get { return GetClientes(); }
        }
        public List<Articulo> Articulos
        {
            get { return GetArticulos(); }
        }
        public List<Pedido> Pedidos
        {
            get { return GetPedidos(); }
        }
        public List<Cliente> GetClientes()
        {
            return new List<Cliente>()
            {
                new Cliente
                {
                IdCliente = 1,
                Nombre = "Juancito",
                Apellidos = "Pérez Pí",
                Localidad = "Valladolid",
                TotalCompras = 25000.59D
                },
                new Cliente
                {
                IdCliente = 2,
                Nombre = "Pepita",
                Apellidos = "Gómez Pí",
                Localidad = "Palencia",
                TotalCompras = 65000.13
                },
                new Cliente
                {
                IdCliente = 3,
                Nombre = "Alicia",
                Apellidos = "García",
                Localidad = "Valladolid",
                TotalCompras = 15000
                },
                new Cliente
                {
                IdCliente = 4,
                Nombre = "Fulano",
                Apellidos = "Trump",
                Localidad = "Washington",
                TotalCompras = 215000
                }
            };
        }
        private List<Articulo> GetArticulos()
        {
            return new List<Articulo>()
            {
                new Articulo {IdArticulo=1, Descripcion="artículo uno",Precio=10},
                new Articulo {IdArticulo=2, Descripcion="artículo dos",Precio=20},
                new Articulo {IdArticulo=3, Descripcion="artículo tres",Precio=30},
                new Articulo {IdArticulo=4, Descripcion="artículo cuatro",Precio=40}
            };
        }
        private List<Pedido> GetPedidos()
        {
            return new List<Pedido>()
            {
                new Pedido{ IdPedido=1, IdCliente=1, IdArticulo=1, Cantidad=3, Fecha=new DateTime(2018,10,01 )},
                new Pedido{ IdPedido=2, IdCliente=1, IdArticulo=2, Cantidad=5, Fecha=new DateTime(2018,10,02 )},
                new Pedido{ IdPedido=3, IdCliente=3, IdArticulo=1, Cantidad=10, Fecha=new DateTime(2018,10,02 )},
                new Pedido{ IdPedido=4, IdCliente=2, IdArticulo=1, Cantidad=8, Fecha=new DateTime(2018,10,02 )},
                new Pedido{ IdPedido=5, IdCliente=4, IdArticulo=3, Cantidad=2, Fecha=new DateTime(2018,10,03 )},
                new Pedido{ IdPedido=6, IdCliente=1, IdArticulo=4, Cantidad=20, Fecha=new DateTime(2018,10,03 )}
            };
        }
    }
}
