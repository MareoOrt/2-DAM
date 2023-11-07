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
                                        Indice = +1,
                                        Nombre = cliente.Nombre,
                                        TotalCompras = $"{cliente.TotalCompras:C2}"
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
            Double TotalCuentas = Datos.GetClientes().Sum<Cliente>(c => c.TotalCompras);
            Double Total = (from c in Datos.GetClientes()
                            select c.TotalCompras).Sum();

            tbResultados.Text = TotalCuentas.ToString("C") + "<=>" + Total.ToString("C");
        }

        private void Button_Click_6(object sender, RoutedEventArgs e)
        {
            var ConsultaAgrupada = (from c in Datos.GetClientes()
                                    group c by c.Localidad);

            lbResultados.Items.Clear();
            // en el foreach string es la localidad
            // es decir la key del diccionario con el resultado de la consulta
            foreach (IGrouping<string, Cliente> grupo in ConsultaAgrupada)
            {
                lbResultados.Items.Add(grupo.Key);
                foreach (Cliente cliente in grupo)
                {
                    lbResultados.Items.Add($"\t{cliente.Nombre}");
                }
            }
        }

        private void Button_Click_7(object sender, RoutedEventArgs e)
        {
            lbResultados.Items.Clear();

            var r = (from c in Datos.GetClientes()
                     group c by c.Localidad into localidad
                     select new
                     {
                         Localidad = localidad.Key,
                         Total = localidad.Sum<Cliente>(l => l.TotalCompras)
                     }).OrderByDescending(x => x.Total);

            foreach (var cl in r)
            {
                lbResultados.Items.Add($"{cl.Localidad,-10}\t{cl.Total,+12:C}");
            }
        }

        private void Button_Click_8(object sender, RoutedEventArgs e)
        {
            lbResultados.Items.Clear();

            var r = (from p in Datos.GetPedidos()
                     join c in Datos.GetClientes() on p.IdCliente equals c.IdCliente
                     join a in Datos.GetArticulos() on p.IdArticulo equals a.IdArticulo
                     select new
                     {
                         Pedido = p.IdPedido,
                         Fecha = p.Fecha,
                         Cliente = c.Nombre,
                         Descripcion = a.Descripcion,
                         Cantidad = p.Cantidad,
                         Importe = a.Precio * p.Cantidad,
                         Localidad = c.Localidad
                     } into consulta
                     group consulta by new
                     {
                         consulta.Localidad,
                         consulta.Cliente
                     });

            foreach (var grupo in r)
            {
                lbResultados.Items.Add($"{grupo.Key.Cliente} ({grupo.Key.Localidad})");
                foreach (var fila in grupo)
                {

                    lbResultados.Items.Add($"\t{fila.Fecha,35:D}:{fila.Importe,10:C}");
                }
            }
        }

        private void Button_Click_9(object sender, RoutedEventArgs e)
        {
            lbResultados.Items.Clear();

            var r = (from p in Datos.GetPedidos()
                     join c in Datos.GetClientes() on p.IdCliente equals c.IdCliente
                     join a in Datos.GetArticulos() on p.IdArticulo equals a.IdArticulo
                     select new
                     {
                         p.IdCliente,
                         c.Nombre,
                         Importe = a.Precio * p.Cantidad,
                     } into consulta
                     group consulta by consulta.IdCliente into grupo
                     select new 
                     {
                         Id=grupo.Key,
                         Cliente = grupo.First(g => g.IdCliente == grupo.Key),
                         NombreCliente = grupo.Select(x => x.Nombre).First(),
                         // Value = grupo.ToArray(),
                         Total = grupo.Sum(g => g.Importe)

                     });

            foreach (var cliente in r)
            {
                lbResultados.Items.Add($"{cliente.NombreCliente} " +
                    $"({cliente.Cliente.Nombre}) = {cliente.Total}");
            }
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
        public static List<Articulo> GetArticulos()
        {
            return new List<Articulo>()
            {
                new Articulo {IdArticulo=1, Descripcion="artículo uno",Precio=10},
                new Articulo {IdArticulo=2, Descripcion="artículo dos",Precio=20},
                new Articulo {IdArticulo=3, Descripcion="artículo tres",Precio=30},
                new Articulo {IdArticulo=4, Descripcion="artículo cuatro",Precio=40}
            };
        }
        public static List<Pedido> GetPedidos()
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

