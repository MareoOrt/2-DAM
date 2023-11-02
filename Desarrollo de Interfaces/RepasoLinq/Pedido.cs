using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfAppLINQ
{
    public class Pedido
    {
        public int IdPedido { get; set; }
        public int IdArticulo { get; set; }
        public int IdCliente { get; set; }
        public int Cantidad { get; set; }
        public DateTime Fecha { get; set; }
    }
}
