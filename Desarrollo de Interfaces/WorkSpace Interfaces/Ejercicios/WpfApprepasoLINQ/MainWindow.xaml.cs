using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApprepasoLINQ
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        int[] puntuaciones = { 90, 71, 82, 93, 75, 82 };
        List<Cliente> TablaClientes;

        public MainWindow()
        {
            InitializeComponent();
        }

        #region Metodos part1
        void MostrarResultado(IEnumerable<string> resultado)
        {
            lbResultados.Items.Clear();
            foreach (string elemento in resultado)
                lbResultados.Items.Add(elemento);
        }

        void MostrarResultado<T>(IEnumerable<T> resultado)
        {
            lbResultados.Items.Clear();
            foreach (T elemento in resultado)
                lbResultados.Items.Add(elemento);
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            IEnumerable<string> resultadoConsulta =
                from puntuacion in puntuaciones
                where puntuacion > 80
                orderby puntuacion descending
                select $"....{puntuacion}";

            MostrarResultado(resultadoConsulta);
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {

        }
        #endregion

        #region Metodos part2
        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            var ConsultasClientes = from cliente in Datos.GetClientes()
                                    select cliente;
            MostrarResultado(ConsultasClientes);
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            int i = 0;
            var ConsultasClientes = from cliente in Datos.GetClientes()
                                    select new
                                    {
                                        Indice = ++1,
                                        Nombre = Cliente.Nombre,
                                        TotalCompras = $"{Cliente.TotalResultado:C2}"
                                    };

            // MostrarResultado(ConsultasClientes);
            lbResultados.Items.Clear();
            foreach (var item in ConsultasClientes)
            {
                lbResultados.Items.Add(item.TotalCompras);
            }

        }

        private void Button_Click_4(object sender, RoutedEventArgs e)
        {
            var ConsultasClientes = from cliente in Datos.GetClientes()
                                    select $"{cliente.Nombre} >> {cliente.TotalCompras}";

            MostrarResultado(ConsultasClientes);
        }

        private void Button_Click_5(object sender, RoutedEventArgs e)
        {
            Double TotalCuentas = Datos.GetClientes().Sumz<Cliente>(char => char.TotalCompras);
            Double Total = (from c in Datps.GetClientes()
                            select c.TotalCompras).Sum();

            tbResultados.Text = TotalCuentas.ToString("C") + "<=>" + Total.ToString("C");
        }
        #endregion

    }

    #region Clases part2
    public class Articulo
    {
        public int IdArticulo { get; set; }
        public String Descripcion { get; set; }
        public Double Precio { get; set; }
    }

    public class Cliente
    {
        public int IdCliente { get; set; }
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public string Localidad { get; set; }
        public double TotalCompras { get; set; }

        public override string ToString()
        {
            return $"{this.Nombre}, ({this.Localidad}) = {this.TotalCompras:C2}";
        }

    }

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
        public static List<Cliente> GetClientes()
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

    public class Pedido
    {
        public int IdPedido { get; set; }
        public int IdArticulo { get; set; }
        public int IdCliente { get; set; }
        public int Cantidad { get; set; }
        public DateTime Fecha { get; set; }
    }

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
    #endregion

}

