using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAppLINQ
{
   public class Vista
    {
        /*
                        Pedido = p.IdPedido,
                        Fecha = p.Fecha,
                        Cliente = c.Nombre,
                        Descripcion = a.Descripcion,
                        Cantidad = p.Cantidad,
                        Importe = a.Precio * p.Cantidad,
                        Localidad = c.Localidad
         */
        public DateTime Fecha { get; set; }
        public String Cliente { get; set; }
        public double Importe { get; set; }
        public string Localidad { get; set; }

    }
}
